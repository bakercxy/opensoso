package com.github.search.unionfind;


import java.util.ArrayList;
import java.util.List;

public class TreeNode {
   
	private String nodeName;
    private String parent;
	private List<String> children;
    //private int coefficient;
    
    public TreeNode(String nodeName,String parent, List<String> children ,int coefficient){
    	this.parent = parent;
    	this.children = children;
    	this.nodeName = nodeName; 
    	//this.coefficient = coefficient;
    	
    }
    
    public TreeNode(String nodeName){
    	this.nodeName = nodeName; 
    	children = new ArrayList<String>();
    }

    public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public void setChildren(List<String> children) {
		this.children = children;
	}

	public String getParent() {
        return parent;
    } 
    
    public List<String> getChildren() {
    	return children;
    }
    
    public String getNodeName() {
    	return nodeName;
    }
    
    public void addChildren(String child) {
    	children.add(child);
	}
   
   
}