package com.github.schema;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.util.FileUtil;

public class OntologySchema {
	private String gexfString;
	
	private Map<String,Node> nodes;
	private List<Edge> edges;
	
	private FileUtil fu;
	public static String RED = "r=\"205\" g=\"0\" b=\"0\"";
	public static String BLUE = "r=\"30\" g=\"144\" b=\"255\"";
	public static String YELLOW = "r=\"255\" g=\"128\" b=\"0\"";
	public static String GREEN = "r=\"50\" g=\"205\" b=\"50\"";
	
	public OntologySchema(){
		fu = new FileUtil();
		gexfString = "";
		nodes = new HashMap<String,Node>();
		edges = new ArrayList<Edge>();
	}
	
	public void addNode(Node node){
		nodes.put(node.getNodeName(), node);
	}
	
	public void addEdge(Edge e){
		edges.add(e);
		nodes.get(e.getSource()).setOutDegree(nodes.get(e.getSource()).getOutDegree() + 1);
		nodes.get(e.getTarget()).setInDegree(nodes.get(e.getTarget()).getInDegree() + 1);
	}
	
	public String generateSchema(){
		InputStream filePath = this.getClass().getClassLoader() .getResourceAsStream("SchemaHead.res");
		
		List<String> headList = fu.readLine(filePath);
		
		for(String line : headList)
			gexfString += (line + "\n");
		
		gexfString += ("<nodes>\n");
		
		for(String node : nodes.keySet())
		{
			gexfString += nodes.get(node).toString();
		}
		
		gexfString += ("</nodes>\n <edges>\n");
		
		for(Edge edge : edges)
			gexfString += edge.toString();
		
		gexfString += ("</edges>\n</graph>\n</gexf>");
		
//		System.out.println(gexfString);
		
		return gexfString;
		
	}
}
