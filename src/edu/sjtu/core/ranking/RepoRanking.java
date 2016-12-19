package edu.sjtu.core.ranking;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import edu.sjtu.core.queryprc.QueryExpansion;
import edu.sjtu.graph.Graph;
import edu.sjtu.graph.GraphByMatrix;
import edu.sjtu.web.bean.SearchRepo;
import edu.sjtu.web.util.HighLight;

/**
 * Created by BakerCxy on 2015/11/12.
 */
@Component("reporank")
public class RepoRanking {

	edu.sjtu.core.resource.Resource resource;
	QueryExpansion queryExpansion;
	RankingModel rankingModel;
	
	public RepoRanking(edu.sjtu.core.resource.Resource resource,
			QueryExpansion queryExpansion) {
		super();
		this.resource = resource;
		this.queryExpansion = queryExpansion;
	}

	public RepoRanking() {
		super();
	}

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

	public RankingModel getRankingModel() {
		return rankingModel;
	}

	@Resource(name = "rankingmodel")
	public void setRankingModel(RankingModel rankingModel) {
		this.rankingModel = rankingModel;
	}

	// 计算title相似度 (0~1)
	public Map<Integer, Double> computeTitleSim(String query) {
		Map<Integer, Double> scores = new HashMap<Integer, Double>();
		double max = 0d;
		for (int repoid : resource.getRepos().keySet()) {
			String lowerRepoName = resource.getRepos().get(repoid)
					.getReponame().toLowerCase();
			double titlesim = 0.0;
			// 最小编辑距离
			double editsim = 1.0 / (double) (getLevenshteinDistance(query,
					lowerRepoName) + 1);
			// 从属关系
			double coincidencesim = getMutualCoverage(lowerRepoName, query);

			if (coincidencesim != 0) {
				titlesim = Math.sqrt(editsim) * (coincidencesim + 1.0);
				scores.put(repoid, titlesim);
				if (titlesim > max)
					max = titlesim;
			}
		}
		
		return scores;
	}

