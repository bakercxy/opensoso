package edu.sjtu.web.controller;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.sjtu.eva.tag.EvaResource;
import edu.sjtu.eva.tag.EvaTag;
import edu.sjtu.eva.tag.Repo;

@Controller
public class TagAnalysisController {
	
	EvaResource evaResource;
	
	public EvaResource getEvaResource() {
		return evaResource;
	}

	@Resource(name="evaresource")
	public void setEvaResource(EvaResource evaResource) {
		this.evaResource = evaResource;
	}

	@RequestMapping(value="taganalysis.html")
	public String returnRepoInfo(ModelMap map) throws Exception {
		
		int repoid = evaResource.getOneRepoId();
		Repo repo = evaResource.getRepoInfoById(repoid);
		EvaTag tags = evaResource.getRepoAllTagById(repoid);
		
		map.put("title", repo.getReponame());
		map.put("id", repo.getDocId());
		map.put("des", repo.getDescription());
		map.put("lang", repo.getLanguage());
		map.put("rm", repo.getReadme());
		
		map.put("tags", tags.getTags());
		
		return "taganalysis"; //跳转到taganalysis.jsp;
	}
	
	@RequestMapping(value="submittaganalysis")
	@ResponseBody
	public String receiveResult(String tagresult, String repoid, ModelMap map){
		String[] tags = tagresult.split(",");
		int id = Integer.parseInt(repoid);
		
		Set<String> tagSet = new HashSet<String>();
		for(String tag : tags)
			tagSet.add(tag);
		evaResource.setAnalysisResult(id, tagSet);
		System.out.println("id:" + id + "\t" + "tags:" + tagSet);
		System.out.println("Remaining Repo Size is " + evaResource.getRepoIndex().size());
		
		return "taganalysis"; //跳转到taganalysis.jsp;
	}
}
