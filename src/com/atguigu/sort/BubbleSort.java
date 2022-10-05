package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: BubbleSort    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/9/20 17:10   // 时间
 * @Version: 1.0     // 版本
 */
//冒泡排序的时间复杂度为O(n*n)
public class BubbleSort {
    public static void main(String[] args) {
        //测试一下冒泡排序的速度O(n^2)
            //创建一个80000个随机的数字
        int [] arr = new int[80000];
            //产生一个随机数
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*8000000);//Math.random()  产生一个0 - 1之间的double数
        }
/*        System.out.print("排序前的数组:");
        for (int i : arr) {
            System.out.print(i+"  ");
        }
        System.out.println();*/
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间为："+date1Str);
        bubbleSort(arr);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间为："+date2Str);
        //过程展示
            //第一趟排序，就是将最大的数排在最后
            /*//第二趟排序，就是将第二大的数排在倒数第二位
        for (int i = 0; i < arr.length - 2; i++) {
            //如果前面的数比后面的数大，则交换
            if (arr[i] > arr[i+1]){
                tmp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = tmp;
            }
        }
        System.out.println("第二趟排序后的数组");
        for (int i : arr) {
            System.out.println(i);
        }
            //第三趟排序，就是将第二大的数排在倒数第三位
        for (int i = 0; i < arr.length - 3; i++) {
            //如果前面的数比后面的数大，则交换
            if (arr[i] > arr[i+1]){
                tmp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = tmp;
            }
        }
        System.out.println("第三趟排序后的数组");
        for (int i : arr) {
            System.out.println(i);
        }
            //第四趟排序，就是将第二大的数排在倒数第四位
        for (int i = 0; i < arr.length - 4; i++) {
            //如果前面的数比后面的数大，则交换
            if (arr[i] > arr[i+1]){
                tmp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = tmp;
            }
        }
        System.out.println("第四趟排序后的数组");
        for (int i : arr) {
            System.out.println(i);
        }*/
    }
    //将前面的冒泡排序算法封装为一个方法：
    public static void bubbleSort(int[] arr){
        int tmp = 0;//临时变量
        boolean flag = false;//标识变量，表示是否进行过交换
        for (int i = 0; i< arr.length-1;i++) {
            for (int j = 0; j < arr.length - 1 -i; j++) {
                //如果前面的数比后面的数大，则交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
            System.out.printf("第%d趟排序为：",i+1);
            for (int i1 : arr) {
                System.out.print(i1+"   ");
            }
            System.out.println();
            if (flag == false){//在一趟排序中，一次交换都没有发生
                break;
            }else {
                flag = false;//重置flag，进行下次判断
            }
        }
    }
}
