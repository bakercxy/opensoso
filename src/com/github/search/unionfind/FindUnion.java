package com.github.search.unionfind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindUnion {
	
	static double  PROP = 0.75;
	
	// 查找map中是否有input的关键字，如果有，返回数组下标，也就是key的值，如果没有返回－1
	public int findIndex(Map<String,Integer> map, String searchWord) {

		for (Object key : map.keySet()) {
			// key
			if(searchWord.equals(key)){
				return map.get(key);
			}
		}
		return -1;
	}

	public List<String> findChildren(List<TreeNode> treeList, Map map, String searchWord) {
		//System.out.println("find Children");
		int index = findIndex(map, searchWord);
//		 System.out.println("在树中的"+searchWord+"其node在List中的index："+index);
		
		TreeNode indexNode = treeList.get(index);
		
		// System.out.println("node name "+node.getNodeName());
//		System.out.println(searchWord+"的children为："+indexNode.getChildren());
		
		return indexNode.getChildren();

	}

	// 判断要搜索的词在不在树中
	public boolean hasSearchWord(List<TreeNode> tree, String searchWord) {
		for (int i = 0; i < tree.size(); i++) {
			if (tree.get(i).getNodeName().equals(searchWord)) {
				return true;
			}
		}
		return false;
	}

	// 找searchWord的衰减系数
	public Map<String,Double> getWordProp(String searchWord,BuildTree bt) {
		
		Map<String,Double> resultMap = new HashMap<String, Double>();
		
		if(bt.getTree().containsKey(searchWord))
		{
			putItsParent(searchWord,resultMap,bt);
			putItsChild(searchWord,null,0,resultMap,bt,1);  //加入三层子节点
		}
			
		return resultMap;
		
	}
	
	public void putItsChild(String node,String parent,int level,Map<String,Double> resultMap,BuildTree bt,double score){
		
		if(bt.getPairScore().containsKey(node+":"+parent))
			score = score * bt.getPairScore().get(node+":"+parent);
		
		resultMap.put(node, Math.pow(PROP, level) * score);
//		System.out.println(node + ", child, " + level + ", " + Math.pow(PROP, level) * score ) ;
		if(level >= 3)
			return;
		else
			for(String s : bt.getTree().get(node).getChildren())
				putItsChild(s,node,level+1,resultMap,bt,score);
	}
	
	public void putItsParent(String node,Map<String,Double> resultMap,BuildTree bt){
		
		if(bt.getTree().get(node).getParent() != null)  //如果父节点存在，加入父节点
		{
			String parent = bt.getTree().get(node).getParent();
			double score = 1.0;
			if(bt.getPairScore().containsKey(node+":"+parent))
				score = bt.getPairScore().get(node+":"+parent);
			resultMap.put(parent, Math.pow(PROP, 1) * score);
///			System.out.println(parent + ", parent, " + -1 + ", " + Math.pow(PROP, 1) * score ) ;
			
//			for(String s: bt.getTree().get(parent).getChildren())  //对于node节点的兄弟进行遍历
//			{
//				if(!s.equals(node))  //如果不是节点自己
//				{
//					double subScore = 1.0;
//					if(bt.getPairScore().containsKey(s+":"+parent))
//						subScore = bt.getPairScore().get(s+":"+parent);
//					resultMap.put(s, 0.2 * score * subScore);
////					System.out.println(s + ", brother, " + 0 + ", " + Math.pow(PROP, 2) * score * subScore ) ;
//				}
//			}
			
		}
	}

//	//获得单个在树中的单词的表
//	public Map getSubResult(List tree, String searchWord, Map map) {
//		
//		//System.out.println("get Sub Result");
//		
//		// subResult存储的是搜索关键字和衰减系数
//		Map subResult = new HashMap();
//		
//		subResult.put(searchWord, Math.pow(0.8, 0));
////        System.out.println(searchWord+"的衰减系数为："+Math.pow(0.8, 0));
//        //System.out.println(searchWord+"的subresultmap："+subResult);
//		// 此处应先判断searchWord在tree中
//
//		List<String> children = findChildren(tree, map, searchWord);
////		 System.out.println("SearchWord"+searchWord+"的孩子：" + children);
////		 System.out.println("children.isEmpty的值："+children.isEmpty());
//		
//
//		if (children.isEmpty()) {
//			
////			System.out.println("searchWord"+searchWord+"的subresult："+searchWord);
//			return subResult;
//			
//		}else{
//
//			for (int i = 0; children.size() > 0; i++) {
//
//				// 得到children的衰减系数
//				int value = findCoefficient(tree, children.get(0))- findCoefficient(tree, searchWord);
//                //System.out.println("children.get(0):"+children.get(0));
//                //System.out.println(value);
//				// 把每一个searchWord的子节点和衰减系数填入map中
//				subResult.put(children.get(0), Math.pow(0.8, value));
//
//				// 将有children的放在list里面，以后继续遍历其字节点
//				List subChildren = fu.findChildren(tree, map, children.get(0));
////				System.out.println("children:"+children.get(0)+"的subchildren"+subChildren);
//
//				for (int j = 0; j < subChildren.size(); j++) {
//
//					if (subChildren.get(j) != null) {
//
//						;
//
//					} else {
//						subChildren.remove(j);
//						// System.out.println("删除null以后："+subChildren);
//					}
//
//				}
//
//				children.addAll(subChildren);
//
//				// System.out.println("加入孩子的孩子以后：" + children);
//
//				children.remove(0);
//
//				// System.out.println("删除孩子以后" + children);
//			}
////			System.out.println("searchWord"+searchWord+"的subresult："+searchWord);
//			return subResult;
//		}
//		}

		
		
		
	}

	
