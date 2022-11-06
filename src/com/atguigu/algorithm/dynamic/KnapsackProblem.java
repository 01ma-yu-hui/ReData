package com.atguigu.algorithm.dynamic;

import java.util.Arrays;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: KnapsackProblem    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/11/5 18:20   // 时间
 * @Version: 1.0     // 版本
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = new int[]{1,4,3};//对应物品的重量
        int[] val = new int[]{1500,3000,2000};//对应物品的单价
        int m = 4;//表示背包的容量
        int n = val.length;//表示物品的个数

        //创建一个二维数组，v[i][j]表示将前i件物品放入容量为j的背包的最大价值
        int[][] v = new int[n+1][m+1];
        //为了记录放入商品的情况，我们定义一个二维数组
        int[][] path = new int[n+1][m+1];

        //初始化第一行和第一列，加入0行0列的目的是为了处理第一个物品的前一个。
        for (int i = 0; i < m+1; i++) {
            v[0][i] = 0;
        }
        for (int i = 0; i < n+1; i++) {
            v[i][0] = 0;
        }

        //根据前面得到的公式来动态规划处理
        for (int i = 1; i < v.length; i++) {//不处理第一行,i是从1开始的
            for (int j = 1; j < v[i].length; j++) {//不处理第一列，j是从1开始的
                //公式
                if (w[i - 1] > j){//因为我们的程序是从1开始的，因此原来的公式w[i]要修改为w[i-1]
                    v[i][j] = v[i-1][j];
                } else {
                    //说明：因为我们的i是从1开始的，因此公式要做一个局部的更新。
                    //v[i][j] = Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                    //为了记录商品存放到背包的情况，我们不能简单的使用上面的公式，需要使用if-else来处理
                    if (v[i-1][j] < val[i-1]+v[i-1][j-w[i-1]]){//最优的情况一定是从这里取到的
                        v[i][j] = val[i-1]+v[i-1][j-w[i-1]];
                        //把当前的情况记录到path
                        path[i][j] = 1;//做一个标记
                    } else {
                        v[i][j] = v[i-1][j];
                    }
                }
            }
        }

        //输出v，看一下效果
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j]+"  ");
            }
            System.out.println();
        }

        System.out.println("将以下物品放入背包中：");
        int i = path.length - 1;    //行的最大下标   3
        int j = path[0].length - 1; //列的最大下标   4
        while (i > 0 && j > 0){  //逆向遍历,从path的最后开始找
            if (path[i][j] == 1){
                System.out.printf("将第%d个商品放入到背包中\n",i);
                j -= w[i-1]; //计算背包的剩余容量，与背包的价值表对应起来。
            }
            i--;
        }
    }
}
