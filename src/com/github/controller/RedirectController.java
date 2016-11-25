package com.github.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


//@Component("redirectController")
@Controller
//请求url配置
public class RedirectController {

//	@RequestMapping(value="index.do", method=RequestMethod.GET)
//	public String testFunction(HttpServletRequest req,ModelMap map) throws Exception {
//		System.out.println("我要跳转到index.jsp");
//		return "index";  //跳转到index.jsp;
//	}
	
	@RequestMapping(value="index.html")
	public String redirectToIndex(HttpServletRequest req,ModelMap map) throws Exception {
//		System.out.println("我要跳转到index.jsp");
		return "index";  //跳转到index.jsp;
	}
	
	@RequestMapping(value="wordcloud.html")
	public String redirectToWordCloud(HttpServletRequest req,ModelMap map) throws Exception {
//		System.out.println("我要跳转到redirect:/hotlanguage.html");
		return "redirect:/hotlanguage.html";  //跳转到index.jsp;
	}
	
	@RequestMapping(value="ontologyschema.html")
	public String redirectToOntologySchema(HttpServletRequest req,ModelMap map) throws Exception {
//		System.out.println("我要跳转到ontologyschema.jsp");
		return "ontologyschema";  //跳转到index.jsp;
	}
	
	@RequestMapping(value="professionalsearch.html")
	public String redirectToProfessionalSearch(HttpServletRequest req,ModelMap map) throws Exception {
//		System.out.println("我要跳转到professionalsearch.jsp");
		return "professionalsearch";  //跳转到index.jsp;
	}
	
	@RequestMapping(value="collaborativepattern.html")
	public String redirectToCollaborationPattern(HttpServletRequest req,ModelMap map) throws Exception {
//		System.out.println("我要跳转到collaborativepattern.jsp");
		return "collaborativepattern";  //跳转到index.jsp;
	}
	
	@RequestMapping(value="socialgraph.html")
	public String redirectToSocialGraph(HttpServletRequest req,ModelMap map) throws Exception {
//		System.out.println("我要跳转到redirect:/watchergraph.html");
		return "redirect:/attendancegraph.html";  //跳转到index.jsp;
	}
	
	@RequestMapping(value="watch.html")
	public String redirectToCollabVisualGraph(HttpServletRequest req,ModelMap map) throws Exception {
//		System.out.println("我要跳转到communitygraph.jsp");
		return "watch";  //跳转到index.jsp;
	}
	
	@RequestMapping(value="attendancegraph.html")
	public String redirectToWatcherVisualGraph(HttpServletRequest req,ModelMap map) throws Exception {
//		System.out.println("我要跳转到attendancegraph.jsp");
		return "attendancegraph";  //跳转到index.jsp;
	}
	
	@RequestMapping(value="programskill.html")
	public String redirectToProgramSkill(HttpServletRequest req,ModelMap map) throws Exception {
//		System.out.println("我要跳转到programskill.jsp");
		return "programskill";  //跳转到index.jsp;
	}
	
	@RequestMapping(value="hotlanguage.html")
	public String redirectToHotLanguage(HttpServletRequest req,ModelMap map) throws Exception {
//		System.out.println("我要跳转到hotlanguage.jsp");
		return "hotlanguage";  //跳转到index.jsp;
	}
	
	@RequestMapping(value="hotcommit.html")
	public String redirectToHotCommit(HttpServletRequest req,ModelMap map) throws Exception {
//		System.out.println("我要跳转到hotcommit.jsp");
		return "hotcommit";  //跳转到index.jsp;
	}
	
	@RequestMapping(value="hotdescription.html")
	public String redirectToHotDescription(HttpServletRequest req,ModelMap map) throws Exception {
//		System.out.println("我要跳转到hotdescription.jsp");
		return "hotdescription";  //跳转到index.jsp;
	}
	
	@RequestMapping(value="linegraph.html")
	public String redirectToLineGraph(HttpServletRequest req,ModelMap map) throws Exception {
//		System.out.println("我要跳转到hotdescription.jsp");
		return "linegraph";  //跳转到index.jsp;
	}
	
	@RequestMapping(value="follower.html")
	public String redirectToFollower(HttpServletRequest req,ModelMap map) throws Exception {
//		System.out.println("我要跳转到hotdescription.jsp");
		return "follower";  //跳转到index.jsp;
	}
	
	@RequestMapping(value="hottopic.html")
	public String redirectToHotTopic(HttpServletRequest req,ModelMap map) throws Exception {
//		System.out.println("我要跳转到hotdescription.jsp");
		return "hottopic";  //跳转到index.jsp;
	}
	
	@RequestMapping(value="search.html")
	public String redirectToSearch(HttpServletRequest req,ModelMap map) throws Exception {
//		System.out.println("我要跳转到hotdescription.jsp");
		return "search";  //跳转到index.jsp;
	}
	
	@RequestMapping(value="association.html")
	public String redirectToAssociation(HttpServletRequest req,ModelMap map) throws Exception {
//		System.out.println("我要跳转到hotdescription.jsp");
		return "association";  //跳转到index.jsp;
	}

	@RequestMapping(value="repositorysearch.html")
	public String redirectToRepoSearch(HttpServletRequest req,ModelMap map) throws Exception {
//		System.out.println("我要跳转到hotdescription.jsp");
		return "repositorysearch";  //跳转到index.jsp;
	}
	
	@RequestMapping(value="githubsearch.html")
	public String redirectToGithubSearch(HttpServletRequest req,ModelMap map) throws Exception {
//		System.out.println("我要跳转到hotdescription.jsp");
		return "githubsearch";  //跳转到index.jsp;
	}
	
	@RequestMapping(value="ratefeedback.html")
	public String redirectToRateFeedback(HttpServletRequest req,ModelMap map) throws Exception {
//		System.out.println("我要跳转到hotdescription.jsp");
		return "ratefeedback";  //跳转到index.jsp;
	}
	
	
//	@Override  //不带参数访问时的默认方法
//	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//		System.out.println("hihihi");
//		return new ModelAndView("index");  //跳转到registerSuccess.jsp;
//	}
	
}
