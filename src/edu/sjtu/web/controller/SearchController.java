package edu.sjtu.web.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.sjtu.core.ranking.RepoFetch;
import edu.sjtu.core.ranking.RepoRanking;
import edu.sjtu.web.bean.SearchRepo;
import edu.sjtu.web.util.Path;

@Controller
public class SearchController {
	private edu.sjtu.core.resource.Resource resource;
	private RepoRanking repoRanking;
	private RepoFetch repoFetch;
	
	public RepoFetch getRepoFetch() {
		return repoFetch;
	}

	@Resource(name = "githubrank")
	public void setRepoFetch(RepoFetch repoFetch) {
		this.repoFetch = repoFetch;
	}

	public RepoRanking getRepoRanking() {
		return repoRanking;
	}

	@Resource(name = "reporank")
	public void setRepoRanking(RepoRanking repoRanking) {
		this.repoRanking = repoRanking;
	}

	public edu.sjtu.core.resource.Resource getResource() {
		return resource;
	}

	@Resource(name = "resource")
	public void setResource(edu.sjtu.core.resource.Resource resource) {
		this.resource = resource;
	}


	@RequestMapping(value="reposearch.do")
	public String repoSearch(String query,ModelMap map){
		try {
			query = new String(query.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query = query.trim();
//		String[] ss = query.split(" ");
//		query = query.substring(0, query.length()-ss[ss.length-1].length()).trim();
		map.put("query", query);
		
		Date d = new Date();
		long l1 = d.getTime();
		
//		List result = repoRanking.rankScore(query, Integer.parseInt(ss[ss.length-1]));
		List result = repoRanking.rankScore(query, 100, true);
		
		d = new Date();
		long l2 = d.getTime();
		double time = (l2 - l1) / 1000.00;
		
		map.put("searchtime", time);
		
		if(result.size() == 0) //没有结果
			return "noreporesult";
		else
		{
			map.put("result", result);
			map.put("rank",false);
			return "reposearchlist";
		}
	}
	
	@RequestMapping(value="reporate.do")
	public String repoRank(String query,ModelMap map){
		try {
			query = new String(query.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query = query.trim();
		map.put("query", query);
		System.out.println("query: " + query);
		
		Date d = new Date();
		long l1 = d.getTime();
		
		List result = repoRanking.rankScore(query, 100,true);
		
		d = new Date();
		long l2 = d.getTime();
		double time = (l2 - l1) / 1000.00;
		
		map.put("searchtime", time);
		
		if(result.size() == 0) //没有结果
			return "noreporesult";
		else
		{
			map.put("result", result);
			map.put("rank",true);
			return "reposearchlist";
		}
	}
	
	@RequestMapping(value="githubsearch.do")
	public String githubSearch(String query,ModelMap map){
		try {
			query = new String(query.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query = query.trim();
		map.put("query", query);
		System.out.println("query: " + query);
		
		Date d = new Date();
		long l1 = d.getTime();
		
		List result = repoFetch.search(query, 10);
		
		d = new Date();
		long l2 = d.getTime();
		double time = (l2 - l1) / 1000.00;
		
		map.put("searchtime", time);
		
		if(result.size() == 0) //没有结果
			return "nogithubresult";
		else
		{
			map.put("result", result);
			map.put("rank",false);
			return "githubsearchlist";
		}
	}
	
	@RequestMapping(value="githubrate.do")
	public String githubRate(String query,ModelMap map){
		try {
			query = new String(query.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query = query.trim();
		map.put("query", query);
		System.out.println("query: " + query);
		
		Date d = new Date();
		long l1 = d.getTime();
		
		List result = repoFetch.search(query, 10);
		
		d = new Date();
		long l2 = d.getTime();
		double time = (l2 - l1) / 1000.00;
		
		map.put("searchtime", time);
		
		if(result.size() == 0) //没有结果
			return "nogithubresult";
		else
		{
			map.put("result", result);
			map.put("rank",true);
			return "githubsearchlist";
		}
	}
	
	@RequestMapping(value="repocompare.do")
	public String repoCompare(String query,ModelMap map){
		try {
			query = new String(query.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query = query.trim();
		map.put("query", query);
		
		Date d = new Date();
		
		List<SearchRepo> result = repoRanking.rankScore(query, 30, false);
		
		Random r = new Random();
		int id1 = r.nextInt(30);
		int id2 = -1;
		
		while(id2 == -1)
		{
			int step = r.nextInt(10) - 5;
			if(id1 + step < 30 && id1 + step >= 0 && step != 0)
				id2 = id1 + step;
		}
		
		System.out.println("id1:" + id1);
		System.out.println("id2:" + id2);
		List<SearchRepo> comparePair = new ArrayList<SearchRepo>();
		comparePair.add(result.get(id1));
		comparePair.add(result.get(id2));
		map.put("id1", result.get(id1).getId());
		map.put("id2", result.get(id2).getId());
		map.put("result", comparePair);
		return "repocomparelist";
	}
	
	@RequestMapping(value="pairsubmit.do")
	public String PairCompare(String query,int id1,int id2, int result, ModelMap map){
		try {
			query = new String(query.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FileWriter fw;
		try {
			fw = new FileWriter(Path.logPath + "labelpair.dat",true);
			fw.write(query + "," + id1 + "," + id2 + "," + (id1 == result?1:0) + "\n");  
			fw.write(query + "," + id2 + "," + id1 + "," + (id1 == result?0:1) + "\n");  
		    fw.close();  		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return repoCompare(query,map);
	}
}
