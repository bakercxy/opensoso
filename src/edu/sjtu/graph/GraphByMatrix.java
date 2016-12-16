package edu.sjtu.graph;

import java.util.LinkedList;
import java.util.Queue;

public class GraphByMatrix {  
    public static final boolean UNDIRECTED_GRAPH = false;//����ͼ��־  
    public static final boolean DIRECTED_GRAPH = true;//����ͼ��־  
  
    public static final boolean ADJACENCY_MATRIX = true;//�ڽӾ���ʵ��  
    public static final boolean ADJACENCY_LIST = false;//�ڽӱ�ʵ��  
  
    public static final int MAX_VALUE = Integer.MAX_VALUE;  
    private boolean graphType;  
    private boolean method;  
    private int vertexSize;  
    private int matrixMaxVertex;  
  
    //�洢���ж�����Ϣ��һά����  
    private Object[] vertexesArray;  
    //�洢ͼ�ж���֮�������ϵ�Ķ�ά����,���ߵĹ�ϵ  
    private int[][] edgesMatrix;  
  
    // ��¼��i���ڵ��Ƿ񱻷��ʹ�  
    private boolean[] visited;  
  
    /** 
     * @param graphType ͼ�����ͣ�����ͼ/����ͼ 
     * @param method    ͼ��ʵ�ַ�ʽ���ڽӾ���/�ڽӱ� 
     */  
    public GraphByMatrix(boolean graphType, boolean method, int size) {  
        this.graphType = graphType;  
        this.method = method;  
        this.vertexSize = 0;  
        this.matrixMaxVertex = size;  
  
        if (this.method) {  
            visited = new boolean[matrixMaxVertex];  
            vertexesArray = new Object[matrixMaxVertex];  
            edgesMatrix = new int[matrixMaxVertex][matrixMaxVertex];  
  
            //��������г�ʼ���������û�б߹�����ֵΪInteger���͵����ֵ  
            for (int row = 0; row < edgesMatrix.length; row++) {  
                for (int column = 0; column < edgesMatrix.length; column++) {  
                    edgesMatrix[row][column] = MAX_VALUE;  
                }  
            }  
  
        }  
    }  
  
    /********************���·��****************************/  
    //����һ�����㵽����һ���������̾���  
    public double Dijkstra(Object obj) throws Exception {  
        return Dijkstra(getVertexIndex(obj));  
    }  
    public double Dijkstra(int v0) {
        int[] dist = new int[matrixMaxVertex];  
        int[] prev = new int[matrixMaxVertex];  
  
        //��ʼ��visited��dist��path  
        for (int i = 0; i < vertexSize; i++) {  
            //һ��ʼ�ٶ�ȡֱ��·�����  
            dist[i] = edgesMatrix[v0][i];  
            visited[i] = false;  
  
            //ֱ������µ�����ɵ���ǳ�����  
            if (i != v0 && dist[i] < MAX_VALUE)  
                prev[i] = v0;  
            else  
                prev[i] = -1; //��ֱ��·��  
        }  
  
        //��ʼʱԴ��v0��visited������ʾv0 ��v0�����·���Ѿ��ҵ�  
        visited[v0] = true;  
  
        // �������辭��һ������ת�����������,���Щ,��֤֮  
        // �ټ��辭����������ת,�����Щ,��֤֮,.....  
        // ֱ����������п��ܵ���ת��  
        int minDist;  
        int v = 0;  
        for (int i = 1; i < vertexSize; i++) {  
            //��һ������������ɵ�,�±�װ�� v  
            minDist = MAX_VALUE;  
  
            for (int j = 0; j < vertexSize; j++) {  
                if ((!visited[j]) && dist[j] < minDist) {  
                    v = j;                             // ���ɶ���j��ת��������  
                    minDist = dist[j];  
                }  
            }  
            visited[v] = true;  
  
            /*����v����S����v0����v��������·��Ϊmin. 
              �ٶ���v0��v������vֱ��������㣬���µ�ǰ���һ�����ɵ㼰����*/  
            for (int j = 0; j < vertexSize; j++) {  
                if ((!visited[j]) && edgesMatrix[v][j] < MAX_VALUE) {  
  
                    if (minDist + edgesMatrix[v][j] <= dist[j]) {  
                        //����ྭ��һ��v�㵽��j��� ���·������Ҫ��,�͸���  
                        dist[j] = minDist + edgesMatrix[v][j];  
  
                        prev[j] = v;                    //���ɵ�����  
                    }  
  
                }  
            }  
  
        }  
  
        double score = 0;
        for (int i = 0; i < matrixMaxVertex; i++) {
        	if(i != v0)
        	{
//        		System.out.println("**" + vertexesArray[v0] + "-->" +vertexesArray[i] + " �����·���ǣ�" + (dist[i]>10000 || dist[i]<-10000?"-":dist[i]));
                if(dist[i]<10000 && dist[i]>-10000)
                	score += 1.0 / (double)dist[i];
        	}
        }  
        return score;
    }  
  
