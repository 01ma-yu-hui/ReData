package com.atguigu.queue;

import java.util.Scanner;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: CircleArrayQueue    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/8/22 20:59   // 时间
 * @Version: 1.0     // 版本
 */
public class CircleArrayQueue {
    public static void main(String[] args) {
        System.out.println("测试数组模拟环形队列");
        //创建一个队列
        CircleArray arrayQueue = new CircleArray(4);//其队列的有效数据最大是3
        //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop=true;
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取数据");
            System.out.println("h(head):查看队列头的数据");
            char key = scanner.next().charAt(0);//接收用户输入一个字符
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.print("请输入一个数：");
                    int i = scanner.nextInt();
                    arrayQueue.addQueue(i);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是：%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("头部数据是：%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop=false;
                    break;
            }
        }
        System.out.println("程序以退出！");
    }
}
//使用数组模拟队列-编写一个ArrayQueue类
class CircleArray{
    private  int maxSize;//表示数组的最大容量
    private  int front;//队列头
    private int rear;//队列尾
    private int[] arr;//该数组用于存放数据,模拟队列

    //创建队列的构造器
    public CircleArray(int arrMaxSize) {
        maxSize=arrMaxSize;
        arr=new int[maxSize];
        front=0;//指向队列的头部（即就是队列的第一个数据）
        rear=0;//指向队列尾，分析出rear是指向队列尾的后一个位置，但不包括后一个位置。目的是空出一个位置，用作约定。
    }
    //判断队列是否满
    public boolean isFull(){
        return (rear+1)%maxSize==front;
    }
    //判断队列是否为空
    public boolean isEmpty(){
        return front==rear;
    }
    //添加数据到队列
    public void addQueue(int n){
        //判断队列是否满
        if (isFull()){
            System.out.println("队列满，不能加入数据");
            return;
        }
        //直接将数据加入
        arr[rear]=n;
        //将rear后移，这里必须考虑取模
        rear=(rear+1)%maxSize;
    }
    //从队列中取数据
    public int getQueue(){
        //判断队列是否空
        if (isEmpty()){
            //通过抛出异常
            throw new RuntimeException("队列空，不能取数据");//throw本身会导致代码return
        }
        //这里需要分析出front是指向队列的第一个元素
        //1.先把front对应的值保留在一个临时变量里
        //2.将front后移
        //3.将临时变量返回
        int value=arr[front];
        front=(front+1)%maxSize;
        return value;
    }
    //显示队列的所以数据
    public void showQueue(){
        //遍历arr数组
        if (isEmpty()){
            System.out.println("队列空，没有数据");
            return;
        }
        //思路：从front开始遍历，遍历多少个元素
        //i值要取模，若不取，则第二轮的时候会溢出
        for (int i = front; i < front+size(); i++) {
            System.out.printf("arr[%d]=%d\n",i%maxSize ,arr[i%maxSize]);//格式化输出
        }
    }
    //求出当前队列有效数据的个数
    public int size(){
        return (rear+maxSize-front)%maxSize;
    }
    //显示队列的头数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空，没有数据");
        }
        return arr[front];
    }
}
