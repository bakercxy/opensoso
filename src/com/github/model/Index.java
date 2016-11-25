package com.github.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "search_index")
public class Index implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3887275282246529615L;
	private String docs;  //拥有者
	private String word;  //url
	private String id;  //索引
	
	public Index(String word){
		this.word = word;
	}
	
	public String getDocs() {
		return docs;
	}
	public void setDocs(String docs) {
		this.docs = docs;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
