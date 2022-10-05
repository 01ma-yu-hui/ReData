package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: ShellSort    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/9/23 18:52   // 时间
 * @Version: 1.0     // 版本
 */
public class ShellSort {
    public static void main(String[] args) {
/*        int[] arr = {8,9,1,7,2,3,5,4,6,0};
        shellSort3(arr);
        System.out.println(Arrays.toString(arr));*/
        int[] arr=new int[80000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*800000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String format = simpleDateFormat.format(date1);
        System.out.println("排序前："+format);
        shellSort3(arr);
        Date date2 = new Date();
        String format1 = simpleDateFormat.format(date2);
        System.out.println("排序后："+format1);

    }

    //整理代码
    public static void shellSort2(int[] arr){
        int temp = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2){  //分组：依次是5组，每组2个，步长为5。
                                                                    // 2组，每组5个，步长为2。
                                                                    //1组，每组10个，步长为1
            for (int i = gap; i < arr.length; i++) {//遍历各组中所有的元素
                for (int j = i - gap; j >= 0 ; j -= gap) {//通过遍历依次比较同组中相邻的两个元素的值。
                    //如果当前元素大于加上步长后的那个元素，说明需要交换
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
        }


    //对交换式的希尔排序进行优化---->移动法
    public static void shellSort3(int[] arr){
        for (int gap = arr.length / 2; gap > 0; gap /= 2){//分组：依次是5组，每组2个，步长为5。
                                                            // 2组，每组5个，步长为2。
                                                            //1组，每组10个，步长为1
            for (int i = gap; i<arr.length;i++){
                int j = i;  //定义待插入的数的下标
                int tmp = arr[j];   //定义待插入的数
                if (arr[j] <arr[j-gap]) {//如果发现前一个数本身就小于后一个数，那么就不需要交换了。用于优化
                        while (j - gap >= 0 && tmp < arr[j - gap]) {
                            //移动
                            arr[j] = arr[j - gap];
                            j -= gap;
                        }
                        arr[j] = tmp;
                    }
            }
        }
    }


    //使用逐步推导的方式来编写希尔排序
    public static void shellSort(int[] arr) {
        int temp = 0;
        //希尔排序的第一轮排序
        //因为第1轮排序，是将10个数据分成了5组，每组两个
        for (int i = 5; i < arr.length; i++) {
            //遍历各组中所有的元素(共5组，每组2个)，步长为5
            for (int j = i - 5; j >= 0 ; j -= 5) {
                //如果当前元素大于加上步长后的那个元素，说明需要交换
                if (arr[j] > arr[j + 5]){
                    temp = arr[j];
                    arr[j] = arr[j+5];
                    arr[j+5] = temp;
                }
            }
        }
        System.out.println("第一轮的排序："+ Arrays.toString(arr));

        //因为第2轮排序，是将10个数据分成了2组，每组5个
        for (int i = 2; i < arr.length; i++) {
            //遍历各组中所有的元素(共2组，每组5个)，步长为2
            for (int j = i - 2; j >= 0 ; j -= 2) {
                //如果当前元素大于加上步长后的那个元素，说明需要交换
                if (arr[j] > arr[j + 2]){
                    temp = arr[j];
                    arr[j] = arr[j+2];
                    arr[j+2] = temp;
                }
            }
        }
        System.out.println("第二轮的排序："+ Arrays.toString(arr));
        //因为第3轮排序，是将10个数据分成了1组，每组10个
        for (int i = 1; i < arr.length; i++) {
            //遍历各组中所有的元素(共2组，每组10个)，步长为1
            for (int j = i - 1; j >= 0 ; j -= 1) {
                //如果当前元素大于加上步长后的那个元素，说明需要交换
                if (arr[j] > arr[j + 1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        System.out.println("第三轮的排序："+ Arrays.toString(arr));
    }
}
