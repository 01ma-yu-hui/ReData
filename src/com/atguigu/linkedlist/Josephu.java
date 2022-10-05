package com.atguigu.linkedlist;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: Josephu    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/8/31 21:06   // 时间
 * @Version: 1.0     // 版本
 */
public class Josephu {
    public static void main(String[] args) {
        //测试环形链表
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(125);
        circleSingleLinkedList.showBoy();
        //测试小孩出圈
        circleSingleLinkedList.countBoy(10,20);//2 4 1 5 3
    }
}
//创建一个单向环形链表
class CircleSingleLinkedList{
    //创建一个first节点，目前没有编号.目的是使第一个节点形成环
    private Boy first=null;
    //添加小孩构建一个环形链表
    public void addBoy(int nums){
        //先对nums做一个数据校验
        if (nums<2){
            System.out.println("数据不正确");
            return;
        }
        //创建一个辅助节点
        Boy curBoy=null;
        //使用for循环来创建我们的环形链表
        for (int i = 1; i <=nums; i++) {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i==1){
                first=boy;
                first.setNext(first);//构成一个环
                curBoy=first;//给curBoy赋值，并让其指向第一个小孩
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy=boy;
            }
        }
    }
    //遍历当前的环形链表]
    public void showBoy(){
        //先判断链表是否为空
        if (first==null){
            System.out.println("链表为空");
            return;
        }
        //因为first不能动，所以定义一个赋值指针，完成遍历
        Boy curBoy = first;
        while (true){
            System.out.printf("小孩的编号：%d\n",curBoy.getNo());
            if (curBoy.getNext()==first){//说明已经遍历完毕
                break;
            }
            curBoy=curBoy.getNext();//让指针后移
        }
    }
    //根据用户的输入，计算出出圈的顺序

    /**
     *
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     */
    public void countBoy(int startNo,int countNum){
        //对数据做一个校验
        if (first==null || startNo<1 ){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建一个辅助指针，帮助完成小孩出圈
        Boy helper=first;
        //通过while循环让helper指向环形链表的最后这个节点
        while (true){
            if (helper.getNext()==first){//说明helper指向了最后
                break;
            }
            helper=helper.getNext();//让helper后移
        }
        //小孩报数前，先让first 和 helper 移动k-1次。因为不一定是从1号开始报数。
        for (int i = 0; i < startNo -1; i++) {
            first=first.getNext();
            helper=helper.getNext();
        }
        //当小孩报数时，先让first 和 helper 移动m-1次。然后出圈
        //这是一个循环操作，直到圈中只有1个小孩
        while (true){
            if (first.getNext()==first){//说明圈中只有一个节点。    能否是f.n=f
                System.out.printf("最后圈中的小孩编号：%d\n",first.getNo());//  能否写在break之前
                break;
            }
            //让first 和 helper 移动m-1次
            for (int i = 0; i < countNum-1; i++) {
                first=first.getNext();
                helper=helper.getNext();
            }
            //这时first指向的节点就是出圈的节点
            System.out.printf("小孩：%d出圈\n",first.getNo());
            //这时，将first指向的节点出圈
            first=first.getNext();
            helper.setNext(first);
        }
    }
}
//创建一个boy类，表示一个节点
class Boy {
    private int no;//编号
    private Boy next;//指向下一个节点，默认为空
    //创建一个构造方法
    public Boy(int no) {
        this.no=no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}