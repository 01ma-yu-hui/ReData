package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: MergeSort    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/9/27 16:39   // 时间
 * @Version: 1.0     // 版本
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8,4,5,7,1,3,6,2};
        int[] temp = new int[arr.length];  //归并排序需要一个额外的空间开销
        mergeSort(arr,0, arr.length-1,temp);
        System.out.println(Arrays.toString(arr));
/*        int[] arr=new int[8];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*80);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String format = simpleDateFormat.format(date1);
        System.out.println("排序前："+format);

        mergeSort(arr,0, arr.length-1,temp);
        Date date2 = new Date();
        String format1 = simpleDateFormat.format(date2);
        System.out.println("排序后："+format1);*/

    }

    //分+合方法
    public static void mergeSort(int[] arr,int left,int right ,int[] temp){
        if (left < right){
            int mid = (left +right) / 2;//中间的索引
            //向左递归进行分
            mergeSort(arr,left,mid,temp);
            //向右递归进行分
            mergeSort(arr,mid + 1,right,temp);
            //到合并时
            merge(arr,left,mid,right,temp);
        }
    }

    //合并的方法
    /**
     * @param arr 需要排序的原始数组
     * @param left 左边有序序列的初试索引
     * @param mid  中间索引
     * @param right 右边有序序列的初试索引
     * @param temp 做中转的数组
     */
    public static void merge(int[] arr,int left,int mid ,int right,int[] temp){
        int i = left;   //初始化i，表示左边有序序列的初试索引
        int j = mid + 1;    //初始化j,右边有序序列的初试索引
        int t = 0;  //指向temp数组的当前索引
        //(一)先把左右两边(有序)的数据，按照规则填充到temp数组
            //直到左右两边的有序序列有一边处理完毕为止。
        while (i <= mid && j <= right){ //继续
            //如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素
            //即将左边的当前元素拷贝到temp数组
            //然后t++,i++
            if (arr[i] <= arr[j]){
                temp[t] = arr[i];
                t++;
                i++;
            } else {    //反之右边的有序序列小于左边的有序序列
                temp[t] = arr[j];
                t++;
                j++;
            }
        }
        //(二)将有剩余数据的一方的数据依次填充到temp中。
        while (i <= mid){   //左边的有序序列还有剩余的元素，就全部填充到temp
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <=right){   //右边的有序序列还有剩余的元素，就全部填充到temp
            temp[t] = arr[j];
            t++;
            j++;
        }
        //(三)将temp数组的元素拷贝到arr
            //因为每个数组都有自己独特的left 和 right索引。因此可以通过left 和 right索引来完成拷贝
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right){
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}
