package com.atguigu.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: HuffmanTree    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/10/21 16:45   // 时间
 * @Version: 1.0     // 版本
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = new int[]{13,7,8,3,29,6,1};
        System.out.println("赫夫曼树的形成过程：");
        Node root = createHuffmanTree(arr);
        //测试
        System.out.println("前序遍历赫夫曼树：");
        root.preOrder();
    }

    //编写一个前序遍历的方法
    public static void preOrder(Node root){
        if (root != null){
            root.preOrder();
        }else {
            System.out.println("赫夫曼树是空树");
        }
    }

    //创建赫夫曼书树的方法
    public static Node createHuffmanTree (int[] arr){
        //第一步：
            //1.遍历arr数组
            //2.将arr的每一个元素构成一个Node
            //3.将Node放入到ArrayList中
        List<Node> nodes = new ArrayList<Node>();
        for (int value : arr){
            nodes.add(new Node(value));
        }

        //我们处理的过程是一个循环的过程
        while (nodes.size() > 1){
            //第二步：排序 从小到大
            Collections.sort(nodes);
            System.out.println(nodes);
            //第三步：取出根节点全值最小的两颗二叉树
            //1.取出权重最小的节点
            Node leftNode = nodes.get(0);
            //2.取出权重次小的节点
            Node rightNode = nodes.get(1);
            //3.构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //4.从ArrayList中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //5.将parent加入到nodes
            nodes.add(parent);
        }
        System.out.println(nodes);
        //返回赫夫曼树的root节点
        return nodes.get(0);
    }
}

//创建节点类
//为了让Node对象支持排序，让Node实现Comparable接口
class Node implements Comparable<Node>{
    int value;  //节点的权重
    Node left;  //指向左子节点
    Node right;  //指向右子节点

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }

    public Node(int value){     //构造器
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;    //实现对权值进行比较,且表示从小到大排
    }
}