	// 计算编辑距离的方法
	private static int getLevenshteinDistance(String s, String t) {
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

	private double getMutualCoverage(String title, String query) {
		double mcsim = 0;
		String[] titles = title.split("[\\s\\-_]");
		String[] queries = query.split("[\\s\\-_]");
		for (String title_term : titles) {
			double maxSim = 0d, csim = 0d;
			for (String query_term : queries) {
				if (title_term.startsWith(query_term)
						|| title_term.endsWith(query_term)
						|| title_term.contains(query_term))
					csim = ((double) query_term.length() / (double) title_term
							.length());
				else if (query_term.startsWith(title_term)
						|| query_term.endsWith(title_term)
						|| query_term.contains(title_term))
					csim = ((double) title_term.length() / (double) query_term
							.length());
				if (maxSim < csim)
					maxSim = csim;
			}
			mcsim += maxSim / (double) titles.length;
		}
		return mcsim;
	}

	// 计算特征相似度 (0~1)
	public Map<Integer, Double> computeCharacterSim(Map<String, Double> wordVec) {
		// System.out.println(wordVec.keySet());
		Map<Integer, Double> scores = new HashMap<Integer, Double>();
		Map<String, Map<Integer, Object>> tt = resource.getTt();
		// System.out.print("Tag: ");
		for (String key : wordVec.keySet()) {
			if (tt.containsKey(key)) {
				// System.out.print(key + ", ");
				for (int id : tt.get(key).keySet()) {
					if (!scores.containsKey(id))
						scores.put(id, 0.0);
					double currentscore = scores.get(id);
					double repotagweight = 0;
					try {
						BigDecimal bd = (BigDecimal) tt.get(key).get(id);
						repotagweight = bd.doubleValue();
					} catch (Exception e) {
						Integer bd = (Integer) tt.get(key).get(id);
						repotagweight = bd.doubleValue();
					}

					// double starscore = 1;
					// if(effects.containsKey(id))
					// starscore = effects.get(id);

					// scores.put(id, currentscore + (wordVec.get(key) * w *
					// (Math.log(key.split("\\s").length) * starscore + 1)));
					scores.put(id, currentscore + (wordVec.get(key) * repotagweight * Math.log(key.split("\\s").length + 1)));
					
				}
			}
		}
		
		return scores;
	}

	// 计算文本相似度 (0~1)
	public Map<Integer, Double> computeTextSim(Map<String, Double> wordVec) {
		Map<Integer, Double> scores = new HashMap<Integer, Double>(); // 仓库id到文本相关度的评分
		Map<Integer, Integer> hitcount = new HashMap<Integer, Integer>(); // 仓库id到该仓库命中多少个关键字的特征

		// 对文档中每个词进行分析
		Map<String, Map<Integer, Object>> tw = resource.getTw(); // 获取word索引
		for (String key : wordVec.keySet()) {
			// 判断文档的词表中是否包含这个词
			if (tw.containsKey(key)) {
				// System.out.print(key + ", ");
				for (int id : tw.get(key).keySet()) {
					// System.out.println(id);
					if (!scores.containsKey(id)) {
						scores.put(id, 0.0);
						hitcount.put(id, 0);
					}
					double currentscore = scores.get(id);
					hitcount.put(id, hitcount.get(id) + 1);

					double repowordweight = 0;
					try {
						BigDecimal bd = (BigDecimal) tw.get(key).get(id);
						repowordweight = bd.doubleValue();
					} catch (Exception e) {
						Integer bd = (Integer) tw.get(key).get(id);
						repowordweight = bd.doubleValue();
					}

					// double starscore = 1;
					// if(effects.containsKey(id))
					// starscore = effects.get(id);

					scores.put(id, currentscore + wordVec.get(key)
							* repowordweight);
					// int length = 0;
					//
					// if(resource.getRepos().containsKey(id))
					// //判断是否数据集中是否存在这个仓库自标id，有个别几个id因为解析原因skip，所以导致不存在
					// if(resource.getRepos().get(id).getDes() != null)
					// length =
					// resource.getRepos().get(id).getDes().split("\\s").length;

					// * (Math.log(1+length) + 1)
				}
			}
		}

		for (int id : scores.keySet()) {
			// scores.put(id, scores.get(id) * hitcount.get(id));
			scores.put(id, scores.get(id) * (Math.sqrt(hitcount.get(id))));
			// scores.put(id, scores.get(id) * (Math.log(hitcount.get(id)) +
			// 1));
		}
		//
		
		return scores;
	}

	public double[] getFeatures(String query, int id1, int id2) {
		double[] features = new double[12];
		query = query.toLowerCase();
		Map<String, Double> wordVec = queryExpansion.getWeightedQuery(query);

		Map<Integer, Double> tagVec = zeroOne(computeCharacterSim(wordVec));
		Map<Integer, Double> textVec = zeroOne(computeTextSim(wordVec));
		Map<Integer, Double> titleVec = zeroOne(computeTitleSim(query));

		features[0] = (titleVec.containsKey(id1) ? titleVec.get(id1) : 0d); // id1项目的标题相似度
		features[1] = (titleVec.containsKey(id2) ? titleVec.get(id2) : 0d); // id2项目的标题相似度

		features[2] = (textVec.containsKey(id1) ? textVec.get(id1) : 0d); // id1项目的文本相似度
		features[3] = (textVec.containsKey(id2) ? textVec.get(id2) : 0d); // id2项目的文本相似度

		features[4] = (tagVec.containsKey(id1) ? tagVec.get(id1) : 0d); // id1项目的标签相似度
		features[5] = (tagVec.containsKey(id2) ? tagVec.get(id2) : 0d); // id2项目的标签相似度

		features[0] = features[0] - features[1];
		features[1] = features[2] - features[3];
		features[2] = features[4] - features[5];

		if (edu.sjtu.core.resource.Resource.KS * resource.getRepos().get(id1).getStargazers() - edu.sjtu.core.resource.Resource.KS
				* resource.getRepos().get(id2).getStargazers() > 0)
			features[3] = 1d;
		else if (edu.sjtu.core.resource.Resource.KS * resource.getRepos().get(id1).getStargazers() - edu.sjtu.core.resource.Resource.KS
				* resource.getRepos().get(id2).getStargazers() < 0)
			features[3] = -1d;
		else
			features[3] = 0d;

		if (edu.sjtu.core.resource.Resource.KF * resource.getRepos().get(id1).getForks() - edu.sjtu.core.resource.Resource.KF
				* resource.getRepos().get(id2).getForks() > 0)
			features[4] = 1d;
		else if (edu.sjtu.core.resource.Resource.KF * resource.getRepos().get(id1).getForks() - edu.sjtu.core.resource.Resource.KF
				* resource.getRepos().get(id2).getForks() < 0)
			features[4] = -1d;
		else
			features[4] = 0d;

		if (Math.pow(2.0, -resource.getDayDiff(id1) / edu.sjtu.core.resource.Resource.KU)
				- Math.pow(2.0, -resource.getDayDiff(id2) / edu.sjtu.core.resource.Resource.KU) > 0)
			features[5] = 1d;
		else if (Math.pow(2.0, -resource.getDayDiff(id1) / edu.sjtu.core.resource.Resource.KU)
				- Math.pow(2.0, -resource.getDayDiff(id2) / edu.sjtu.core.resource.Resource.KU) < 0)
			features[5] = -1d;
		else
			features[5] = 0d;

		int desLen1 = 0, desLen2 = 0;
		if (resource.getRepos().get(id1).getDes() != null)
			desLen1 = resource.getRepos().get(id1).getDes().split("[\\s\\-_]").length;
		if (resource.getRepos().get(id2).getDes() != null)
			desLen2 = resource.getRepos().get(id2).getDes().split("[\\s\\-_]").length;
		if (desLen1 > desLen2)
			features[6] = (desLen1 - desLen2) / (double) desLen1;
		else if (desLen1 < desLen2)
			features[6] = -(desLen2 - desLen1) / (double) desLen2;
		else
			features[6] = 0d;

		int nameLen1 = resource.getRepos().get(id1).getReponame().length();
		int nameLen2 = resource.getRepos().get(id2).getReponame().length();
		if (nameLen1 > nameLen2) // 项目名称字符串长度 若r_a的长度大于r_b，则设置为1，反之为0。
			features[7] = (nameLen1 - nameLen2) / (double) nameLen1;
		else if (nameLen1 < nameLen2)
			features[7] = -(nameLen2 - nameLen1) / (double) nameLen2;
		else
			features[7] = 0d;

		String repoName1 = resource.getRepos().get(id1).getReponame()
				.toLowerCase();
		String repoName2 = resource.getRepos().get(id2).getReponame()
				.toLowerCase();
		double ed1 = 1.0 / (double) (getLevenshteinDistance(query, repoName1) + 1);
		double ed2 = 1.0 / (double) (getLevenshteinDistance(query, repoName2) + 1);
		features[8] = ed1 - ed2;
		features[9] = getMutualCoverage(repoName1, query)
				- getMutualCoverage(repoName2, query);

		String[] queries = query.split("[\\s\\-_]");
		features[10] = 0d; // id1项目的文档关键字交集
		features[11] = 0d; // id2项目的文档关键字交集

		for (String keyword : queries) {
			if (resource.getTw().containsKey(keyword)) {
				if (resource.getTw().get(keyword).containsKey(id1))
					features[10] += (1 / (double) queries.length);
				if (resource.getTw().get(keyword).containsKey(id2))
					features[10] -= (1 / (double) queries.length);
			}
			if (resource.getTt().containsKey(keyword)) {
				if (resource.getTt().get(keyword).containsKey(id1))
					features[11] += (1 / (double) queries.length);
				if (resource.getTt().get(keyword).containsKey(id2))
					features[11] -= (1 / (double) queries.length);
			}
		}

		return features;
	}

	public double[] getFeatures(String query, Map<Integer, Double> tagVec,
			Map<Integer, Double> textVec, Map<Integer, Double> titleVec,
			int id1, int id2) {
		double[] features = new double[12];
		query = query.toLowerCase();

		features[0] = (titleVec.containsKey(id1) ? titleVec.get(id1) : 0d); // id1项目的标题相似度
		features[1] = (titleVec.containsKey(id2) ? titleVec.get(id2) : 0d); // id2项目的标题相似度

		features[2] = (textVec.containsKey(id1) ? textVec.get(id1) : 0d); // id1项目的文本相似度
		features[3] = (textVec.containsKey(id2) ? textVec.get(id2) : 0d); // id2项目的文本相似度

		features[4] = (tagVec.containsKey(id1) ? tagVec.get(id1) : 0d); // id1项目的标签相似度
		features[5] = (tagVec.containsKey(id2) ? tagVec.get(id2) : 0d); // id2项目的标签相似度

		features[0] = features[0] - features[1];
		features[1] = features[2] - features[3];
		features[2] = features[4] - features[5];

		if (edu.sjtu.core.resource.Resource.KS * resource.getRepos().get(id1).getStargazers() - edu.sjtu.core.resource.Resource.KS
				* resource.getRepos().get(id2).getStargazers() > 0)
			features[3] = 1d;
		else if (edu.sjtu.core.resource.Resource.KS * resource.getRepos().get(id1).getStargazers() - edu.sjtu.core.resource.Resource.KS
				* resource.getRepos().get(id2).getStargazers() < 0)
			features[3] = -1d;
		else
			features[3] = 0d;

		if (edu.sjtu.core.resource.Resource.KF * resource.getRepos().get(id1).getForks() - edu.sjtu.core.resource.Resource.KF
				* resource.getRepos().get(id2).getForks() > 0)
			features[4] = 1d;
		else if (edu.sjtu.core.resource.Resource.KF * resource.getRepos().get(id1).getForks() - edu.sjtu.core.resource.Resource.KF
				* resource.getRepos().get(id2).getForks() < 0)
			features[4] = -1d;
		else
			features[4] = 0d;

		if (Math.pow(2.0, -resource.getDayDiff(id1) / edu.sjtu.core.resource.Resource.KU)
				- Math.pow(2.0, -resource.getDayDiff(id2) / edu.sjtu.core.resource.Resource.KU) > 0)
			features[5] = 1d;
		else if (Math.pow(2.0, -resource.getDayDiff(id1) / edu.sjtu.core.resource.Resource.KU)
				- Math.pow(2.0, -resource.getDayDiff(id2) / edu.sjtu.core.resource.Resource.KU) < 0)
			features[5] = -1d;
		else
			features[5] = 0d;
		
		int desLen1 = 0, desLen2 = 0;
		if (resource.getRepos().get(id1).getDes() != null)
			desLen1 = resource.getRepos().get(id1).getDes().split("[\\s\\-_]").length;
		if (resource.getRepos().get(id2).getDes() != null)
			desLen2 = resource.getRepos().get(id2).getDes().split("[\\s\\-_]").length;
		if (desLen1 > desLen2)
			features[6] = (desLen1 - desLen2) / (double) desLen1;
		else if (desLen1 < desLen2)
			features[6] = -(desLen2 - desLen1) / (double) desLen2;
		else
			features[6] = 0d;

		int nameLen1 = resource.getRepos().get(id1).getReponame().length();
		int nameLen2 = resource.getRepos().get(id2).getReponame().length();
		if (nameLen1 > nameLen2) // 项目名称字符串长度 若r_a的长度大于r_b，则设置为1，反之为0。
			features[7] = (nameLen1 - nameLen2) / (double) nameLen1;
		else if (nameLen1 < nameLen2)
			features[7] = -(nameLen2 - nameLen1) / (double) nameLen2;
		else
			features[7] = 0d;

		String repoName1 = resource.getRepos().get(id1).getReponame()
				.toLowerCase();
		String repoName2 = resource.getRepos().get(id2).getReponame()
				.toLowerCase();
		double ed1 = 1.0 / (double) (getLevenshteinDistance(query, repoName1) + 1);
		double ed2 = 1.0 / (double) (getLevenshteinDistance(query, repoName2) + 1);
		features[8] = ed1 - ed2;
		features[9] = getMutualCoverage(repoName1, query)
				- getMutualCoverage(repoName2, query);

		String[] queries = query.split("[\\s\\-_]");
		features[10] = 0d; // id1项目的文档关键字交集
		features[11] = 0d; // id2项目的文档关键字交集

		for (String keyword : queries) {
			if (resource.getTw().containsKey(keyword)) {
				if (resource.getTw().get(keyword).containsKey(id1))
					features[10] += (1 / (double) queries.length);
				if (resource.getTw().get(keyword).containsKey(id2))
					features[10] -= (1 / (double) queries.length);
			}
			if (resource.getTt().containsKey(keyword)) {
				if (resource.getTt().get(keyword).containsKey(id1))
					features[11] += (1 / (double) queries.length);
				if (resource.getTt().get(keyword).containsKey(id2))
					features[11] -= (1 / (double) queries.length);
			}
		}

		return features;
	}

	public List<SearchRepo> rankScore(String query, int top,
			boolean useLearningRankModel) {

		System.out.print("query:\"" + query + "\"  size:" + top + "  rankmodel:" + useLearningRankModel + "  ");

		Date begin = new Date();
		query = query.toLowerCase();
		Map<String, Double> wordVec = queryExpansion.getWeightedQuery(query);

		Map<Integer, Double> tagVec = zeroOne(computeCharacterSim(wordVec));
		Map<Integer, Double> textVec = zeroOne(computeTextSim(wordVec));
		Map<Integer, Double> titleVec = zeroOne(computeTitleSim(query));

		RepoScore[] repoRelavences = new RepoScore[Size.Raw_Repo];
		for (int i = 0; i < Size.Raw_Repo; i++) {
			int id = i + 1;
			if (resource.getRepos().containsKey(id)) {
				if (!tagVec.containsKey(id) && !textVec.containsKey(id) && !titleVec.containsKey(id))
					repoRelavences[i] = new RepoScore(id, 0.0);
				else {
					repoRelavences[i] = new RepoScore(id,
							((1.0 + (tagVec.containsKey(id) ? tagVec.get(id) : 0.0)) * (1.0 + (textVec.containsKey(id) ? textVec.get(id) : 0.0)) * (1.0 + (titleVec
									.containsKey(id) ? titleVec.get(id) : 0.0))));
					
//					if(Double.isNaN(repoRelavences[i].getScore()))
//					{
//						System.out.println("rank:" + id+ " not a number!");
//						System.out.println(1.0 + (tagVec.containsKey(id) ? tagVec.get(id) : 0.0));
//						System.out.println(1.0 + (textVec.containsKey(id) ? textVec.get(id) : 0.0));
//						System.out.println(1.0 + (titleVec.containsKey(id) ? titleVec.get(id) : 0.0));
//					}
				}
			} else
				repoRelavences[i] = new RepoScore(id, 0.0);
		}
		
		// 基于相关度值对所有资源排序
		Arrays.sort(repoRelavences, new RepoScore());

		RepoScore[] repoScoresWithEffect = new RepoScore[top * 2];
		for (int i = 0; i < top * 2; i++) {
			// System.out.println("rev_"+ (i+1)
			// +": "+repoRelavences[i].getScore());
			repoScoresWithEffect[i] = new RepoScore(repoRelavences[i].getId(),
					repoRelavences[i].getScore());
			int repoId = repoRelavences[i].getId();
			if (resource.getEffects().containsKey(repoId))
				repoScoresWithEffect[i].setScore(repoRelavences[i].getScore()
						* resource.getEffects().get(repoId));
		}
		// 基于相关度和影响力综合打分对top*2的资源排序
		Arrays.sort(repoScoresWithEffect, new RepoScore());

		if (useLearningRankModel) {
			Map<String, Boolean> edge = new HashMap<String, Boolean>();
			for (int i = 0; i < top - 1; i++)
				for (int j = i + 1; j < top; j++)
					edge.put(repoScoresWithEffect[i].getId() + "-"
							+ repoScoresWithEffect[j].getId(), rankingModel
							.compare(getFeatures(query, tagVec, textVec,
									titleVec, repoScoresWithEffect[i].getId(),
									repoScoresWithEffect[j].getId())));

			GraphByMatrix graph = getAnalysisGraph(top, repoScoresWithEffect,
					edge);
			for (int i = 0; i < top; i++) {
				try {
					double rankValue = graph.Dijkstra(""
							+ repoScoresWithEffect[i].getId());
					repoScoresWithEffect[i].setRank(rankValue);
//					System.out.println("No." + i + "  id: "
//							+ repoScoresWithEffect[i].getId() + "  rankvalue: "
//							+ repoScoresWithEffect[i].getRank() + "  score: "
//							+ repoScoresWithEffect[i].getScore());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 基于排序模型对top的资源排序
			Arrays.sort(repoScoresWithEffect, new RepoScore());
		}

//		for (int i = 0; i < top; i++)
//			System.out.println("No." + i + "  id: "
//					+ repoScoresWithEffect[i].getId() + "  rankvalue: "
//					+ repoScoresWithEffect[i].getRank() + "  score: "
//					+ repoScoresWithEffect[i].getScore());

		// 返回前台
		List<SearchRepo> result = new ArrayList<SearchRepo>();
		for (int i = 0; i < top; i++) {
			SearchRepo r;
			try {
				r = (SearchRepo) resource.getRepos()
						.get(repoScoresWithEffect[i].getId()).clone();
				// System.out.println("score_"+ (i+1) + " : " +
				// repoScoresWithEffect[i].getScore() +
				// " effect: "+effects.get(repoScoresWithEffect[i].getId()));
				result.add(HighLight.highLight(r, query));
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}

		Date end = new Date();
		System.out.println("  computation time: "
				+ (end.getTime() - begin.getTime()) / 1000.0 + "s");
		return result;
	}

	// 一个用于模拟图分析的方法，用于计算时间效率
	private GraphByMatrix getAnalysisGraph(int nodesize, RepoScore[] repos,
			Map<String, Boolean> edge) {
		String[] nodes = new String[nodesize];
		for (int i = 0; i < nodesize; i++)
			nodes[i] = "" + repos[i].getId();
		GraphByMatrix graph = new GraphByMatrix(Graph.DIRECTED_GRAPH,
				Graph.ADJACENCY_MATRIX, nodes.length);
		for (String node : nodes)
			graph.addVertex(node);

		for (int i = 0; i < nodesize - 1; i++)
			for (int j = i + 1; j < nodesize; j++) {
				if (edge.get(nodes[i] + "-" + nodes[j]))
					graph.addEdge(nodes[i], nodes[j]);
				else
					graph.addEdge(nodes[j], nodes[i]);
			}
		return graph;
	}

	// private Map<Integer,Double> normalizeResult(Map<Integer,Double> map,
	// double base){
	//
	// double total = 0;
	// // System.out.println(map.size());
	// for(int id : map.keySet()){
	// total += map.get(id);
	// // System.out.println(map.get(id));
	// }
	// for(int id : map.keySet())
	// map.put(id, map.get(id) / total * base);
	// return map;
	// }

	// 01化
	private Map<Integer, Double> zeroOne(Map<Integer, Double> map) {
		double largest = 0d;
		for (int id : map.keySet()) {
			if (map.get(id) > largest)
				largest = map.get(id);
		}
		if(largest == 0d)
			return map;
		for (int id : map.keySet())
			map.put(id, map.get(id) / largest);
		return map;
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */

	// 计算两个
	public static void main(String[] args) {
		edu.sjtu.core.resource.Resource r = new edu.sjtu.core.resource.Resource();
		QueryExpansion q = new QueryExpansion(r);
		RepoRanking repoRanking = new RepoRanking(r, q);
		System.out.println(Arrays.toString(repoRanking.getFeatures("unit test",
				64212, 410673)));
	}
}
