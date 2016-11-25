package com.github.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "langtime")
public class LangTime implements Serializable {

	private static final long serialVersionUID = -5785857960597910260L;

	private String language;
	private Map<String,Integer> time;
	private String id;
	
	public LangTime(){
		
	}
	
	public LangTime(String id,String language){
		this.id = id;
	}
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public Map<String, Integer> getTime() {
		return time;
	}

	public void setTime(Map<String, Integer> time) {
		this.time = time;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "LangTime [language=" + language + ", time=" + time + ", id="
				+ id + "]";
	}
	
}
