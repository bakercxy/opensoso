package com.github.model;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "search_users")
public class User extends ComparableBean implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 455787192408982531L;
	private String tags;  //和该用户相关的标签
	private String user;  //用户名
	private double score;  //评分
	private String rlink;  //url
	private int index;  //索引
	private String type;
	private String name;
	private String created_at;
	private String updated_at;
	private String blog;
	private String location;
	private String email;
	private String company;
	
	
	public User(int index, double score) {
		super(index, score);
		
		this.index = index;
		this.score = score;
		this.type = "USER";
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getCreated_at() {
		return created_at;
	}




	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}




	public String getUpdated_at() {
		return updated_at;
	}




	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}




	public String getBlog() {
		return blog;
	}




	public void setBlog(String blog) {
		this.blog = blog;
	}




	public String getLocation() {
		return location;
	}




	public void setLocation(String location) {
		this.location = location;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getCompany() {
		return company;
	}




	public void setCompany(String company) {
		this.company = company;
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

	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
		super.setSortscore(score);
	}
	public String getRlink() {
		return rlink;
	}
	public void setRlink(String rlink) {
		this.rlink = rlink;
	}


	@Override
	public String toString() {
		return "User [tags=" + tags + ", user=" + user + ", score=" + score
				+ ", rlink=" + rlink + ", index=" + index + ", type=" + type
				+ ", name=" + name + ", created_at=" + created_at
				+ ", updated_at=" + updated_at + ", blog=" + blog
				+ ", location=" + location + ", email=" + email + ", company="
				+ company + "]";
	}

}
