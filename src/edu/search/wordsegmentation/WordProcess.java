package edu.search.wordsegmentation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.resource.Resource;

/**
 * Created by BakerCxy on 2015/11/13.
 */
public class WordProcess {

    Resource res;
    String[] bigwords;
    String[] smallwords;
    Set<String> keyWords, otherWords,textWords;

    public WordProcess(Resource resource){
        this.res = resource;
    }

    public void devideWords(String sentence){
    	
    	sentence = sentence.toLowerCase();
        bigwords = sentence.split("\\s");
        smallwords = sentence.split("[\\s\\-_]");
    	
        keyWords = new HashSet<>();
        otherWords = new HashSet<>();
        textWords = new HashSet<>();

        for(String word : bigwords)
        {
            if(res.getTaxonomy().getTreePosIndex().get(word) != null)  //如果是关键词
            {
                keyWords.add(word);
                textWords.add(word);
            }
            else if(!res.getStopWord().contains(word))
            {
                otherWords.add(word);
                textWords.add(word);
            }
        }
        
        for(String word : smallwords)
        {
            if(res.getTaxonomy().getTreePosIndex().get(word) != null)  //如果是关键词
            {
                keyWords.add(word);
                textWords.add(word);
            }
            else if(!res.getStopWord().contains(word))
            {
                otherWords.add(word);
                textWords.add(word);
            }
        }
        
        textWords.add(sentence);
        textWords.add(sentence.replaceAll("[\\s\\-_]", " "));
        
//        System.out.println("keyword:" + keyWords);
//        System.out.println("otherWords:" + otherWords);
//        System.out.println("textWords:" + textWords);
    }

    public Set<String> getKeyWords() {
        return keyWords;
    }

    public Set<String> getOtherWords() {
        return otherWords;
    }
    
    public Set<String> getTextWords() {
        return textWords;
    }
}
