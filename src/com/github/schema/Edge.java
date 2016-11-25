package com.github.schema;

public class Edge {
	public Edge(String source, String target, double weight) {
		super();
		this.source = source;
		this.target = target;
		this.weight = weight;
	}
	String source;
	String target;
	double weight;
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public String toString(){
		return "<edge source=\"" + getSource() + "\" target=\"" + getTarget() + "\" weight=\"" + getWeight() + "\"></edge>\n";
	}
}
