package edu.sjtu.web.controller;

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
	
	@RequestMapping(value="paircomp.html")
	public String redirectToPairComp(HttpServletRequest req,ModelMap map) throws Exception {
//		System.out.println("我要跳转到hotdescription.jsp");
		return "repocomparelist";  //跳转到index.jsp;
	}
	
	
//	@Override  //不带参数访问时的默认方法
//	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//		System.out.println("hihihi");
//		return new ModelAndView("index");  //跳转到registerSuccess.jsp;
//	}
	
}
