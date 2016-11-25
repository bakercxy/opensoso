package edu.sjtu.core.taxonomy;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Lab1.FileUtil;
import Lab1.ILineHandler;
import edu.sjtu.web.config.Path;

public class Taxonomy {

	private Map<String, TreeNode> treeNameIndex;
	private Map<String, Integer> treePosIndex;
	private List<TreeNode> treeNodeList;
	private Map<String,Double> edgeCoff;

	public Map<String, TreeNode> getTreeNameIndex() {
		return treeNameIndex;
	}

	public Map<String, Integer> getTreePosIndex() {
		return treePosIndex;
	}

	public List<TreeNode> getTreeNodeList() {
		return treeNodeList;
	}


	public Map<String, Double> getEdgeCoff() {
		return edgeCoff;
	}

	public Taxonomy() {

		treeNameIndex = new HashMap<String, TreeNode>();  //保存了节点名字到节点对象的索引
		treePosIndex = new HashMap<String, Integer>();  //保存了节点名字到节点位置的索引
		treeNodeList = new ArrayList<TreeNode>();  //保存了所有节点的列表
		edgeCoff = new HashMap<String,Double>(); //保存了节点之间的权值

		FileUtil fu = new FileUtil();
		//构建树的边
		fu.readFile(new File(Path.searchPath + "tree.res"), new ILineHandler() {
			@Override
			public void process(String s, int i) throws Exception {
				String[] split = s.split(":");
				String child = split[0];  //前面的为子节点
				String parent = split[1];  //后面的为父节点
				addEdge(parent, child);  //增加边
			}
		});

		fu.readFile(new File(Path.searchPath + "convince.res"), new ILineHandler() {
			@Override
			public void process(String s, int i) throws Exception {
				String[] split = s.split(":");
				String child = split[0];
				String parent = split[1];
				edgeCoff.put(child + ":" + parent, Double.parseDouble(split[2]));
			}
		});
	}

	// 增加一条边
	public void addEdge(String parent, String child) {
		TreeNode parentNode, childNode;
		if (treeNameIndex == null)
			return;

		if (!treeNameIndex.containsKey(parent)) // 如果父节点不存在
		{
			parentNode = new TreeNode(parent);  //创建父节点
			treeNameIndex.put(parent, parentNode);
			treeNodeList.add(parentNode);
			treePosIndex.put(parent, treeNodeList.size());
		} else
			parentNode = treeNameIndex.get(parent);

		if (!treeNameIndex.containsKey(child)) // 如果子节点不存在
		{
			childNode = new TreeNode(child);
			treeNameIndex.put(child, childNode);
			treeNodeList.add(childNode);
			treePosIndex.put(child, treeNodeList.size());
		} else
			childNode = treeNameIndex.get(child);

		parentNode.addChildren(child);
		childNode.setParent(parent);
	}

}
