package edu.sjtu.bean;

import java.util.List;

public class Query {
	String queryString;
	List<Integer> scores;
	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	public List<Integer> getScores() {
		return scores;
	}
	public void setScores(List<Integer> scores) {
		this.scores = scores;
	}
	public Query(String queryString, List<Integer> scores) {
		super();
		this.queryString = queryString;
		this.scores = scores;
	}
	public Query() {
		super();
	}
	
}
