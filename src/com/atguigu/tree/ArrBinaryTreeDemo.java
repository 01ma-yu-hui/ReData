package com.atguigu.tree;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: ArrBinaryTreeDemo    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/10/15 19:34   // 时间
 * @Version: 1.0     // 版本
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};
        //创建一个ArrBinaryTree
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        System.out.println("前序遍历：");
        arrBinaryTree.preOrder(0);
        System.out.println();
        System.out.println("后序遍历");
        arrBinaryTree.postOrder(0);
    }
}

//编写一个ArrBinaryTree，实现顺序存储二叉树遍历
class ArrBinaryTree{
    private int[] arr;  //存储数据节点的数组

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //遍历一个方法，完成顺序存储二叉树的前序遍历
    /**
     * @param index 表示数组的下标
     */
    public void preOrder(int index){
        if (arr == null || arr.length == 0){
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //输出当前这个节点
        System.out.print(arr[index]+"   ");
        //向左递归遍历
        if ((2*index + 1) < arr.length){
            preOrder(2*index + 1);
        }
        //向右递归
        if ((2*index + 2) < arr.length){
            preOrder(2*index + 2);
        }
    }

    //后序遍历
    public void postOrder(int index){
        if (arr == null || arr.length == 0){
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //向左递归遍历
        if ((2*index + 1) < arr.length){
            postOrder(2*index + 1);
        }
        //向右递归
        if ((2*index + 2) < arr.length){
            postOrder(2*index + 2);
        }
        //输出当前这个节点
        System.out.print(arr[index]+"   ");
    }
}