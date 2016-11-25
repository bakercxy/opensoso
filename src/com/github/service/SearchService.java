package com.github.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.github.dao.RepoDAO;
import com.github.dao.SearchIndexDAO;
import com.github.dao.UserDAO;
import com.github.model.Index;
import com.github.model.Repo;
import com.github.model.User;
import com.github.schema.Edge;
import com.github.schema.Node;
import com.github.schema.OntologySchema;
import com.github.search.ranking.Ranking;
import com.github.search.unionfind.BuildTree;
import com.github.search.wordsegmentation.SeparateWords;
import com.github.util.ShowCollections;

@Component("searchService")
public class SearchService {

	SearchIndexDAO searchIndexDAO;
	RepoDAO repoDAO;
	UserDAO userDAO;
	BuildTree buildTree;

	public BuildTree getBuildTree() {
		return buildTree;
	}

	@Resource(name = "buildTree")
	public void setBuildTree(BuildTree buildTree) {
		this.buildTree = buildTree;
	}

	public SearchIndexDAO getSearchIndexDAO() {
		return searchIndexDAO;
	}

	@Resource(name = "searchIndexDAOImpl")
	public void setSearchIndexDAO(SearchIndexDAO searchIndexDAO) {
		this.searchIndexDAO = searchIndexDAO;
	}

	public RepoDAO getRepoDAO() {
		return repoDAO;
	}

