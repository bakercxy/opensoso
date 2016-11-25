package com.github.search.unionfind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.mongodb.util.JSON;

public class ReadJson {
	private static Map<String, Object> Tree = new HashMap<String, Object>();
	//private static Map<String, Object> TreeArry = new HashMap<String, Object>();
	
	public ReadJson(File file){
		
	}

	public static void main(String args[]) throws FileNotFoundException {

		File file = new File("/Users/qianzhenzheng/Desktop/find_union.json");

		// 遍历data文件夹下的所有文件

		String json = ReadFile.readFile(file);

		// 只读json文件

		Map<String, Object> map = (Map<String, Object>) JSON.parse(json);
//          for (Map.Entry<String, Object> entry : map.entrySet()) {

//			// 得到键
//			String key = entry.getKey();
//			System.out.println("keyword:"+key);
//			// 得到值
//			Object value = entry.getValue();
//			System.out.println("value:"+value);
//			//Object TreeKind;
//			
//		}
		Set set =map.entrySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry node = (Map.Entry) iterator.next();
			System.out.println(node.getKey() + "/" + node.getValue());
		}
	}
}
	





