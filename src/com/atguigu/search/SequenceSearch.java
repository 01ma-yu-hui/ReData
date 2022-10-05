package com.atguigu.search;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: SequenceSearch    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/9/30 13:43   // 时间
 * @Version: 1.0     // 版本
 */
public class SequenceSearch {
    public static void main(String[] args) {
        int[] arr = {1,9,11,-1,34,89,1};  //无序的数组
        int index = sequenceSearch(arr, -1);
        if (index==-1){
            System.out.println("没有找到");
        }else {
            System.out.println("找到了，下标为"+index);
        }
    }

    //这里我们实现的线性查询是找到一个满足条件的值就返回
    public static int sequenceSearch(int[] arr,int value){
        //线性查找是逐一比对，发现有相同的值时，则返回下标。
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value){
                return i;
            }
        }
        return -1;
    }
}