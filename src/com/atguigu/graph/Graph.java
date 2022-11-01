package com.atguigu.graph;

import java.util.ArrayList;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: Graph    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/10/26 20:00   // 时间
 * @Version: 1.0     // 版本
 */
public class Graph {

    private ArrayList<String> vertexList;//存储顶点的集合
    private int[][] edges;//图的表示方式：邻接矩阵
    private int numOfEdges;//表示边的个数
    private boolean[] isVisited;//定义一个数组boolean[],记录某个节点是否被访问

    public static void main(String[] args) {
        //测试
        int n = 5;//节点的个数
        String[] seGi = {"A","B","C","D","E"};
        //创建图对象
        Graph graph = new Graph(n);
        //循环的添加顶点
        for (int i = 0; i < seGi.length; i++) {
            graph.insertVertex(seGi[i]);
        }
        //添加边：A-B A-C B-C B-D B-E
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        graph.showGraph();
        int numOfVertex = graph.getNumOfVertex();
        System.out.printf("该图有%d个节点\n",numOfVertex);
        int numOfEdges = graph.getNumOfEdges();
        System.out.printf("该图有%d条边\n",numOfEdges);

        //测试DFS
        System.out.println("DFS");
        graph.dfs();
    }

    //构造器
    public Graph(int n){    //n表示顶点的个数
        //初始化矩阵和ArrayList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);  //代表放n个数据进去
        numOfEdges = 0;
        isVisited = new boolean[5];//给数组进行初始化
    }

    //根据当前节点的下标得到第一个邻接节点的下标w

    /**
     * @param index 该节点的下标
     * @return 返回邻接节点的下标w，若没有邻接节点，则返回-1；
     */
    public int getFirstNeighbor(int index){
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0){//edges[index][j]>0说明，index对应的值与j对应的值邻接
                return j;
            }
        }
        return -1;
    }

    //根据当前节点和第一个邻接节点来获取当前节点的第二个邻接节点

    /**
     * @param v1 是当前节点的下标
     * @param v2 上一个邻接节点的下标
     * @return
     */
    public int getNextNeighbor(int v1,int v2){
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0){
                return j;
            }
        }
        return -1;
    }

    //深度优先(DFS)遍历算法
    //i第一次就是0

    private void dfs(boolean[] isVisited,int i){
        //首先我们访问该节点（输出）
        System.out.print(getValueByIndex(i) + "->");
        //将节点至为已访问
        isVisited[i] = true;
        //查找节点i的第一个邻接节点w
        int w = getFirstNeighbor(i);
        while (w != -1){//说明有
            //判断邻接节点有没有被访问过
            if (!isVisited[w]){
                dfs(isVisited,w);//对w进行深度优先遍历递归
            }
                //如果w节点已经被访问过,则去找当前节点的其他邻接节点
                w = getNextNeighbor(i, w);
        }
    }

    //对DFS进行一个重载,遍历我们所有的节点，并进行DFS
    public void dfs(){
        //遍历所有的节点，进行DFS[回溯]
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]){//若i没有被访问
                dfs(isVisited,i);
            }
        }
    }


    //返回节点的个数
    public int getNumOfVertex(){
        return vertexList.size();//即ArrayList集合的大小
    }

    //得到边的个数
    public int getNumOfEdges (){
        return numOfEdges;
    }

    //返回节点 下标i 对应的值
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    //返回v1与v2的权值,即判断v1与v2两个节点之间是否有连接的关系
    public int getWeigh(int v1,int v2){
        return edges[v1][v2];
    }

    //显示图对应的矩阵
    public void showGraph(){
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                System.out.print(edges[i][j]+"    ");
            }
            System.out.println();
        }
    }

    //插入顶点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    //添加边
    /**
     * @param v1 表示第一个点的下标,即是第几个顶点。
     * @param v2 表示第二个点的下标。如：描述A与B的对应关系，则A--0,B--1
     * @param weight 表示是否关联。若关联值为1，不关联值为2
     */
    public void insertEdge(int v1, int v2, int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

}
