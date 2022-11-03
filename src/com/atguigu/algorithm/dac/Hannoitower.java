package com.atguigu.algorithm.dac;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: Hannoitower    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/11/3 19:05   // 时间
 * @Version: 1.0     // 版本
 */
public class Hannoitower {
    public static void main(String[] args) {
        hannoiTower(3,'A','B','C');
    }

    //汉诺塔的移动的方法
    //使用的是分治算法

    /**
     * @param num 盘的个数
     * @param A A塔
     * @param B B塔
     * @param C C塔
     */
    public static void hannoiTower(int num, char A, char B, char C){
        //如果只有一个盘
        if (num == 1){
            System.out.println("第1个盘从"+A+"--->"+C);
        }else {
            //如果n >= 2,我们总是可以看作是两个盘：1.最下面的盘。2.上面所有的盘
            //1.先把上面所有的盘A-->B,移动的过程会使用到C
            hannoiTower(num - 1,A,C,B);
            //2.把最下面的盘从A-->C
            System.out.println("第"+num+"个盘从"+A+"-->"+C);
            //3.把B塔所有的盘从B-->C,移动的过程会使用到A
            hannoiTower(num - 1,B,A,C);
        }
    }
}
