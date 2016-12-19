package edu.sjtu.core.resource;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import edu.sjtu.core.taxonomy.Taxonomy;
import edu.sjtu.web.bean.SearchRepo;
import edu.sjtu.web.util.Path;
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
    Map<String,Integer> title;  //title到自标id的映射
    Map<Integer, SearchRepo> repos;  //自标id到title的映射
    Map<Integer,Integer> repoids;  //github-id到自标id的映射
    Map<Integer, Double> effects; //repoid到影响力
    public static double KS = 0.2, KF = 0.15, KU = 1000;

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
        
        effectCompute();
    }

	public Map<Integer, Integer> getRepoids() {
		return repoids;
	}
	
	private void effectCompute() {
		if (effects == null) {
			System.out.println("computing repository effects ...");
			effects = new HashMap<Integer, Double>();
			for (int id : repos.keySet()) {

				effects.put(id,
						(Math.log10(1.0 + Math.sqrt(1.0 + KS
								* repos.get(id).getStargazers()
								+ KF * repos.get(id).getForks())) + 1.0)
								* Math.pow(2.0, -getDayDiff(id) / KU));
			}
		}
	}
	
	public Map<Integer, Double> getEffects() {
		return effects;
	}

	public void setEffects(Map<Integer, Double> effects) {
		this.effects = effects;
	}

	private int daysBetween(Date smdate, Date bdate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	public double getDayDiff(int id) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String stringDate = "2015-07-31 00:00:00";
		Date curDate;
		try {
			curDate = df.parse(stringDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			curDate = new Date(115, 7, 31);
			e1.printStackTrace();
		}
		Date repoDate;
		try {
			repoDate = df.parse(getRepos().get(id).getDate()
					.replace("T", " ").replace("Z", " "));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			repoDate = new Date(115, 1, 1);
			e.printStackTrace();
		}

		int daydiff = 180;
		try {
			daydiff = daysBetween(repoDate, curDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return daydiff;
	}

}
