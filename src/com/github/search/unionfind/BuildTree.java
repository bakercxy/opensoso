package com.github.search.unionfind;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.github.util.FileUtil;
import com.github.util.ILineHandler;

@Component("buildTree")
public class BuildTree {

	private Map<String, TreeNode> tree;
	private Map<String, Integer> treeIndex;
	private List<TreeNode> treeList;
	private Map<String,Double> pairScore;

	public Map<String, Integer> getTreeIndex() {
		return treeIndex;
	}

	public void setTreeIndex(Map<String, Integer> treeIndex) {
		this.treeIndex = treeIndex;
	}

	public List<TreeNode> getTreeList() {
		return treeList;
	}

	public void setTreeList(List<TreeNode> treeList) {
		this.treeList = treeList;
	}

	public void setTree(Map<String, TreeNode> tree) {
		this.tree = tree;
	}

	public Map<String, TreeNode> getTree() {
		return tree;
	}
	
	

	public Map<String, Double> getPairScore() {
		return pairScore;
	}

	public void setPairScore(Map<String, Double> pairScore) {
		this.pairScore = pairScore;
	}

	public BuildTree() {

		tree = new HashMap<String, TreeNode>();
		treeIndex = new HashMap<String, Integer>();
		treeList = new ArrayList<TreeNode>();
		pairScore = new HashMap<String,Double>();

		FileUtil fu = new FileUtil();
		// File f = new File("./tree.res");
		// String
		// t=Thread.currentThread().getContextClassLoader().getResource("").getPath();
		// int num=t.indexOf(".metadata");
		// String path=t.substring(1,num).replace('/',
		// '\\')+"GHunter\\WebContent\\tree.res";
		InputStream filePath = this.getClass().getClassLoader()
				.getResourceAsStream("tree.res");

		List<String> l = fu.readLine(filePath);

		for (String s : l) {
			String[] split = s.split(":");
			String child = split[0];
			String parent = split[1];
			addEdge(parent, child);
		}
		
		filePath = this.getClass().getClassLoader()
				.getResourceAsStream("convince.res");
		l = fu.readLine(filePath);
		
		for (String s : l) {
			String[] split = s.split(":");
			String child = split[0];
			String parent = split[1];
			pairScore.put(child +":"+ parent, Double.parseDouble(split[2]));
		}

	}

	// 增加一条边
	public void addEdge(String parent, String child) {
		TreeNode parentNode, childNode;
		if (tree == null)
			return;

		if (!tree.containsKey(parent)) // 如果父节点不存在
		{
			parentNode = new TreeNode(parent);
			tree.put(parent, parentNode);
			treeList.add(parentNode);
			treeIndex.put(parent, treeList.size());
		} else
			parentNode = tree.get(parent);

		if (!tree.containsKey(child)) // 如果子节点不存在
		{
			childNode = new TreeNode(child);
			tree.put(child, childNode);
			treeList.add(childNode);
			treeIndex.put(child, treeList.size());
		} else
			childNode = tree.get(child);

		parentNode.addChildren(child);
		childNode.setParent(parent);
	}

}
