package com.atguigu.tree;

import java.util.Arrays;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: HeapSort    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/10/18 18:48   // 时间
 * @Version: 1.0     // 版本
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = new int[]{4,6,8,5,9};
        System.out.println("要求将数组升序排列");
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    //编写一个堆排序的方法
    public static void heapSort(int[] arr){
        System.out.println("堆排序");
        //1.将无序序列构建成一个大顶堆。
        for (int i = (arr.length - 2) / 2; i >= 0 ; i--) {  //目的是从下往上，从右往左
            adjustHeap(arr,i, arr.length);
        }
        //2.将堆顶元素与末尾元素交换，将最大元素“沉”到末尾
        //3.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
        int temp = 0;   //定义一个临时变量，用于交换
        for (int j = arr.length - 1; j > 0; j--) {
            //交换
            temp = arr[0];
            arr[0] = arr[j];
            arr[j] = temp;
            //调整
            adjustHeap(arr,0,j);
        }
    }

    //将一个数组(对应二叉树)，调整成一个大顶堆（局部）
    /**
     * @param arr  待调整的数组
     * @param i  表示非叶子节点在数组中的索引
     * @param length 表示对多少个元素进行调整,length在逐渐减少。不是数组的长度。
     */
    public static void adjustHeap(int[] arr,int i,int length){
        int temp = arr[i];  //先取出当前元素的值，保存在临时变量当中
        //开始调整
            //说明：
            //1. k = i * 2 + 1:k是i节点的左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {    //与 i = k 联合起来理解，即，交换过后，i与k要重新赋值
                                                                //注意：i始终为父节点；k始终为子节点
            if ((k + 1) < length && arr[k] < arr[k+1]){    //一定要有(k + 1) < length，因为不是每个节点都有右子节点
                                                            // 说明左子节点的值小于右子节点的值
                k++;    //k指向右子节点
            }//程序走到这里时，k指向最大的子节点
            if (arr[k] > temp){    //如果子节点大于父节点
                arr[i] = arr[k];    //把较大的值，赋给当前节点

                i = k;  //！！！！！ 目的是修改i的值，且通过heapSort()方法的第一个for循环，重新判断。因为你交换之后，你的值可能
                        //比你的子节点中的值要小，所以必须判断。
            } else {
                break;  //跳出k循环
            }
        }
        //当for循环结束后，已经将以i为父节点的树的最大值，放在了最顶（局部）
        arr[i] = temp;  //放到外面比放在里面效率高，因为可以交换的次数。
    }
}

