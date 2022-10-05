package com.atguigu.recursion;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: Queen8    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/9/15 17:13   // 时间
 * @Version: 1.0     // 版本
 */
public class Queen8 {
    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义一个数组array,保存皇后放置位置的结果
    int[] array = new int[max];
    static int count = 0;
    static int judge = 0;
    public static void main(String[] args) {

        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.printf("总共有%d种解法\n",count);
        System.out.printf("总共判断了%d次",judge);
    }

    //编写一个方法，放置第n个皇后
    private void check(int n){
        if (n == max){ //n = 8 。即此时开始放置第9个皇后
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后n,放到该行的第1列
            array[n] = i;
            //判断当放置第n个皇后到i列时，是否冲突
            if (judge(n)){// 不冲突
                //接着放置n+1个皇后
                check(n+1);//产生回溯
            }
            //如果冲突，就继续执行array[n] = i+1.即将第n个皇后，放置在本行的后移的一个位置
        }
    }
    /**
     * @param n 表示第n个皇后
     * @return
     */
    //查看当我们放置第n个皇后时，就去检测该皇后和前面已经摆放的皇后冲突
    private boolean judge(int n) {
        judge++;
        for (int i = 0; i < n; i++) {
            //下面if判断表示任意两个皇后不能处于同一行、同一列、同一条斜线(利用等腰直角三角形的性质)
            if (array[i] == array[n] || Math.abs(i-n) == Math.abs(array[i] - array[n])){
                return false;
            }
        }
        return true;
    }
    //写一个方法，可以将皇后摆放的位置打印
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
}
