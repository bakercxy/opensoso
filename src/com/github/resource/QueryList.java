package com.github.resource;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;


@Component("querylist")
public class QueryList {
	
	List<String> sosoList = Collections.synchronizedList(new ArrayList<String>());
	List<String> githubList =  Collections.synchronizedList(new ArrayList<String>());
	
	public static int MIN = 0;
	public static int MAX = 50;
	
	public List<String> getSosoList() {
		return sosoList;
	}
	public void setSosoList(List<String> sosoList) {
		this.sosoList = sosoList;
	}
	public List<String> getGithubList() {
		return githubList;
	}
	public void setGithubList(List<String> githubList) {
		this.githubList = githubList;
	}
	
}
