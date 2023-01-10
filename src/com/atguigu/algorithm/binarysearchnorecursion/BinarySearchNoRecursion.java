package com.atguigu.algorithm.binarysearchnorecursion;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: BinarySearchNoRecursion    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/11/3 16:28   // 时间
 * @Version: 1.0     // 版本
 */
public class BinarySearchNoRecursion {
    public static void main(String[] args) {
        //测试。至此，两分支合并会有冲突哦！
        int[] arr = new int[]{1,3,8,10,11,67,100};
        int i = binarySearch(arr, 100);
        System.out.println(i);
    }

    //二分查找的非递归实现

    /**
     * @param arr 待查找的有序数组
     * @param target 需要查找的数
     * @return 返回对应的下标，-1表示没有找到
     */
    public static int binarySearch(int[] arr, int target){

        int left = 0;
        int right = arr.length - 1;
        while (left <= right){  //说明继续查找
            int mid = (left + right) / 2;
            if (arr[mid] == target){
                return mid;
            }else if (arr[mid] > target){//向左找
                right = mid - 1;
            }else {//向右找
                left = mid + 1;
            }
        }
        return -1;
    }

}
