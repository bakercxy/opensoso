package com.github.model;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "search_repos")
public class Repo extends ComparableBean implements Serializable {
	
	private static final long serialVersionUID = 5674814629786694753L;
	private String create_date;  //仓库创建日期
	private String description;  //仓库描述
	private String language;  //仓库开发语言
	private String tags;  //和该仓库相关的标签
	private String repo;  //仓库名称
	private double score;  //评分
	private String owner;  //拥有者
	private String rlink;  //url
	private int index;  //索引
	private String type;
	
	public Repo(int index, double score) {
		super(index, score);
		
		this.index = index;
		this.score = score;
		this.type = "REPO";
		// TODO Auto-generated constructor stub
	}
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
		super.setSortindex(index);
	}

	public String getCreate_date() {
		return create_date;
	}
	
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getRepo() {
		return repo;
	}
	public void setRepo(String repo) {
		this.repo = repo;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
		super.setSortscore(score);
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getRlink() {
		return rlink;
	}
	public void setRlink(String rlink) {
		this.rlink = rlink;
	}

	@Override
	public String toString() {
		return "Repo [create_date=" + create_date + ", description="
				+ description + ", language=" + language + ", tags=" + tags
				+ ", repo=" + repo + ", score=" + score + ", owner=" + owner
				+ ", rlink=" + rlink + ", index=" + index + ", type=" + type
				+ "]";
	}
	
}
