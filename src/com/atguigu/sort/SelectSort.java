package com.atguigu.sort;

import sun.plugin.javascript.navig.Array;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: SelectSort    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/9/21 17:26   // 时间
 * @Version: 1.0     // 版本
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*8000);
        }
        //System.out.println("排序前:"+Arrays.toString(arr));
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间为："+date1Str);
        selectSort(arr);
        //System.out.println("排序后:"+Arrays.toString(arr));   //Arrays.toString(arr):用于输出数组
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序前的时间为："+date2Str);
    }


    public static void selectSort(int[] arr){
        //时间复杂度为O(n^2)
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;   //假定该位为最小的索引
            int min = arr[minIndex];    //假定该位为最小的值i
            for (int j = 1 + i; j < arr.length; j++) {
                if (min > arr[j]){  //说明假定的最小值并不是最小值
                    min = arr[j];   //重置最下值
                    minIndex = j;   //重置下标
                }
            }   //第一轮for循环后得到第一轮最小值和下标索引
            if (minIndex != i){ //只有在下标改变的情况下进行交换
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
