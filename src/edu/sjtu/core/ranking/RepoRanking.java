package edu.sjtu.core.ranking;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import edu.sjtu.core.queryprc.QueryExpansion;
import edu.sjtu.test.Graph;
import edu.sjtu.test.GraphByMatrix;
import edu.sjtu.web.bean.SearchRepo;
import edu.sjtu.web.util.HighLight;

/**
 * Created by BakerCxy on 2015/11/12.
 */
@Component("reporank")
public class RepoRanking {

	edu.sjtu.core.resource.Resource resource;
	QueryExpansion queryExpansion;
	Map<Integer,Double> effects;
	static double KS = 0.2, KF = 0.15, KU = 1000;

	public edu.sjtu.core.resource.Resource getResource() {
		return resource;
	}

	@Resource(name = "resource")
	public void setResource(edu.sjtu.core.resource.Resource resource) {
		this.resource = resource;
	}

	public QueryExpansion getQueryExpansion() {
		return queryExpansion;
	}

	@Resource(name = "queryExpansion")
	public void setQueryExpansion(QueryExpansion queryExpansion) {
		this.queryExpansion = queryExpansion;
	}
	

	// 计算title相似度 (0~1)
	public Map<Integer, Double> computeTitleSim(String query) {
		String[] queries = query.split("[\\s\\-_]");
		Map<Integer, Double> scores = new HashMap<Integer, Double>();

		for (String repoName : resource.getTitle().keySet()) // 对每个repo进行title打分
		{
			String lowerRepoName = repoName.toLowerCase();
			double titlesim = 0.0;
			//最小编辑距离
			double editsim = 1.0 / (double)(getLevenshteinDistance(query, lowerRepoName) + 1);
			//从属关系
			double coincidencesim = 0;
			String[] titles = lowerRepoName.split("[\\s\\-_]");
			for(String title_term : titles)
			{
				double maxSim = 0d, csim = 0d;
				for(String query_term : queries)
				{
					if(title_term.startsWith(query_term) || title_term.endsWith(query_term))			
						csim = ((double)query_term.length() / (double)title_term.length());
					else if(query_term.startsWith(title_term) || query_term.endsWith(title_term))
						csim = ((double)title_term.length() / (double)query_term.length());
					if(maxSim < csim)
						maxSim = csim;
				}
				coincidencesim += maxSim / (double)titles.length;
				System.out.println("coincidencesim: " + coincidencesim);
			}
			
			if(coincidencesim == 0)
				titlesim = 0;
			else
				titlesim = editsim * (coincidencesim + 1.0);
			if (titlesim > 0)
				scores.put(resource.getTitle().get(repoName), titlesim);
		}
		return scores;
	}
	
	// 计算特征相似度 (0~1)
	public Map<Integer, Double> computeCharacterSim(Map<String, Double> wordVec) {
//		System.out.println(wordVec.keySet());
		Map<Integer, Double> scores = new HashMap<Integer, Double>();
		Map<String, Map<Integer, Object>> tt = resource.getTt();
//		System.out.print("Tag: ");
		for (String key : wordVec.keySet()) {
			if (tt.containsKey(key))
			{
//				System.out.print(key + ", ");
				for (int id : tt.get(key).keySet()) {
					if (!scores.containsKey(id))
						scores.put(id, 0.0);
					double currentscore = scores.get(id);
					double w = 0;
					try {
						BigDecimal bd = (BigDecimal) tt.get(key).get(id);
						w = bd.doubleValue();
					} catch (Exception e) {
						Integer bd = (Integer) tt.get(key).get(id);
						w = bd.doubleValue();
					}
					
					double starscore = 1;
					
					if(effects.containsKey(id))
						starscore = effects.get(id);
					
					scores.put(id, currentscore + (wordVec.get(key) * w * (Math.log(key.split("\\s").length) * starscore + 1)));
//					System.out.println(wordVec.get(key) * w);
				}
			}
		}
		return scores;
	}

