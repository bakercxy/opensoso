package edu.sjtu.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

import edu.sjtu.core.resource.ScoreSaveRunnable;
import edu.sjtu.web.bean.Query;

@Controller
public class RateController {
	
	@RequestMapping(value="ratesubmit.do")
	public String Rate(String query,String score,String engine,ModelMap map){
		try {
			query = new String(query.getBytes("ISO-8859-1"), "UTF-8");
			score = new String(score.getBytes("ISO-8859-1"), "UTF-8");
			engine = new String(engine.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
//		System.out.println(query);
//		System.out.println(score);
//		System.out.println(engine);
		
		List<Integer> scores = JSONObject.parseObject(score,List.class);
		Query queryBean = new Query(query, scores);
		
		if(engine.equals("soso"))
		{
			ScoreSaveRunnable.sosoList.add(JSONObject.toJSONString(queryBean));
//			System.out.println("size:" + ScoreSaveRunnable.sosoList.size());
		}
		else if(engine.equals("github"))
		{
			ScoreSaveRunnable.githubList.add(JSONObject.toJSONString(queryBean));
//			System.out.println("size:" + ScoreSaveRunnable.githubList.size());
		}

//		System.out.println(JSONObject.toJSONString(queryBean));
		
		return "ratefeedback";
	}

}
