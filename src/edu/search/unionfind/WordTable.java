package edu.search.unionfind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordTable {
	
	static double gamma_c = 0.1;
	static double gamma_p = 0.2;

	Taxonomy tx;

	public WordTable(Taxonomy tx){
		this.tx = tx;
	}
	
	// 返回word在标签库中的索引，若不存在，则返回-1
	public int findIndex(String word) {
		if(tx.getTreePosIndex().containsKey(word))
			return tx.getTreePosIndex().get(word);
		else
			return -1;
	}

	public List<String> findChildren(String word) {
		int index = findIndex(word);  //查找wordindex
		TreeNode indexNode = tx.getTreeNodeList().get(index);  //根据index找到相应的node

		return indexNode.getChildren();
	}

	// 构建与word相关的所有词的权值表
	public Map<String,Double> getWordTable(String word) {
		
		Map<String,Double> resultMap = new HashMap<String, Double>();  //用于保存边权的map
		
		if(tx.getTreeNameIndex().containsKey(word))
		{
			putItsParent(word,resultMap);
			putItsChild(word,null,0,resultMap,1);  //加入三层子节点
		}
		return resultMap;
	}
	
	private void putItsChild(String node,String parent,int layer,Map<String,Double> resultMap,double score){
		
		if(tx.getEdgeCoff().containsKey(node+":"+parent))
			score *= tx.getEdgeCoff().get(node+":"+parent);
		
		resultMap.put(node, Math.pow(gamma_c, layer) * score);
//		System.out.println(node + ", child, " + level + ", " + Math.pow(PROP, level) * score ) ;
		if(layer >= 1) //搜寻1层节点
			return;
		else
			for(String s : tx.getTreeNameIndex().get(node).getChildren())
				putItsChild(s,node,layer+1,resultMap,score);
	}

	private void putItsParent(String node,Map<String,Double> resultMap) {

		if (tx.getTreeNameIndex().get(node).getParent() != null)  //如果父节点存在，加入父节点
		{
			String parent = tx.getTreeNameIndex().get(node).getParent();
			double score = 1.0;
			if (tx.getEdgeCoff().containsKey(node + ":" + parent))
				score = tx.getEdgeCoff().get(node + ":" + parent);
			resultMap.put(parent, Math.pow(gamma_p, 1) * score);

		}
	}
}

	