	// 计算文本相似度 (0~1)
	public Map<Integer, Double> computeTextSim(Map<String, Double> wordVec) {
		Map<Integer, Double> scores = new HashMap<Integer, Double>();
		Map<Integer,Integer> hitcount = new HashMap<Integer, Integer>();
		
		//对文档中每个词进行分析
		Map<String, Map<Integer, Object>> tw = resource.getTw();
//		System.out.print("Word: ");
		for (String key : wordVec.keySet()) {
			//判断文档的词表中是否包含这个词
			if (tw.containsKey(key))
			{
//				System.out.print(key + ", ");
				for (int id : tw.get(key).keySet()) {
//					System.out.println(id);
					if (!scores.containsKey(id))
					{
						scores.put(id, 0.0);
						hitcount.put(id, 0);
					}
					double currentscore = scores.get(id);
					hitcount.put(id, hitcount.get(id) + 1);
					
					double w = 0;
					try {
						BigDecimal bd = (BigDecimal) tw.get(key).get(id);
						w = bd.doubleValue();
					} catch (Exception e) {
						Integer bd = (Integer) tw.get(key).get(id);
						w = bd.doubleValue();
					}
					
					double starscore = 1;
					
					if(effects.containsKey(id))
						starscore = effects.get(id);
					
					scores.put(id, currentscore + wordVec.get(key) * w * starscore);
//					int length = 0;
//					
//					if(resource.getRepos().containsKey(id))  //判断是否数据集中是否存在这个仓库自标id，有个别几个id因为解析原因skip，所以导致不存在
//						if(resource.getRepos().get(id).getDes() != null)
//							length = resource.getRepos().get(id).getDes().split("\\s").length;
					
//					* (Math.log(1+length) + 1)
				}
			}
		}
		
		for(int id : scores.keySet())
		{
			scores.put(id, scores.get(id) * hitcount.get(id));
			scores.put(id, scores.get(id) * (Math.sqrt(hitcount.get(id))));
//			scores.put(id, scores.get(id) * (Math.log(hitcount.get(id)) + 1));
		}
//			

		return scores;
	}

