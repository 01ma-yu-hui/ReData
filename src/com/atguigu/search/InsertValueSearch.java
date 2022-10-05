package com.atguigu.search;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: InsertValueSearch    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/10/3 15:42   // 时间
 * @Version: 1.0     // 版本
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i+1;
        }
        int i = insertValueSearch(arr, 0, arr.length - 1, 59);
        System.out.println(i);
    }

    /**
     * @param arr 传入的数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findVal 要找的值
     * @return 如果找到就返回对应的下标，找不到就返回-1；
     */
    //编写插值查找算法
    public static int insertValueSearch(int[] arr,int left,int right,int findVal){
        if (left > right || findVal < arr[0] || findVal > arr[arr.length-1]){
            return -1;
        }
        //求出mid
        int mid = left + (right - left) * (findVal -arr[left]) / (arr[right] - arr[left]);//自适应从mid开始查找。
        int midVal = arr[mid];
        if (findVal > midVal){
            return insertValueSearch(arr,mid + 1,right,findVal);
        } else if (findVal < midVal){
            return insertValueSearch(arr,left,mid - 1,findVal);
        } else {
            return mid;
        }
    }
}
