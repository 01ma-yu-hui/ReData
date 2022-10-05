package com.atguigu.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: BinarySearch    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/9/30 14:32   // 时间
 * @Version: 1.0     // 版本
 */
public class BinarySearch {
    public static void main(String[] args) {
        //测试一
        int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        int resIndex = binarySearch(arr,1,0,arr.length-1);
        if (resIndex != -1){
            System.out.println("值存在，且下标为："+resIndex);
        }else {
            System.out.println("值不存在");
        }
        //测试二
        /*List<Integer> integers = binarySearchFinal(arr, 1000, 0, arr.length - 1);
        for (Integer integer : integers) {
            System.out.println(integer);
        }*/
    }

    /**
     * 二分查找算法
     * @param arr 数组
     * @param findVal 要查找的值
     * @param left 左边的索引
     * @param right 右边的索引
     * @return 如果找到，则返回下标.如果没有找到，则返回-1
     */
    public static int binarySearch(int[] arr ,int findVal,int left,int right){
        //当 left > right 时，说明递归整个数组，但是没有找到。
        if (left > right){
            return -1;
        }
        int mid = (left + right) / 2;   //中间的下标索引
        int midVal = arr[mid];  //拿到中间下标索引对应的值
        if (findVal > midVal){  //要找的值比中间值大，则向右递归
            return binarySearch(arr,findVal,mid+1,right);  //向右递归：左索引=中间的索引+1，右索引不变
        }else if(findVal < midVal){ //要找的值比中间值小，则向左递归
            return binarySearch(arr,findVal,left,mid-1);    //向左递归：右索引=中间的索引-1，左索引不变
        }else {
            return mid;
        }
    }

    //二分法查找算法的高级版,即可以查找多个重复的数值
        //思路分析：
            // 1、在找到mid索引值时不要马上返回。
            // 2、向mid索引值的左边扫描，将索引满足条件的索引值 都加入到一个集合中。
            // 3、向mid索引值的右边扫描，将索引满足条件的索引值 都加入到一个集合中。
    public static List<Integer> binarySearchFinal(int[] arr , int findVal, int left, int right){
        //当 left > right 时，说明递归整个数组，但是没有找到。
        if (left > right){
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;   //中间的下标索引
        int midVal = arr[mid];  //拿到中间下标索引对应的值
        if (findVal > midVal){  //要找的值比中间值大，则向右递归
            return binarySearchFinal(arr,findVal,mid+1,right);  //向右递归：左索引=中间的索引+1，右索引不变
        }else if(findVal < midVal){ //要找的值比中间值小，则向左递归
            return binarySearchFinal(arr,findVal,left,mid-1);    //向左递归：右索引=中间的索引-1，左索引不变
        }else {
            List<Integer> resIndexList = new ArrayList<>();
            //向mid索引值的左边扫描，将索引满足条件的索引值 都加入到一个集合中。
            int temp = mid -1;
            while (true){
                if (temp < 0 ||arr[temp] != findVal){   //退出
                    break;
                }
                resIndexList.add(temp);
                temp--;
            }
            resIndexList.add(mid);
            //向mid索引值的右边扫描，将索引满足条件的索引值 都加入到一个集合中。
            temp = mid + 1;
            while (true){
                if (temp > arr.length-1 || arr[temp]!=findVal){
                    break;
                }
                resIndexList.add(temp);
                temp++;
            }
            return resIndexList;
        }
    }
}