package edu.sjtu.test;

import java.io.File;

import Lab1.FileUtil;
import Lab1.ILineHandler;
import edu.sjtu.web.config.Path;

public class TagCount {
	public static void main(String[] args) {
		FileUtil fu = new FileUtil();
		fu.readFile(new File(Path.bigFilePath + "\\tagit.dat"), new ILineHandler() {
			@Override
			public void process(String line, int index) throws Exception {
				// TODO Auto-generated method stub
				String[] ss = line.split("\\{");
				System.out.println(ss[0].substring(0, ss[0].length()-1) + "\t" + (ss[1].split(",").length));
			}
		});
	}
}
