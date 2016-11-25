package com.github.util;

import java.util.List;

public class ShowCollections {
	public  static void ShowList(String title,List<String> l){
		if(title != null)
			System.out.print(title + ": ");
		for(String s : l)
			System.out.print(s + ", ");
		System.out.println();
		
	}
}
