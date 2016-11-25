package com.github.util;

import java.util.Iterator;
import java.util.List;

import com.github.model.HotCommit;
import com.github.model.HotDes;
import com.github.model.HotLang;
import com.github.model.HotWord;


public class Freq2WeightUtil {
	public static List<HotCommit> Freq2WeightList_Commit(List<HotCommit> list,double minweight,int maxsize,int minsize){
		
		double maxweight = list.get(0).getWeight();
		double k = (double)(maxsize - minsize) / (double)(maxweight - minweight);
		double b = ((maxsize - maxweight * k) + (minsize - minweight * k)) / 2;
		
		for(Iterator<HotCommit> iter = list.iterator();iter.hasNext();)
		{
			HotCommit hotWord = iter.next();
			hotWord.setWeight(Freq2Weight3(hotWord.getWeight(),k,b));
		}
		
		return list;
	}
	
	public static List<HotLang> Freq2WeightList_Lang(List<HotLang> list,double minweight,int maxsize,int minsize){
		
		double maxweight = list.get(0).getWeight();
		double k = (double)(maxsize - minsize) / (double)(maxweight - minweight);
		double b = ((maxsize - maxweight * k) + (minsize - minweight * k)) / 2;
		
		for(Iterator<HotLang> iter = list.iterator();iter.hasNext();)
		{
			HotLang hotWord = iter.next();
			hotWord.setWeight(Freq2Weight2(hotWord.getWeight(),k,b));
		}
		
		return list;
	}
	
public static List<HotDes> Freq2WeightList_Des(List<HotDes> list,double minweight,int maxsize,int minsize){
		
		double maxweight = list.get(0).getWeight();
		double k = (double)(maxsize - minsize) / (double)(maxweight - minweight);
		double b = ((maxsize - maxweight * k) + (minsize - minweight * k)) / 2;
		
		for(Iterator<HotDes> iter = list.iterator();iter.hasNext();)
		{
			HotDes hotWord = iter.next();
			hotWord.setWeight(Freq2Weight(hotWord.getWeight(),k,b));
		}
		return list;
	}
	
	public static int Freq2Weight(double weight,double k, double b){
		int size = (int) (k * weight + b);
		return size;
	}
	
	//just for lang
	public static int Freq2Weight2(double weight,double k, double b){
		
		if(weight >= 0.2)  //75
			return 75;
		else if(weight < 0.2 && weight >= 0.1)  //60-75
			return (int) (150 * weight + 45);
		else if(weight < 0.1 && weight >= 0.05) //55-60
			return (int) (100 * weight + 50);
		else if(weight < 0.05 && weight >= 0.01) //50-55
			return (int) (125 * weight + 48.75);
		else if(weight < 0.01 && weight >= 0.005) //45-50
			return (int) (1000 * weight + 40);
		else if(weight < 0.005 && weight >= 0.001) //40-45
			return (int) (1250 * weight + 38.75);
		else if(weight < 0.001 && weight >= 0.0005) //35-40
			return (int) (10000 * weight + 30);
		else if(weight < 0.0005 && weight >= 0.0001) //30-35
			return (int) (12500 * weight + 28.75);
		else if(weight < 0.0001 && weight >= 0.00005) //25-30
			return (int) (100000 * weight + 20);
		else if(weight < 0.00005 && weight >= 0.00001) //20-25
			return (int) (125000 * weight + 18.75);
		else if(weight < 0.00001 && weight >= 0.000005) //15-20
			return (int) (1000000 * weight + 10);
		else if(weight < 0.000005 && weight >= 0.000001) //10-15
			return (int) (1250000 * weight + 8.75);
		else  //<0.000005
			return (int) (15);  //10
	}
	
	
	//just for commit
	public static int Freq2Weight3(double weight,double k, double b){
		
		if(weight >= 0.2)  //75
			return 80;
		else if(weight < 0.2 && weight >= 0.15)  //65-75
			return (int) (200 * weight + 40);
		else if(weight < 0.15 && weight >= 0.1) //55-65
			return (int) (200 * weight + 35);
		else if(weight < 0.1 && weight >= 0.05) //45-55
			return (int) (200 * weight + 28);
		else if(weight < 0.05 && weight >= 0.01) //40-45
			return (int) (125 * weight + 40.75);
		else if(weight < 0.01 && weight >= 0.005) //35-40
			return (int) (1000 * weight + 30);
		else if(weight < 0.005 && weight >= 0.001) //30-35
			return (int) (1250 * weight + 20.75);
		else if(weight < 0.001 && weight >= 0.0005) //25-30
			return (int) (10000 * weight + 12);
		else if(weight < 0.0005 && weight >= 0.0001) //20-25
			return (int) (12500 * weight + 8.75);
		else if(weight < 0.0001 && weight >= 0.00005) //15-20
			return (int) (100000 * weight + 6);
		else  //<0.000005
			return (int) (15);  //10
	}
	
}
