package com.github.resource;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import Lab1.FileUtil;
import edu.sjtu.util.Path;

/**
 * Created by BakerCxy on 2015/11/17.
 */
@Component("scoresave")
public class ScoreSaveRunnable implements Runnable{

	public static List<String> sosoList;
	public static  List<String> githubList;
	
	public static int MIN = 0;
	public static int MAX = 50;
	FileUtil fu;

    public ScoreSaveRunnable(){
       fu = new FileUtil();
       sosoList = Collections.synchronizedList(new ArrayList<String>());
       githubList =  Collections.synchronizedList(new ArrayList<String>());
       
//       this.run();
    }

	public void run(){
    	int count = 0;
        while(true) {
//        	System.out.println(count);
        	try {
				Thread.sleep(1000 * 60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	if(++count != 5)
        	{
        		if(githubList.size() >= MAX)
        		{
        			fu.writeFile(new File(Path.logPath + "github\\" +new Date().getTime() + ".dat"), githubList);
        			githubList.clear();
        			System.out.println("Github Save");
        		}
        		if(sosoList.size() >= MAX)
        		{
        			fu.writeFile(new File(Path.logPath + "soso\\" +new Date().getTime() + ".dat"), sosoList);
        			sosoList.clear();
        			System.out.println("SOSO Save");
        		}
        	}
        	if(count == 5)
        	{
        		if(githubList.size() > MIN)
        		{
        			fu.writeFile(new File(Path.logPath + "github\\" +new Date().getTime() + ".dat"), githubList);
        			githubList.clear();
        			System.out.println("Github Save");
        		}
        		if(sosoList.size() > MIN)
        		{
        			fu.writeFile(new File(Path.logPath + "soso\\" +new Date().getTime() + ".dat"), sosoList);
        			sosoList.clear();
        			System.out.println("SOSO Save");
        		}
        		count = 0;
        	}
        }
       
    }
}

