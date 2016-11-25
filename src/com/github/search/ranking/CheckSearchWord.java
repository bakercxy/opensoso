package com.github.search.ranking;

import java.util.List;
import java.util.Map;

import com.github.search.unionfind.TreeNode;
import com.github.search.wordsegmentation.SeparateWords;

public class CheckSearchWord {

	// 查找在tree中的词
	public List findInTreeTable(List inPutWord, Map<String,TreeNode> treemap) {
		SeparateWords sp = new SeparateWords();
		List isInTheTreeTable = sp.intersect(inPutWord, treemap);
		// 返回在树中的list
		return isInTheTreeTable;
	}

}
