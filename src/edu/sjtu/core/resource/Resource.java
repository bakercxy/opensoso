package edu.sjtu.core.resource;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import edu.sjtu.core.taxonomy.Taxonomy;
import edu.sjtu.web.bean.SearchRepo;
import edu.sjtu.web.config.Path;
import edu.sjtu.web.util.FileUtil;
import edu.sjtu.web.util.ILineHandler;

/**
 * Created by BakerCxy on 2015/11/12.
 */
@Component("resource")
public class Resource {

    Set<String> stopWord = new HashSet<String>();
    Set<String> stopPunc = new HashSet<String>();
    Taxonomy taxonomy;
    Map<String, Map<Integer,Object>> tt; //标签对项目的权值倒排表
    Map<String, Map<Integer,Object>> tw; //文本对项目的权值倒排表
    Map<String,Integer> title;  //自标id到title的映射
    Map<Integer, SearchRepo> repos;  //自标id到title的映射
    Map<Integer,Integer> repoids;  //github-id到自标id的映射

    public Map<String, Map<Integer, Object>> getTt() {
        return tt;
    }

    public Map<String, Map<Integer, Object>> getTw() {
        return tw;
    }

    public Map<String, Integer> getTitle() {
        return title;
    }

    public Set<String> getStopWord() {
        return stopWord;
    }

    public Set<String> getStopPunc() {
        return stopPunc;
    }

    public Taxonomy getTaxonomy() {
        return taxonomy;
    }

    public Map<Integer, SearchRepo> getRepos() {
        return repos;
    }

    public Resource(){
        System.out.println("initializing stopwords ...");
        FileUtil.readFile(new File(Path.resPath + "english_stopword.txt"), new ILineHandler() {
            @Override
            public void process(String s, int i) throws Exception {
                stopWord.add(s);
            }
        });

        System.out.println("initializing punctuations ...");
        FileUtil.readFile(new File(Path.resPath + "english_punctuation.txt"), new ILineHandler() {
            @Override
            public void process(String s, int i) throws Exception {
                stopPunc.add(s);
            }
        });

        System.out.println("initializing taxonomy ...");
        taxonomy = new Taxonomy();

        System.out.println("initializing tag table ...");
        tt = new HashMap<String, Map<Integer,Object>>();
        FileUtil.readFile(new File(Path.bigResPath + "tagit.dat"), new ILineHandler() {
            @Override
            public void process(String s, int i) throws Exception {
                int index = s.indexOf(":");
                String tag = s.substring(0, index);  //提取标签
                String nolinetag = tag.replaceAll("[\\-_]", " ");  //提取无横杠的标签
                Map<Integer, Object> map = JSONObject.parseObject(s.substring(index+1), HashMap.class);
                tt.put(tag, map);
                tt.put(nolinetag, map);
            }
        });

        tw = new HashMap<String, Map<Integer,Object>>();
        System.out.println("initializing word table ...");
        FileUtil.readFile(new File(Path.bigResPath + "wordit.dat"), new ILineHandler() {
            @Override
            public void process(String s, int i) throws Exception {
                int index = s.indexOf(":");
                String word = s.substring(0, index);
                Map<Integer, Object> map = JSONObject.parseObject(s.substring(index + 1), HashMap.class);
                tw.put(word, map);
            }
        });

        System.out.println("initializing repoinfo ...");
        repos = new HashMap<Integer, SearchRepo>();
        title = new HashMap<String,Integer>();
        FileUtil.readFile(new File(Path.bigResPath + "repoinfo.dat"), new ILineHandler() {
            @Override
            public void process(String s, int i) throws Exception {
                try {
                    SearchRepo sr = JSONObject.parseObject(s,SearchRepo.class);
                    repos.put(sr.getId(),sr);
                    title.put(sr.getReponame(), sr.getId());
                }catch(Exception e){
                    System.out.println(i);
                    e.printStackTrace();
                }
            }
        });
        
        System.out.println("initializing repoid ...");
        repoids = new HashMap<Integer,Integer>();
        FileUtil.readFile(new File(Path.bigResPath + "repoid.dat"), new ILineHandler() {
			@Override
			public void process(String s, int i) throws Exception {
				int gitid = Integer.parseInt(s.split(":")[0]);
				int myid = Integer.parseInt(s.split(":")[1]);
				repoids.put(gitid,myid);
			}
		});
    }

	public Map<Integer, Integer> getRepoids() {
		return repoids;
	}

}
