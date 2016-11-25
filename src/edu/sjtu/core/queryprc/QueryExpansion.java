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

	public Map<String,Double> getWeightedQuery(String sentence){

        Map<String,Double> resultMap = new HashMap<String,Double>();

        WordProcess wp = new WordProcess(resource);
        WordTable wt = new WordTable(resource.getTaxonomy());
        wp.devideWords(sentence);  //将sentence分成keyword，otherword和textword

        //特征词处理
        for(String t : wp.getKeyWords())
        {
            Map<String,Double> wordTable = wt.getWordTable(t);

            for(String w : wordTable.keySet()) {
                if (!resultMap.containsKey(w))
                    resultMap.put(w, 0.0);
                double cw = resultMap.get(w);
                cw += wordTable.get(w);
                resultMap.put(w, cw);
            }
        }

        //一般词处理
        for(String t : wp.getOtherWords())
        {
            resultMap.put(t,1.0);
        }
        
        //整体词处理
        for(String t : wp.getTextWords())
        {
            resultMap.put(t,1.0);
        }
        
        return resultMap;
    }


}