	@Resource(name = "repoDAOImpl")
	public void setRepoDAO(RepoDAO repoDAO) {
		this.repoDAO = repoDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	@Resource(name = "userDAOImpl")
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public Map<String, Map> Search(List<String> querylist) {

		String[] wordList = new String[querylist.size()];
		for (int i = 0; i < querylist.size(); i++)
			wordList[i] = querylist.get(i);

		List<Index> indexList = searchIndexDAO.find(wordList);
		// System.out.println("list:" + indexList.size());

		Iterator<Index> iter = indexList.iterator();

		Map<String, Map> resultMap = new HashMap<String, Map>(); // 保存最终结果的map

		while (iter.hasNext()) {
			Map<String, List> keyWordMap = new HashMap<String, List>(); // 保存这个关键词的map

			Index index = iter.next();

			String keyword = index.getWord(); // 关键词

			String indexSet = index.getDocs(); // 索引集
			indexSet = indexSet.substring(1, indexSet.length() - 1);
			String[] singleResultIDSet = indexSet.split(",");

			Set<Integer> repoSet = new HashSet<Integer>();
			Set<Integer> userSet = new HashSet<Integer>();

			for (int i = 0; i < singleResultIDSet.length; i++) {
				int keyID = Integer.parseInt(singleResultIDSet[i].trim());
				if (keyID > 0)
					repoSet.add(keyID);
				else
					userSet.add(keyID);
			}

			List<Repo> repoResult = repoDAO.find(repoSet);

			keyWordMap.put("repos", repoResult);

			List<User> userResult = userDAO.find(userSet);
			keyWordMap.put("users", userResult);

			// System.out.println("word:" + keyword + " users:" +
			// userResult.size() + " repos:" + repoResult.size());

			resultMap.put(keyword, keyWordMap);
		}
		return resultMap;
	}

	public List getSearchList(String inPutString, int range) {

		List finalResult = new ArrayList();

		SeparateWords spw = new SeparateWords();

		Ranking rank = new Ranking();

		if (inPutString != null) {

			// 判断输入是否在table里,如果在，resultMap中key为单词，value为1
			// System.out.println(inWordTable);
			Map<String, Double> wordAndScore = rank.inPutWordRanking(
					inPutString, buildTree);

			for (String s : wordAndScore.keySet())
				System.out.println(s + " : " + wordAndScore.get(s));

			List<String> keywordList = new ArrayList<String>(); // 建一个保存关键词的List

			for (Object key : wordAndScore.keySet()) {
				keywordList.add((String) key);
			}

			Map<String, Map> resultMap = Search(keywordList); // 查询关键词，并将结果保存到Map

			List result = new ArrayList();

			if (range > 0) {
				for (String word : resultMap.keySet()) {

					// System.out.println(resultMap.get(word));

					Map<String, List> subResultList = resultMap.get(word); // 获取关键词“java”有关的Map
					if (subResultList.containsKey("repos")) {
						// System.out.println("in repo");
						List<Repo> repoList = subResultList.get("repos"); // 获取关键词“java”有关的repo的List
						// System.out.println("repoList:" + repoList);
						Iterator<Repo> repoIter = repoList.iterator();
						// System.out.println("repoIter.hasNext():"
						// + repoIter.hasNext());
						while (repoIter.hasNext()) // 遍历所有user,将
						{
							Repo repo = repoIter.next();
							// ComparableBean result1 = new
							// ComparableBean(repo.getIndex(),(repo.getScore() *
							// wordAndScore.get(word)));
							// System.out.println(word + " in repo :" +
							// result1);
							result.add(repo);
						}
					}
				}
			}

			if (range < 0) {

				for (String word : resultMap.keySet()) {

					// System.out.println(resultMap.get(word));

					Map<String, List> subResultList = resultMap.get(word); // 获取关键词“java”有关的Map

					if (subResultList.containsKey("users")) {
						List<User> userList = subResultList.get("users"); // 获取关键词“java”有关的user的List
						Iterator<User> userIter = userList.iterator();
						while (userIter.hasNext()) // 遍历所有user,将
						{
							User user = userIter.next();
							// ComparableBean result1 = new
							// ComparableBean(user.getIndex(),(user.getScore() *
							// wordAndScore.get(word)));
							// System.out.println(word + " in user :" +
							// result1);
							result.add(user);
						}
					}

				}
			}

			if (range == 0) {

				for (String word : resultMap.keySet()) {

					// System.out.println(resultMap.get(word));

					Map<String, List> subResultList = resultMap.get(word); // 获取关键词“java”有关的Map

					if (subResultList.containsKey("users")) {
						List<User> userList = subResultList.get("users"); // 获取关键词“java”有关的user的List
						Iterator<User> userIter = userList.iterator();
						while (userIter.hasNext()) // 遍历所有user,将
						{
							User user = userIter.next();
							// ComparableBean result1 = new
							// ComparableBean(user.getIndex(),(user.getScore() *
							// wordAndScore.get(word)));
							// System.out.println(word + " in user :" +
							// result1);
							result.add(user);
						}
					}

					if (subResultList.containsKey("repos")) {
						// System.out.println("in repo");
						List<Repo> repoList = subResultList.get("repos"); // 获取关键词“java”有关的repo的List
						// System.out.println("repoList:" + repoList);
						Iterator<Repo> repoIter = repoList.iterator();
						// System.out.println("repoIter.hasNext():"
						// + repoIter.hasNext());
						while (repoIter.hasNext()) // 遍历所有user,将
						{
							Repo repo = repoIter.next();
							// ComparableBean result1 = new
							// ComparableBean(repo.getIndex(),(repo.getScore() *
							// wordAndScore.get(word)));
							// System.out.println(word + " in repo :" +
							// result1);
							result.add(repo);
						}
					}

				}
			}

			Collections.sort(result);

			if (result.size() <= 100) {
				finalResult.addAll(result);
			} else {
				for (int i = 0; i < 100; i++) {
					finalResult.add(result.get(i));
				}
			}

		} else {

			System.out.println("Not Found");
		}

		return finalResult;

	}

	public String getSchema(String word) {

		OntologySchema onto = new OntologySchema();

		if (buildTree.getTree().containsKey(word)) {

			onto.addNode(new Node(word, 4.0, OntologySchema.RED, 0, 0));

			 //添加子节点
			int n = 0;
			final double WIDTH = 400;
			List<String> childList = getChilds(word);
			double STEP = WIDTH / ((double) childList.size() - 1);
			for (String child : childList) {
				onto.addNode(new Node(child, 2.0, OntologySchema.YELLOW, - WIDTH / 2 + STEP * n++, -100));
				onto.addEdge(new Edge(word, child, 1.0));
			}
//
			// 添加父节点
			String parent = getParent(word);
			onto.addNode(new Node(parent, 3.0, OntologySchema.BLUE, 0, 100));
			onto.addEdge(new Edge(parent, word, 1.0));
//
			n = 0;
			
			List<String> brotherList = getBrothers(word);
			STEP = WIDTH / ((double) brotherList.size() - 1);
			for (String brother : brotherList) {
				onto.addNode(new Node(brother, 1.5, OntologySchema.GREEN, - WIDTH / 2 + STEP * n++, 20));
				onto.addEdge(new Edge(parent, brother, 1.0));
			}
		}

		return onto.generateSchema();

	}

	public List<String> getChilds(String concept) {
		if (buildTree.getTree().containsKey(concept))
		{
			ShowCollections.ShowList("childs", buildTree.getTree().get(concept).getChildren());
			return buildTree.getTree().get(concept).getChildren();
		}
		else
			return null;
	}

	public String getParent(String concept) {
		if (buildTree.getTree().containsKey(concept))
		{
			System.out.println("parent: "+ buildTree.getTree().get(concept).getParent());
			return buildTree.getTree().get(concept).getParent();
		}
			
		else
			return null;
	}

	public List<String> getBrothers(String concept) {
		if (buildTree.getTree().containsKey(concept)) {
			String parent = buildTree.getTree().get(concept).getParent();
			ShowCollections.ShowList("brother", buildTree.getTree().get(parent).getChildren());
			return buildTree.getTree().get(parent).getChildren();
		} else
			return null;
	}
}
