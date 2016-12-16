package edu.sjtu.eva.tag;

import java.util.List;

public class Repo {
	int docId;
	int id;
//	String repoName;
	String userName;
	String description;
	String readme;
//	String createDate;
//	String updateDate;
	String language;
	List<String> userList;
	String reponame;
	
	public String getReponame() {
		return reponame;
	}
	public void setReponame(String reponame) {
		this.reponame = reponame;
	}
	public Repo(int docId, int id, String userName, String description,
			String readme, String language, List<String> userList) {
		super();
		this.docId = docId;
		this.id = id;
		this.userName = userName;
		this.description = description;
		this.readme = readme;
		this.language = language;
		this.userList = userList;
	}
	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReadme() {
		return readme;
	}
	public void setReadme(String readme) {
		this.readme = readme;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public List<String> getUserList() {
		return userList;
	}
	public void setUserList(List<String> userList) {
		this.userList = userList;
	}
	public Repo(int id, String userName, String description, String readme,
			String language, List<String> userList) {
		super();
		this.id = id;
		this.userName = userName;
		this.description = description;
		this.readme = readme;
		this.language = language;
		this.userList = userList;
	}
	
	@Override
	public String toString() {
		return "Repo [docId=" + docId + ", id=" + id + ", userName=" + userName
				+ ", description=" + description + ", readme=" + readme
				+ ", language=" + language + ", userList=" + userList
				+ ", reponame=" + reponame + "]";
	}
	public Repo(){
		
	}
	
}
