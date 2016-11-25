package com.github.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.github.dao.LangTimeDAO;
import com.github.model.LangTime;

@Component("langTimeService")
public class LangTimeService {
	
	private LangTimeDAO langTimeDAO;
	
	
	public LangTimeDAO getLangTimeDAO() {
		return langTimeDAO;
	}

	@Resource(name = "langTimeDAOImpl")
	public void setLangTimeDAO(LangTimeDAO langTimeDAO) {
		this.langTimeDAO = langTimeDAO;
	}
	
	public List<LangTime> getLangTimeList(String[] lang){
//		LangTime criteriaLangTime = new LangTime();
//		System.out.println(lang);
//		criteriaLangTime.setLanguage(lang);
		List<LangTime> langTimeList =  langTimeDAO.find(lang,0,0);
		
		return langTimeList;
	}
	
}
