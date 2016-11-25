package com.github.schema;

public class Node {
	String nodeName;
	double value;
	String color;
	double x;
	double y;
	int inDegree;
	int outDegree;

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getInDegree() {
		return inDegree;
	}

	public void setInDegree(int inDegree) {
		this.inDegree = inDegree;
	}

	public int getOutDegree() {
		return outDegree;
	}

	public void setOutDegree(int outDegree) {
		this.outDegree = outDegree;
	}

	public Node(String nodeName, double value, String color, double x,
			double y) {
		super();
		this.nodeName = nodeName;
		this.value = value;
		this.color = color;
		this.x = x;
		this.y = y;
		this.inDegree = 0;
		this.outDegree = 0;
	}

	public String toString() {
		return "<node id=\""
				+ getNodeName()
				+ "\" label=\""
				+ getNodeName()
				+ "\"><attvalues><attvalue for=\"Property\" value=\"target\"></attvalue><attvalue for=\"indegree\" value=\""
				+ getInDegree() + "\"></attvalue>"
				+ "<attvalue for=\"outdegree\" value=\"" + getOutDegree()
				+ "\"></attvalue><attvalue for=\"degree\" value=\""
				+ (getInDegree() + getOutDegree())
				+ "\"></attvalue></attvalues>" + "<viz:size value=\""
				+ getValue() + "\"></viz:size><viz:position x=\"" + getX()
				+ "\" y=\"" + getY()
				+ "\" z=\"0.0\"></viz:position> <viz:color " + getColor()
				+ "></viz:color>" + "</node>";
	}

}