	private void effectCompute(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String stringDate = "2015-07-31 00:00:00";
		Date curDate;
		try {
			curDate = df.parse(stringDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			curDate = new Date(115,7,31);
			e1.printStackTrace();
		}
		if(effects == null)
		{
			effects = new HashMap<Integer, Double>();
			for(int id : resource.getRepos().keySet())
			{
				Date repoDate;
				try {
					repoDate = df.parse(resource.getRepos().get(id).getDate().replace("T", " ").replace("Z", " "));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					repoDate = new Date(115,1,1);
					e.printStackTrace();
				}
				
				int daydiff = 180;
				try {
					daydiff = daysBetween(repoDate, curDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				effects.put(id, (Math.log10(1.0+Math.sqrt(1.0 + KS*resource.getRepos().get(id).getStargazers()+KF*resource.getRepos().get(id).getForks())) + 1.0)*Math.pow(2.0, -daydiff/KU));
			}
		}
	}
	
	public List<SearchRepo> rankScore(String query, int top) {
		
		System.out.print("query:" + query);
		System.out.print("  size:" + top);
		
		GraphByMatrix graph = getAnalysisGraph(top);
		
		Date begin = new Date();
		
		query = query.toLowerCase();
		Map<String, Double> wordVec = queryExpansion.getWeightedQuery(query);
		effectCompute();
		
//		Map<Integer, Double> m1 = computeCharacterSim(wordVec);
//		Map<Integer, Double> m2 = computeTextSim(wordVec);
//		Map<Integer, Double> m3 = computeTitleSim(query);
		Map<Integer, Double> m1 = zeroOne(computeCharacterSim(wordVec));
		Map<Integer, Double> m2 = zeroOne(computeTextSim(wordVec));
		Map<Integer, Double> m3 = zeroOne(computeTitleSim(query));

		RepoScore[] repoRelavences = new RepoScore[Size.Raw_Repo];
		for (int i = 0; i < Size.Raw_Repo; i++) {
			int id = i + 1;
			if(resource.getRepos().containsKey(id)){
				if(!m1.containsKey(id) && !m2.containsKey(id) && !m3.containsKey(id))
					repoRelavences[i] = new RepoScore(id,0.0);
				else
				{
//					double weight = Math.log10(1 + Math.log10(1.0 + resource.getRepos().get(id).getStargazers())) + 1.0;
//					repoScores[i] = new RepoScore(id, 
//							((1.0 + (m1.containsKey(id) ? m1.get(id): 0.0))
//							* (1.0 + (m2.containsKey(id) ? m2.get(id) : 0.0))
//							* (1.0 + (m3.containsKey(id) ? m3.get(id) : 0.0)))
//							* (starScore.containsKey(id) ? starScore.get(id) : 1.0));
					repoRelavences[i] = new RepoScore(id, 
							((1.0 + (m1.containsKey(id) ? m1.get(id): 0.0))
							* (1.0 + (m2.containsKey(id) ? m2.get(id) : 0.0))
							* (1.0 + (m3.containsKey(id) ? m3.get(id) : 0.0))));
				}
			}
			else
				repoRelavences[i] = new RepoScore(id,0.0);
			
		}

		Arrays.sort(repoRelavences, new RepoScore());
		
		//基于effect再排序
		RepoScore[] repoScoresWithEffect = new RepoScore[top*2];
		for(int i = 0; i < top*2;i++)
		{
//			System.out.println("rev_"+ (i+1) +": "+repoRelavences[i].getScore());
			repoScoresWithEffect[i] = new RepoScore(repoRelavences[i].getId(),repoRelavences[i].getScore());
			int repoId = repoRelavences[i].getId();
			if(effects.containsKey(repoId))
				repoScoresWithEffect[i].setScore(repoRelavences[i].getScore()*effects.get(repoId));
		}
		Arrays.sort(repoScoresWithEffect, new RepoScore());
		
		List<SearchRepo> result = new ArrayList<SearchRepo>();
//		for (int i = 0; i < top; i++) {
//			SearchRepo r;
//			try {
//				r = (SearchRepo) resource.getRepos().get(repoScores[i].getId()).clone();
//				System.out.println((i+1) + " : " + repoScores[i].getScore());
//				result.add(HighLight.highLight(r,query));
//			} catch (CloneNotSupportedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
		for (int i = 0; i < top*2; i++) {
			SearchRepo r;
			try {
				r = (SearchRepo) resource.getRepos().get(repoScoresWithEffect[i].getId()).clone();
//				System.out.println("score_"+ (i+1) + " : " + repoScoresWithEffect[i].getScore() + " effect: "+effects.get(repoScoresWithEffect[i].getId()));
				result.add(HighLight.highLight(r,query));
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i = 0; i < top; i++)
		{
			try {
//				System.out.println("n_"+ i +"\t"+graph.Dijkstra(""+i));
				graph.Dijkstra(""+i);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Date end = new Date();
		
		System.out.println("  compute time: " + (end.getTime()-begin.getTime())/1000.0);

		return result;
	}

	//一个用于模拟图分析的方法，用于计算时间效率
	private GraphByMatrix getAnalysisGraph(int nodesize){
		Random random = new Random();
		String[] nodes = new String[nodesize];
		for(int i = 0; i< nodesize; i++)
			nodes[i] = ""+ i;
		GraphByMatrix graph = new GraphByMatrix(Graph.DIRECTED_GRAPH, Graph.ADJACENCY_MATRIX, nodes.length);
		for(String node : nodes)
			graph.addVertex(node);  
		
		for(int i = 0; i < nodesize-1;i++)
			for(int j = i+1; j < nodesize; j++)
			{
				if(random.nextBoolean())
					graph.addEdge(nodes[i],nodes[j]);
				else
					graph.addEdge(nodes[j],nodes[i]);
			}
		return graph;
	}
	
	
	
	// 计算编辑距离的方法
	public static int getLevenshteinDistance(String s, String t) {
		if (s == null || t == null) {
			throw new IllegalArgumentException("Strings must not be null");
		}

		int n = s.length(); // length of s
		int m = t.length(); // length of t

		if (n == 0) {
			return m;
		} else if (m == 0) {
			return n;
		}

		int p[] = new int[n + 1]; // 'previous' cost array, horizontally
		int d[] = new int[n + 1]; // cost array, horizontally
		int _d[]; // placeholder to assist in swapping p and d

		// indexes into strings s and t
		int i; // iterates through s
		int j; // iterates through t

		char t_j; // jth character of t

		int cost; // cost

		for (i = 0; i <= n; i++) {
			p[i] = i;
		}

		for (j = 1; j <= m; j++) {
			t_j = t.charAt(j - 1);
			d[0] = j;

			for (i = 1; i <= n; i++) {
				cost = s.charAt(i - 1) == t_j ? 0 : 1;
				// minimum of cell to the left+1, to the top+1, diagonally left
				// and up +cost
				d[i] = Math.min(Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1]
						+ cost);
			}

			// copy current distance counts to 'previous row' distance counts
			_d = p;
			p = d;
			d = _d;
		}

		// our last action in the above loop was to switch d and p, so p now
		// actually has the most recent cost counts
		return p[n];
	}
	
	
	public Map<Integer,Double> normalizeResult(Map<Integer,Double> map, double base){
				
		double total = 0;
//		System.out.println(map.size());
		for(int id : map.keySet()){
			total += map.get(id);
//			System.out.println(map.get(id));
		}
		for(int id : map.keySet())
			map.put(id, map.get(id) / total * base);
		return map;
	}
	
	
	//01化
	public Map<Integer,Double> zeroOne(Map<Integer,Double> map){
		double largest = 0;
		for(int id : map.keySet()){
			if(map.get(id) > largest)
				largest = map.get(id);
		}
		for(int id : map.keySet())
			map.put(id, map.get(id) / largest);
		return map;
	}

	/**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }    

	// public static void main(String[] args) {
	// Resource r = new Resource();
	// RepoRanking rr = new RepoRanking(r);
	// String query = "cassandra";
	// List<RepoScore> l = rr.rankScore(query, 10);
	// for(RepoScore ps : l)
	// {
	// SearchRepo sr = r.getRepos().get(ps.getId());
	// System.out.println("=========================");
	// System.out.println(sr.getId() + "\t" +
	// sr.getUserName()+"/"+sr.getRepoName() + "\t" + ps.getScore());
	// System.out.println(sr.getDes() + "\n" + sr.getUrl());
	// }
	// }
}
