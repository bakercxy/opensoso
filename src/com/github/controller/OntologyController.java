package com.github.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.service.SearchService;

@Controller
public class OntologyController {
	
	private SearchService searchService;

	public SearchService getSearchService() {
		return searchService;
	}

	@Resource(name = "searchService")
	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}
	
	@RequestMapping(value="ontology.do")
	@ResponseBody
	public String search(HttpServletRequest req){
		
		String word = req.getParameter("word");
		try {
        	return searchService.getSchema(word);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
	}
	
public List<String> getChilds(String query) {
    	
        try {
        	return searchService.getChilds(query);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public List<String> getBrothers(String query) {  
    	try {
        	return searchService.getBrothers(query);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String getParent(String query) {  
    	try {
        	return searchService.getParent(query);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
