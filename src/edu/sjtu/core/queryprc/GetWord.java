package edu.sjtu.core.queryprc;

import java.util.ArrayList;
import java.util.List;

public class GetWord {

	public List<String> getWordsFromInput(String inPutString) {
	
		// 读取输入的查询语
		List<String> searchWordList = new ArrayList<String>();
					
		inPutString = inPutString.toLowerCase();
		
		//此处应将单词单个取出，并将输入全部转化成小写
		String[] temp = inPutString.split(" ");
		//将输入字符串全部转换成小写
		
		for (int i = 0; i < temp.length; i++) {
			searchWordList.add(temp[i]);
		}
		
		// 去除重复出现的单词

		for (int i = 0; i < searchWordList.size(); i++) {
			for (int j = i + 1; j < searchWordList.size(); j++) {
				if (searchWordList.get(i).equals(searchWordList.get(j))) {
					searchWordList.remove(i);
					i--;
					break;
				}
			}
		}

		return searchWordList;

	}
	
	
}