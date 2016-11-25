package com.github.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.module.RepoFetch;
import com.github.module.RepoRanking;
import com.github.service.SearchService;

@Controller
public class SearchController {
	private SearchService searchService;
	private com.github.resource.Resource resource;
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

	public com.github.resource.Resource getResource() {
		return resource;
	}

	@Resource(name = "resource")
	public void setResource(com.github.resource.Resource resource) {
		this.resource = resource;
	}

	public SearchService getSearchService() {
		return searchService;
	}

	@Resource(name = "searchService")
	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}
	
	@RequestMapping(value="search.do")
	public String search(String query,String type,ModelMap map){
		try {
			query = new String(query.getBytes("ISO-8859-1"), "UTF-8");
			type = new String(type.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
//		System.out.println("search:" + query);
//		System.out.println("type:" + type);
		
		map.put("query", query);
		map.put("type", type);
		
		Date d = new Date();
		long l1 = d.getTime();
		int typeNumber = 0;
		
		if(type.equals("All"))
			typeNumber = 0;
		else if(type.equals("Users"))
			typeNumber = -1;
		else if(type.equals("Repos"))
			typeNumber = 1;
		
		System.out.println("typeNumber:" + typeNumber);
		
		List result = searchService.getSearchList(query,typeNumber);
		
		d = new Date();
		long l2 = d.getTime();
		double time = (l2 - l1) / 1000.00;
		
		map.put("searchtime", time);
		
		if(result.size() == 0) //没有结果
			return "noresult";
		else
		{
			map.put("result", result);
			return "searchlist";
		}
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
		map.put("query", query);
		System.out.println("query: " + query);
		
		Date d = new Date();
		long l1 = d.getTime();
		
		List result = repoRanking.rankScore(query, 100);
		
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
		
		List result = repoRanking.rankScore(query, 10);
		
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
}
