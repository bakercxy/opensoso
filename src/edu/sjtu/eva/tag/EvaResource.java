package edu.sjtu.eva.tag;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import edu.sjtu.web.config.Path;
import edu.sjtu.web.util.FileUtil;
import edu.sjtu.web.util.ILineHandler;

@Component("evaresource")
@Scope("singleton")
public class EvaResource {

	public Map<Integer, Repo> repoIndex;
	public Map<Integer, EvaTag> combinedTagIndex;
	public Map<Integer, EvaTag> labeledTagIndex;

	public Map<Integer, EvaTag> getLabeledTagIndex() {
		return labeledTagIndex;
	}

	public void setLabeledTagIndex(Map<Integer, EvaTag> labeledTagIndex) {
		this.labeledTagIndex = labeledTagIndex;
	}

	public EvaResource() {
		if(combinedTagIndex == null)
		{
			combinedTagIndex = new HashMap<Integer, EvaTag>();
			labeledTagIndex = new HashMap<Integer, EvaTag>();
			File file = new File(Path.logPath + "tag-log.dat");
			if(file.exists())
			{
				System.out.println("TagEva: Reading labeled repos...");
				FileUtil.readFile(file, new ILineHandler() {
						@Override
						public void process(String s, int i) throws Exception {
							EvaTag evaTag = JSONObject.parseObject(s, EvaTag.class);
							labeledTagIndex.put(evaTag.getId(), evaTag);
						}
					});
			}
			
			System.out.println("TagEva: Reading repo infos...");
			repoIndex = new HashMap<Integer, Repo>();
			FileUtil.readFile(new File(Path.resPath + "allrepoinfo.dat"),
					new ILineHandler() {
						@Override
						public void process(String s, int i) throws Exception {
							Repo repo = JSONObject.parseObject(s, Repo.class);
							if(!labeledTagIndex.containsKey(repo.getDocId()))
							{
								repoIndex.put(repo.getDocId(), repo);
								combinedTagIndex.put(repo.getDocId(), new EvaTag(repo.getDocId(), new HashSet<String>()));
							}
						}
					});

			System.out.println("TagEva: Combining tags ...");
			File dir = new File(Path.resPath + "tags");
			for (File f : dir.listFiles())
				FileUtil.readFile(f, new ILineHandler() {
					@Override
					public void process(String s, int i) throws Exception {
						EvaTag evaTag = JSONObject.parseObject(s, EvaTag.class);
						if(!labeledTagIndex.containsKey(evaTag.getId()))
							combinedTagIndex.get(evaTag.getId()).getTags().addAll(evaTag.getTags());
					}
				});
			
			System.out.println("TagEva: Remaining Size is " + repoIndex.size());
		}
			
		
	}
	
	public  Map<Integer, Repo> getRepoIndex() {
		return repoIndex;
	}

	public  void setRepoIndex(Map<Integer, Repo> repoIndex) {
		this.repoIndex = repoIndex;
	}

	public  Map<Integer, EvaTag> getCombinedTagIndex() {
		return combinedTagIndex;
	}

	public  void setCombinedTagIndex(Map<Integer, EvaTag> combinedTagIndex) {
		this.combinedTagIndex = combinedTagIndex;
	}

	public synchronized int getOneRepoId(){
		Random r = new Random();
		int size = repoIndex.size();
		if(size == 0)
			return -1;
		int step = r.nextInt(size);
		for(int index : repoIndex.keySet())
		{
			if(step-- == 0)
				return index;
		}
		return 0;
	}
	
	public Repo getRepoInfoById(int id){
		if(repoIndex.containsKey(id))
			return repoIndex.get(id);
		else
			return null;
	}
	
	public EvaTag getRepoAllTagById(int id){
		if(combinedTagIndex.containsKey(id))
			return combinedTagIndex.get(id);
		else
			return null;
	}
	
	public synchronized void setAnalysisResult(int id, Set<String> tags){
		if(labeledTagIndex.containsKey(id))
			labeledTagIndex.get(id).getTags().addAll(tags);
		else
			labeledTagIndex.put(id, new EvaTag(id, tags));
		
		repoIndex.remove(id);
		combinedTagIndex.remove(id);
		
		List<String> tagList = Collections.synchronizedList(new ArrayList<String>());
		for(int index : labeledTagIndex.keySet())
			tagList.add(JSONObject.toJSONString(labeledTagIndex.get(index)));
		FileUtil.writeFile(new File(Path.logPath + "tag-log.dat"), tagList);
		
	}
}