    //��ȡ����ֵ���������Ӧ������  
    private int getVertexIndex(Object obj) throws Exception {  
        int index = -1;  
        for (int i = 0; i < vertexSize; i++) {  
            if (vertexesArray[i].equals(obj)) {  
                index = i;  
                break;  
            }  
        }  
        if (index == -1) {  
            throw new Exception("û�����ֵ��");  
        }  
  
        return index;  
    }  
  
    /** 
     * ��Դ���·���㷨�����ڼ���һ���ڵ㵽����!!���нڵ�!!�����·�� 
     */  
    public void Dijkstra2(int v0) {  
        // LinkedListʵ����Queue�ӿ� FIFO  
        Queue<Integer> queue = new LinkedList<Integer>();  
        for (int i = 0; i < vertexSize; i++) {  
            visited[i] = false;  
        }  
  
        //���ѭ����Ϊ��ȷ��ÿ�����㶼��������  
        for (int i = 0; i < vertexSize; i++) {  
            if (!visited[i]) {  
                queue.add(i);  
                visited[i] = true;  
  
                while (!queue.isEmpty()) {  
                    int row = queue.remove();  
                    System.out.print(vertexesArray[row] + "-->");  
  
                    for (int k = getMin(row); k >= 0; k = getMin(row)) {  
                        if (!visited[k]) {  
                            queue.add(k);  
                            visited[k] = true;  
                        }  
                    }  
  
                }  
            }  
        }  
    }  
  
    private int getMin( int row) {  
        int minDist = MAX_VALUE;  
        int index = 0;  
        for (int j = 0; j < vertexSize; j++) {  
            if ((!visited[j]) && edgesMatrix[row][j] < minDist) {  
                minDist = edgesMatrix[row][j];  
                index = j;  
            }  
        }  
        if (index == 0) {  
            return -1;  
        }  
        return index;  
    }  
  
    public boolean addVertex(Object val) {  
        assert (val != null);  
        vertexesArray[vertexSize] = val;  
        vertexSize++;  
        return true;  
    }  
  
    public boolean addEdge(Object vobj1, String vobj2) {
    	int vnum1 = 0, vnum2 = 0;
		try {
			vnum1 = getVertexIndex(vobj1);
			vnum2 = getVertexIndex(vobj2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return addEdge(vnum1, vnum2, 1);
    }  
    
    
    public boolean addEdge(int vnum1, int vnum2) {  
        assert (vnum1 >= 0 && vnum2 >= 0 && vnum1 != vnum2);  
        return addEdge(vnum1, vnum2, 1);
    }  
    
    public boolean addEdge(int vnum1, int vnum2, int weight) {  
        assert (vnum1 >= 0 && vnum2 >= 0 && vnum1 != vnum2 && weight >= 0);  
  
        //����ͼ  
        if (graphType) {  
            edgesMatrix[vnum1][vnum2] = weight;  
  
        } else {  
            edgesMatrix[vnum1][vnum2] = weight;  
            edgesMatrix[vnum2][vnum1] = weight;  
        }  
  
        return true;  
    }  
  
}  