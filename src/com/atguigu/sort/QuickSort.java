package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: QuickSort    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/9/26 13:44   // 时间
 * @Version: 1.0     // 版本
 */
public class QuickSort {

    public static void main(String[] args) {
/*        int[] arr = {6,1,2,7,9,3,4,5,10,8};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));*/
        int[] arr=new int[80000001];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*800000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String format = simpleDateFormat.format(date1);
        System.out.println("排序前："+format);
        quickSort(arr,0, arr.length-1);
        Date date2 = new Date();
        String format1 = simpleDateFormat.format(date2);
        System.out.println("排序后："+format1);
    }

    /**
     * @param arr
     * @param left :从哪个位置开始排
     * @param right：排到哪个位置
     */
    public static void quickSort(int[] arr,int left,int right){
        //进行判断，如果左边索引比右边索引要大，是不合法的，直接使用return，结束这个方法
        if (left > right){//递归退出的条件
            return;
        }
        //定义变量保存基准数
        int base = arr[left];
        //定义变量i，指向最左边
        int i = left;
        //定义变量j，指向最右边
        int j = right;
        //当i与j不相遇的时候，在循环中进行检索
        while (i != j){
            //先由j从右往左检索比基准数小的，如果检索到比基准数小的就停下。
                    //即 如果检索到比基准数大的或等于的，就继续检索
            while (arr[j] >= base && i < j){
                j--;//j从右往左移动
            }//当跳出循环时，此时j位置的元素比基准数小

            //再由i从左往右检索比基准数大的，如果检索到比基准数大的就停下。
            //即 如果检索到比基准数小的或等于的，就继续检索
            while (arr[i] <=base && i < j){
                i++;//i从左往右
            }//当跳出循环时，此时i位置的元素比基准数大

            //交换i与j位置的元素
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        //当跳出外层while循环时 ，此时i=j
        //如果i与j相遇，则将基准数与相遇位置的数交换
            //把相遇位置的元素赋值给基准数这个位置的元素
        arr[left] = arr[i];
            //再把基准数赋值给相遇位置的元素
        arr[i] = base;
        //此时基准数左边的元素都比基准数小，右边的元素都比基准数大
        //先排基准数左边
        quickSort(arr,left,i - 1);//注意：left一直没有变，变的是i
        //再排基准数右边
        quickSort(arr,i + 1,right);//注意：right一直没有变，变的是j
    }
}






























































































































