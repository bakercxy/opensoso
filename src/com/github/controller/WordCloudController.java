package com.github.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.model.HotCommit;
import com.github.model.HotDes;
import com.github.model.HotLang;
import com.github.service.HotCommitService;
import com.github.service.HotDesService;
import com.github.service.HotLangService;
import com.github.service.HotWordService;

@Controller
public class WordCloudController{

	private HotLangService hotLangService;
	private HotCommitService hotCommitService;
	private HotDesService hotDesService;
//	private HotWordService hotWordService;
	
	
//	public HotWordService getHotWordService() {
//		return hotWordService;
//	}
//
//	@Resource(name="hotWordService")
//	public void setHotWordService(HotWordService hotWordService) {
//		this.hotWordService = hotWordService;
//	}

	public HotLangService getHotLangService() {
		return hotLangService;
	}

	@Resource(name="hotLangService")
	public void setHotLangService(HotLangService hotLangService) {
		this.hotLangService = hotLangService;
	}

	public HotCommitService getHotCommitService() {
		return hotCommitService;
	}
	
	@Resource(name="hotCommitService")
	public void setHotCommitService(HotCommitService hotCommitService) {
		this.hotCommitService = hotCommitService;
	}

	public HotDesService getHotDesService() {
		return hotDesService;
	}

	@Resource(name="hotDesService")
	public void setHotDesService(HotDesService hotDesService) {
		this.hotDesService = hotDesService;
	}

	@RequestMapping(value="queryword.do",params="type=lang")
	@ResponseBody
	public Map<String, List<HotLang>> QueryWordCloud_lang(HttpServletRequest req) throws Exception {
		String f = req.getParameter("freq");
		double freq = 0;
		if(f.endsWith("%"))
			freq = new Float(f.substring(0,f.indexOf("%")))/100;
		else if(f.equals("All"))
			freq = 0;
		String s = req.getParameter("minsize");
		Map<String,List<HotLang>> result = new HashMap<String,List<HotLang>>();
		List<HotLang> wordList = hotLangService.queryHotLangList(freq,70,10);
		result.put("wordlist", wordList);
		return result;
	}
	
	@RequestMapping(value="queryword.do",params="type=commit")
	@ResponseBody
	public Map<String, List<HotCommit>> QueryWordCloud_commit(HttpServletRequest req) throws Exception {
		String f = req.getParameter("freq");
		double freq = 0;
		if(f.endsWith("%"))
			freq = new Float(f.substring(0,f.indexOf("%")))/100;
		else if(f.equals("All"))
			freq = 0;
		String s = req.getParameter("minsize");
		Map<String,List<HotCommit>> result = new HashMap<String,List<HotCommit>>();
		List<HotCommit> wordList = hotCommitService.queryHotCommitList(freq,70,10);
		result.put("wordlist", wordList);
		return result;
	}
	
	@RequestMapping(value="queryword.do",params="type=des")
	@ResponseBody
	public Map<String, List<HotDes>> QueryWordCloud_des(HttpServletRequest req) throws Exception {
		String f = req.getParameter("freq");
		double freq = 0;
		if(f.endsWith("%"))
			freq = new Float(f.substring(0,f.indexOf("%")))/100;
		else if(f.equals("All"))
			freq = 0;
		int minsize = Integer.parseInt(req.getParameter("minsize"));
		int maxsize = Integer.parseInt(req.getParameter("maxsize"));
		Map<String,List<HotDes>> result = new HashMap<String,List<HotDes>>();
		List<HotDes> wordList = hotDesService.queryHotDesList(freq,maxsize,minsize);
		result.put("wordlist", wordList);
		return result;
	}
	
//	@RequestMapping(value="queryword.do")
//	@ResponseBody
//	public Map<String, List> QueryWordCloud(HttpServletRequest req) throws Exception {
//		String f = req.getParameter("freq");
//		String type = req.getParameter("type");
//		
//		System.out.println("type:" + type);
//		
//		double freq = 0;
//		if(f.endsWith("%"))
//			freq = new Float(f.substring(0,f.indexOf("%")))/100;
//		else if(f.equals("All"))
//			freq = 0;
//		int minsize = Integer.parseInt(req.getParameter("minsize"));
//		int maxsize = Integer.parseInt(req.getParameter("maxsize"));
//		Map<String,List> result = new HashMap<String,List>();
//		List wordList = hotWordService.queryHotWordList(freq,maxsize,minsize,type);
//		result.put("wordlist", wordList);
//		return result;
//	}
}
