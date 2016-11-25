package com.github.search.ranking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.search.unionfind.BuildTree;
import com.github.search.unionfind.FindUnion;
import com.github.search.unionfind.TreeNode;
import com.github.search.wordsegmentation.GetWord;
import com.github.search.wordsegmentation.SeparateWords;
import com.github.util.ShowCollections;

public class Ranking extends CheckSearchWord {

	public Map inPutWordRanking(String originInputWord, BuildTree bt) {
		FindUnion fu = new FindUnion();
		GetWord gw = new GetWord();
		CheckSearchWord hasWord = new CheckSearchWord();
		
		List<String> originWordList = gw.getWordsFromInput(originInputWord);
		
		Map<String, Double> resultMap = new HashMap<String, Double>();
		//ShowCollections.ShowList("origin",originWordList);
		
		SeparateWords sw = new SeparateWords();
		List<String> inPutWord = sw.diff(originWordList);  //去停用词
		
		//ShowCollections.ShowList("nostopword",inPutWord);
		List<String> notInTreeTable = sw.diff(inPutWord, bt.getTreeIndex());   //分离出不在树中的单词
		
		//ShowCollections.ShowList("notinTree",notInTreeTable);
		
		List<String> inTreeTable = hasWord.findInTreeTable(inPutWord, bt.getTree());   //分离出在树中的单词
		
		//ShowCollections.ShowList("inTree",inTreeTable);
		
		for (int i = 0; i < notInTreeTable.size(); i++) {
			resultMap.put((String) notInTreeTable.get(i), 0.9);
		}

		// 判断输入是否在table中，如果在，resultMap中key为单词，value为衰减系数
		for (int i = 0; !inTreeTable.isEmpty(); i++) {

		// 一个输入单词的子结果表Mapsubresult
		Map<String, Double> subR = fu.getWordProp((String) inTreeTable.get(0), bt);

			// 对于subr里面的每个结果，加到result里面

			for (String o : subR.keySet()) {
				if (resultMap.containsKey(o)) {
					// key
					resultMap.put(o, (subR.get(o) + resultMap.get(o)));
					// System.out.println(o + ":" + resultMap.get(o));

				} else {
					resultMap.put(o, subR.get(o));
					// System.out.println(o + ":" + resultMap.get(o));
				}
			}

			inTreeTable.remove(0);
			// System.out.println("移除第0个以后" + inTreeTable);
		}

		return resultMap;
	}



}
