package com.github.search.unionfind;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadFile {

	public static String readFile(File file) throws FileNotFoundException {

		StringBuffer sb = new StringBuffer();
		String s = "";
		BufferedReader br = new BufferedReader(new FileReader(file));

		try {
			while ((s = br.readLine()) != null) {
				sb.append(s + "\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str = sb.toString();
		return str;
		// str是你想要的东西
		// System.out.println(str);

	}

	

}
