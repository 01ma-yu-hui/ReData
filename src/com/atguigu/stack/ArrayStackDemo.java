package com.atguigu.stack;

import java.util.Scanner;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: ArrayStackDemo    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/9/2 18:01   // 时间
 * @Version: 1.0     // 版本
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        //创建一个arrayStack对象
        ArrayStack arrayStack = new ArrayStack(5);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("show:表示显示栈");
            System.out.println("exit:退出栈");
            System.out.println("push:添加数据到栈");
            System.out.println("pop:表示从栈中取数据");
            System.out.print("请输入你的选择：");
            key = scanner.next();
            switch (key){
                case "show":
                    arrayStack.list();
                    break;
                case "push":
                    System.out.print("请输入一个数：");
                    int num = scanner.nextInt();
                    arrayStack.push(num);
                    break;
                case "pop":
                    try {
                        int pop = arrayStack.pop();//接收return的值
                        System.out.printf("出栈的数据是%d\n",pop);
                    }catch (Exception e){
                        System.out.println(e.getMessage());//处理异常信息
                    }
                    break;
                case "exit":
                    scanner.close();
                    System.out.println("退出");
                    loop=false;
                    break;
            }
        }
    }
}
//定义一个ArrayStack 表示栈结构

class ArrayStack{
    private int maxSize;//栈的大小
    private int[] stack;// 用数组模拟栈，数据村在该数组中
    private int top=-1;//栈顶。初始化为-1
    public  ArrayStack (int maxSize){
        this.maxSize=maxSize;
        stack =new int[this.maxSize];//给数组进行初始化
    }
    //栈满
    public boolean isFull(){
        return top==maxSize-1;
    }
    //栈空
    public boolean isEmpty(){
        return top==-1;
    }
    //入栈push
    public void push(int value){
        //先判断栈是否已满
        if (isFull()){
            System.out.println("栈已经满了");
            return;
        }else {   //注：老师这里没有else
            top++;
            stack[top]=value;
        }
    }
    //出栈pop
    public int pop(){
        if (isEmpty()){//判断栈是否为空
            throw new RuntimeException("栈空");//使用异常
        }
            int value =stack[top];
            top--;
            return value;

    }
    //显示栈
    public void list(){
        //遍历时，需要从栈顶开始显示数据
        if (isEmpty()){
            System.out.println("栈空，没有数据");
            return;
        }else {//老师这里没有else
            for (int i =top;i>=0;i--){
                System.out.printf("stack[%d]=%d\n",i,stack[i]);
            }
        }
    }
}
