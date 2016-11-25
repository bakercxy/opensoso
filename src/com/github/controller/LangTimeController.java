package com.github.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.model.LangTime;
import com.github.service.LangTimeService;

@Controller
public class LangTimeController{
	private LangTimeService langTimeService;

	public LangTimeService getLangTimeService() {
		return langTimeService;
	}

	@Resource(name = "langTimeService")
	public void setLangTimeService(LangTimeService langTimeService) {
		this.langTimeService = langTimeService;
	}

	@RequestMapping(value="querylangtime.do")
	@ResponseBody
	public List<List<Object>> QueryWordCloud_lang(HttpServletRequest req) throws Exception {
		String lang = req.getParameter("lang");
		System.out.println(lang);
		String year = req.getParameter("year");
		System.out.println(year);
		String[] langSet;
		if(!lang.equals(""))
			langSet = lang.split("_");
		else
		{
			System.out.println("langSet is null");
			langSet = new String[1];
			langSet[0] = "null";
		}
		
		List<LangTime> langTimeList = langTimeService.getLangTimeList(langSet);
		
		if(langTimeList.size() != 0)
		{
			List<List<Object>> resultList =  new ArrayList<List<Object>>();
			//插入x轴的数据
			List<Object> xAxisList = new ArrayList<Object>();
			xAxisList.add("");
			Iterator<String> iter = langTimeList.get(0).getTime().keySet().iterator();
			while(iter.hasNext())
			{
				String date = iter.next();
				if(date.startsWith(year) || year.equals("All"))
					xAxisList.add(date);
			}
			
			resultList.add(xAxisList);
			
			//插入data
			Iterator<LangTime> langIter = langTimeList.iterator();
			while(langIter.hasNext())
			{
				LangTime langTime = langIter.next();
				if(!langTime.getLanguage().equals("null"))
				{
					List<Object> data = new ArrayList<Object>();
					data.add(langTime.getLanguage());
					iter = langTime.getTime().keySet().iterator();
					while(iter.hasNext())
					{
						String date = iter.next();
						if(date.startsWith(year) || year.equals("All"))
							data.add(langTime.getTime().get(date));
					}
					resultList.add(data);
				}
			}
			return resultList;
		}
		
		return null;
		
		
	}
	
}
