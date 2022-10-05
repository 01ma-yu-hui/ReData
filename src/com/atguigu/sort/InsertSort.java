package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: InsertSort    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/9/22 21:28   // 时间
 * @Version: 1.0     // 版本
 */
public class InsertSort {
    public static void main(String[] args) {
        //int[] arr = {101,34,119,1};
        //insertSort2(arr);
        //System.out.println(Arrays.toString(arr));
        int[] arr=new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*800000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String format = simpleDateFormat.format(date1);
        System.out.println("排序前："+format);
        insertSort2(arr);
        Date date2 = new Date();
        String format1 = simpleDateFormat.format(date2);
        System.out.println("排序后："+format1);
    }
    //整理代码
    public static void insertSort2(int[] arr){
        for (int i = 1; i < arr.length; i++) {  //默认第一个数有序，因此从第二个数开始
            int insertVal = arr[i];   //定义待插入的数
            int insertIndex = i -1;     //找到待插入数字的前一个位置
            while (insertIndex >= 0 && insertVal < arr[insertIndex]){//如果发现待插入的数字比前一个位置的数字小，则进入循环
                arr[insertIndex+1] = arr[insertIndex];  //二者交换位置
                insertIndex--;  //指针继续向前移动，依次判断
            }
            //当循环结束，则找到待插入的位置：insertIndex+1
            //判断是否需要赋值
            if(insertIndex+1!=i){
                arr[insertIndex + 1] = insertVal;//如果待插入数字的下标没有改变，那么就不需要赋值了。
            }
        }
    }


    //插入排序
    public static void insertSort1(int[] arr){
        //使用逐步推导的方法
        //第一轮{101,34,119,1}=>{34,101,119,1}
        //定义待插入的数
        int insertVal = arr[1];
        int insertIndex = 1-1;//找到即arr[1]的前面这个数的下标
        //insertIndex>=0  给insertVal找到插入的位置,不越界
        //insertVal<arr[insertIndex]  待插入的数还没有找到位置
        //就需要将arr[insertIndex]后移
        while (insertIndex>=0&&insertVal<arr[insertIndex]){//当定义的数比前面的数大时退出
            arr[insertIndex+1] = arr[insertIndex];
            insertIndex--;//其要与前面的数字依次比较
        }
        //当退出while循环时，说明插入的位置找到:insertIndex+1
        arr[insertIndex+1] = insertVal;
        System.out.println("第一轮后："+ Arrays.toString(arr));
    }
}
