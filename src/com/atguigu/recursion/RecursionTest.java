package com.atguigu.recursion;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: RecursionTest    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/9/14 11:57   // 时间
 * @Version: 1.0     // 版本
 */
public class RecursionTest {
    public static void main(String[] args) {
        //通过打印问题，回顾递归的调用机制
        //test(6);
        int factorial = factorial(4);
        System.out.println(factorial);
        System.out.println("我是mian方法");
    }
    //打印问题
    public static void test(int n){
        if (n > 2){
            test(n - 1);
        }
            System.out.println("n = "+n);
    }
    //阶乘问题
    public static int factorial(int n){
        if (n == 1){
            return 1;
        }else {
            return factorial(n - 1) * n;
        }
    }
}
