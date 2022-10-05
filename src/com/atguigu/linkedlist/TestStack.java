package com.atguigu.linkedlist;

import java.util.Stack;

/**
 * @Description: // 演示栈的基本使用
 * @ClassName: TestStack    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/8/29 17:14   // 时间
 * @Version: 1.0     // 版本
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        //add()方法,或者push()方法入栈
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");
        //通过遍历出栈
        while (stack.size()>0){
            System.out.println(stack.pop());//pop就是将栈顶的数据取出
        }
    }
}
