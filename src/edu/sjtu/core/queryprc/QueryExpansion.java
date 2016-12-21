package edu.sjtu.core.queryprc;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import edu.sjtu.core.taxonomy.WordTable;

/**
 * Created by BakerCxy on 2015/11/12.
 */
@Component("queryExpansion")
public class QueryExpansion {

	edu.sjtu.core.resource.Resource resource;
	

    public edu.sjtu.core.resource.Resource getResource() {
		return resource;
	}

    @Resource(name = "resource")
	public void setResource(edu.sjtu.core.resource.Resource resource) {
		this.resource = resource;
	}
    

	public QueryExpansion(edu.sjtu.core.resource.Resource resource) {
		super();
		this.resource = resource;
	}
	
	public QueryExpansion() {
		super();
	}

	public Map<String,Double> getWeightedQuery(String sentence){

        Map<String,Double> weightedTermMap = new HashMap<String,Double>();
        WordProcess wp = new WordProcess(resource);
        WordTable wt = new WordTable(resource.getTaxonomy());
        wp.devideWords(sentence);  //将sentence分成keyword，otherword和textword

        //特征词处理
        for(String keyword : wp.getKeyWords())
        {
            Map<String,Double> relatedWords = wt.getRelatedWords(keyword);
            for(String word : relatedWords.keySet()) {
                if (!weightedTermMap.containsKey(word))
                	weightedTermMap.put(word, 0.0);
                
                double cwt = relatedWords.get(word); //获取相关分值
                cwt += weightedTermMap.get(word) + cwt - weightedTermMap.get(word) * cwt ;  //将分值与现有分值整合
                weightedTermMap.put(word, cwt);
            }
        }

        //一般词处理
        for(String t : wp.getOtherWords())
        {
        	weightedTermMap.put(t,1.0);
        }
        
        //整体词处理
        for(String t : wp.getTextWords())
        {
        	weightedTermMap.put(t,1.0);
        }
        
        return weightedTermMap;
    }


}
