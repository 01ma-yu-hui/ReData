package com.atguigu.sort;

import java.util.Arrays;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: RadixSort    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/9/29 15:17   // 时间
 * @Version: 1.0     // 版本
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53,3,542,748,14,214};
        radixSortFinal(arr);
    }

    //基数排序方法
    public static void radixSort(int[] arr){
        //第一轮排序(针对每个元素的个位进行排序处理)
        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
            //1.二维数组包含10个一维数组
            //2.为了防止在放入数的时候数据溢出，则每个一维数组的大小为arr.length(设置的大一点)
            //3.基数排序是空间换时间的经典算法。
        int[][] bucket =new int[10][arr.length]; //int[n][m]:表示有n个一维数组，每个一位数组中有m个元素。
        //为了记录每个桶中实际存放了多少个数据，我们定义一个一维数组来记录各个桶每次放入的数据。
            //可以这样理解，bucketElementCounts[0] ，记录的是bucket[0]桶的放入的数据个数
        int[] bucketElementCounts = new int[10];//有10个数字，分别存放了10个桶中所包含了多少个元素。

        //第一轮，针对于每个元素的个位数进行排序处理
        for (int j = 0;j < arr.length;j++){
            //取出每个元素的个位数的值
            int digitOfElement = arr[j] % 10;
            //放入到对应的桶的对应位置。
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序，依次取出数据，放入原来的数组。
        int index = 0;//定义一个下标
        //遍历每一个桶，并将桶中的数据放入到原数组
        for (int k =0; k < bucketElementCounts.length; k++){    //k < bucketElementCounts.length=10，即10个桶
                                                        //只是为了方便起见，使用bucketElementCounts变量。
            //如果桶中有数据，我们才放入到原数组
            if (bucketElementCounts[k] != 0){   //说明对应桶中是有数据的
                //则循环该桶,即第k个桶(即第K个一维数组)
                for (int l = 0; l < bucketElementCounts[k]; l++){
                    //取出元素放入到arr
                    arr[index] = bucket[k][l];
                    index++;
                }
                //第一轮处理后，需要将每个bucketElementCounts[k] = 0！！！！！
                bucketElementCounts[k] = 0;
            }
        }

        System.out.println("第一轮，对个位的排序处理:arr ="+Arrays.toString(arr));

        //第二轮的处理
        //第二轮，针对于每个元素的十位数进行排序处理
        for (int j = 0;j < arr.length;j++){
            //取出每个元素的十位数的值
            int digitOfElement = arr[j] / 10 % 10;
            //放入到对应的桶的对应位置。
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序，依次取出数据，放入原来的数组。
        index = 0;//定义一个下标
        //遍历每一个桶，并将桶中的数据放入到原数组
        for (int k =0; k < bucketElementCounts.length; k++){
            //如果桶中有数据，我们才放入到原数组
            if (bucketElementCounts[k] != 0){   //说明对应桶中是有数据的
                //则循环该桶,即第k个桶(即第K个一维数组)
                for (int l = 0; l < bucketElementCounts[k]; l++){
                    //取出元素放入到arr
                    arr[index] = bucket[k][l];
                    index++;
                }
                //第二轮处理后，需要将每个bucketElementCounts[k] = 0！！！！！
                bucketElementCounts[k] = 0;
            }
        }
        System.out.println("第二轮，对个位的排序处理:arr ="+Arrays.toString(arr));

        //第三轮的处理
        //第三轮，针对于每个元素的百位数进行排序处理
        for (int j = 0;j < arr.length;j++){
            //取出每个元素的十位数的值
            int digitOfElement = arr[j] / 100 % 10;
            //放入到对应的桶的对应位置。
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序，依次取出数据，放入原来的数组。
        index = 0;//定义一个下标
        //遍历每一个桶，并将桶中的数据放入到原数组
        for (int k =0; k < bucketElementCounts.length; k++){
            //如果桶中有数据，我们才放入到原数组
            if (bucketElementCounts[k] != 0){   //说明对应桶中是有数据的
                //则循环该桶,即第k个桶(即第K个一维数组)
                for (int l = 0; l < bucketElementCounts[k]; l++){
                    //取出元素放入到arr
                    arr[index] = bucket[k][l];
                    index++;
                }
                //第三轮处理后，需要将每个bucketElementCounts[k] = 0！！！！！
                bucketElementCounts[k] = 0;
            }
        }
        System.out.println("第三轮，对个位的排序处理:arr ="+Arrays.toString(arr));
    }

    //根据前面的推导，我们可以得到最终的基数排序的代码
    public static void radixSortFinal(int[] arr){
        //1.先得到数组中最大的数的位数
        int max = arr[0];   //假定第一位就是最大值
        for (int i = 1; i <arr.length; i++) {
            if (arr[i] > max){
                max = arr[i];
            }
        }
        //2.得到数组中最大数的位数
        String strMax = Integer.toString(max);
        int maxLength=strMax.length();
        //3.定义一个二维数组，表示10个桶，每个桶就是一个一维数组
            //1)二维数组包含10个一维数组
            //2)为了防止在放入数的时候数据溢出，则每个一维数组的大小为arr.length(设置的大一点)
            //3)基数排序是空间换时间的经典算法。
        int[][] bucket =new int[10][arr.length]; //int[n][m]:表示有n个一维数组，每个一位数组中有m个元素。
        //4.为了记录每个桶中实际存放了多少个数据，我们定义一个一维数组来记录各个桶每次放入的数据。
        //可以这样理解，bucketElementCounts[0] ，记录的是bucket[0]桶的放入的数据个数
        int[] bucketElementCounts = new int[10];//有10个数字，分别存放了10个桶中所包含了多少个元素。
        //5.这里我们使用循环将代码进行处理
        for (int i = 0 ; i < maxLength; i++) {
            //依次对元素的各位、十位、百位进行排序
            for (int j = 0;j < arr.length;j++){
                //取出每个元素对应位的值
                int pow = (int)Math.pow(10, i);
                int digitOfElement = arr[j] / pow % 10;
                //放入到对应的桶的对应位置。
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序，依次取出数据，放入原来的数组。
            int index = 0;//定义一个下标
            //遍历每一个桶，并将桶中的数据放入到原数组
            for (int k =0; k < bucketElementCounts.length; k++){    //k < bucketElementCounts.length=10，即10个桶
                //只是为了方便起见，使用bucketElementCounts变量。
                //如果桶中有数据，我们才放入到原数组
                if (bucketElementCounts[k] != 0){   //说明对应桶中是有数据的
                    //则循环该桶,即第k个桶(即第K个一维数组)
                    for (int l = 0; l < bucketElementCounts[k]; l++){
                        //取出元素放入到arr
                        arr[index] = bucket[k][l];
                        index++;
                    }
                    //第i+1轮处理后，需要将每个bucketElementCounts[k] = 0！！！！！
                    bucketElementCounts[k] = 0;
                }
            }

            System.out.println("第"+(i+1)+"轮，对个位的排序处理:arr ="+Arrays.toString(arr));
        }
    }
}
