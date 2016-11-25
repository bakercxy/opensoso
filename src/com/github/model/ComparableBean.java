package com.github.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComparableBean implements Comparable<ComparableBean>{

	private int sortindex;  //索引
	private double sortscore;

	public ComparableBean(int i,double s)
	{
		sortindex = i;
		sortscore = s;
	}
	
	

	public int getSortindex() {
		return sortindex;
	}



	public void setSortindex(int sortindex) {
		this.sortindex = sortindex;
	}



	public Double getSortscore() {
		return sortscore;
	}



	public void setSortscore(double sortscore) {
		this.sortscore = sortscore;
	}



	@Override
	public String toString() {
		return "ComparableBean [index=" + sortindex + ", score=" + sortscore + "]";
	}

	@Override
	public int compareTo(ComparableBean comparableBean) {
		return comparableBean.getSortscore().compareTo(this.getSortscore());
	}
	
	/**
	 * 测试方法
	 */
	public static void main(String[] args) {
		List comparableBeans = new ArrayList();
		
		Repo r1 = new Repo(1, 0.3);
		r1.setDescription("111");
		Repo r2 = new Repo(2, 0.6);
		r2.setDescription("222");
		Repo r3 = new Repo(3, 0.1);
		r3.setDescription("333");
		
//		ComparableBean user1 = new ComparableBean(1, 25.0);
//		ComparableBean user2 = new ComparableBean(2, 20.0);
//		ComparableBean user3 = new ComparableBean(3, 21.0);
//		comparableBeans.add(user1);
//		comparableBeans.add(user2);
//		comparableBeans.add(user3);
		
		comparableBeans.add(r1);
		comparableBeans.add(r2);
		comparableBeans.add(r3);
		
		
		Collections.sort(comparableBeans);
		for (int i = 0; i < comparableBeans.size(); i++) {
			Repo r = (Repo) comparableBeans.get(i);
			ComparableBean user = (ComparableBean) comparableBeans.get(i);
			System.out.println(r);
			System.out.println(user);
		}
	}


	
}