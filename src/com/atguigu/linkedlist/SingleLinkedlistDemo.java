package com.atguigu.linkedlist;

import java.util.Stack;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: SingleLinkedlistDemo    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/8/25 15:30   // 时间
 * @Version: 1.0     // 版本
 */
public class SingleLinkedlistDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建几个节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        //创建一个链表
        SingleLinkedlist singleLinkedlist = new SingleLinkedlist();
        //加入节点
/*        singleLinkedlist.add(hero1);
        singleLinkedlist.add(hero2);
        singleLinkedlist.add(hero3);
        singleLinkedlist.add(hero4);*/
        //显示
        //singleLinkedlist.list();

        //加入按照编号的顺序
        singleLinkedlist.addByOrder(hero2);
        singleLinkedlist.addByOrder(hero4);
        singleLinkedlist.addByOrder(hero1);
        singleLinkedlist.addByOrder(hero3);
        //测试修改节点的代码
        HeroNode newHero= new HeroNode(2, "小卢", "小尾巴");
        singleLinkedlist.update(newHero);
        //测试删除节点
        singleLinkedlist.delete(1);
        //显示一把
        singleLinkedlist.list();
        //测试一下 求单链表有效节点的个数
        System.out.printf("有效节点数为：%d\n",getLength(singleLinkedlist.getHead()));
        //测试一下，求倒数第K个节点
        System.out.println("倒数第二个节点为："+findIndexNode(singleLinkedlist.getHead(), 2));
        //测试一下，反转链表
        System.out.println("反转单链表:");
        reverseList(singleLinkedlist.getHead());
        singleLinkedlist.list();
        System.out.println("测试逆序打印单链表");
        reversePrint(singleLinkedlist.getHead());
    }
    //使用栈的方式来逆序打印单链表
    public static void reversePrint(HeroNode head){
        if (head.next==null){
            return;//空链表，不能打印
        }
        //创建一个栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur=head.next;//定义一个指针
        //利用遍历将各个节点入栈
        while (cur!=null){
            stack.push(cur);
            cur=cur.next;
        }
        //通过遍历出栈
        while (stack.size()>0){
            System.out.println(stack.pop());//栈的特点是先进入后出去
        }
    }
    //将单链表进行反转
    public static void reverseList(HeroNode head){
        //如果当前链表为空，或者只有一个节点，则无需反转，直接返回
        if (head.next==null || head.next.next==null){
            return;
        }
        //1.定义一个辅助节点，帮助我们遍历原来的链表
        HeroNode cur=head.next;
        HeroNode next=null;//指向当前节点[cur]的下一个节点
        HeroNode reverseHead=new HeroNode(0,"","");
        //2.遍历原来的链表，每遍历一个节点，将其取出，并放在新的链表reverseHead的最前端
        while (cur!=null){//若等于null代表反转完毕，可以跳出去了
            next=cur.next;//先暂时保存当前节点的下一代节点,因为后面有用。。。。。cur.next代表cur的下一个节点
            cur.next=reverseHead.next;//将cur指向新的链表的最前端。。。。。cur.next代表cur的next域
            reverseHead.next=cur;//将cur连接到新的链表上
            cur=next;//将next节点的位置赋予cur。即把cur指针重新拉回原来的head单链表这条路
        }
        //3.将head的next域，指向reverseHead.next.实现反转
        head.next=reverseHead.next;
    }
    //查找单链表中的倒数第K个节点
    //思路
    //1.编写一个方法，接收head节点，同时接收一个index
    //2.index表示的是倒数第index个节点
    //3.先把链表从头到尾遍历，得到链表的总的长度size
    //4.得到size后，我们从链表的第一个开始遍历size-index个，就得到了。
    //5.如果找到了 返回该节点，否则返回null
    public static HeroNode findIndexNode(HeroNode head,int index){
        //判断如果链表为空，返回null
        if (head.next==null){
            return null;
        }
        //第一次遍历，得到有效节点的个数
        int size=getLength(head);
        //进行第二次遍历，遍历到size-inde位置
        //先做一个数据的校验
        if (index<=0||index>size){
            return null;
        }
        //定义一个辅助变量
        HeroNode cur=head.next;
        //定义一个指针
        int flag=0;
        while (flag!=(size-index)){
            flag++;
            cur=cur.next;
        }
        return cur;
    }
    //方法：获取到单链表的节点的个数(如果是带头节点的链表，需求是不统计头节点)
    /**
     *
     * @param head 链表的头节点
     * @return 返回有效节点的个数
     */
    public static int getLength(HeroNode head){
        if (head.next==null){//带头节点的空链表
            return 0;
        }
        int length=0;
        //定义一个辅助变量
        HeroNode cur=head.next;//这里没有统计头节点
        while (cur!=null){
            length++;
            cur=cur.next;
        }
        return length;
    }
}
//定义SingleLinkedlist来管理我们的英雄
class SingleLinkedlist{
    //先初始化一个头节点,头节点不要动
    private HeroNode head=new HeroNode(0,"","");
    //返回头节点
    public HeroNode getHead(){
        return head;
    }
    //第一种添加节点到单向链表的方式（不考虑排名）
    //思路，当不考虑标号的顺序时
    // 1.找到当前链表的最后节点
    //2.将最后这个节点的next域指向新的节点
    public void add(HeroNode heroNode){
        //因为head节点不动，所有需要一个辅助节点temp
        HeroNode temp=head;
        //遍历链表，找到最后
        while (true){
            if (temp.next==null){
                break;
            }
            temp=temp.next;
        }
        //一定要退出循环后在添加
        //当退出while循环时，temp就指向链表的最后
        //将最后这个节点的next域指向新的节点
        temp.next=heroNode;
    }
    //第二种添加节点到单向链表的方式（考虑排名）
    //如果有这个排名，则添加失败，并给出提示信息
    public void addByOrder(HeroNode heroNode){
        //因为head节点不动，所有需要一个辅助节点temp，遍历链表，找到添加的位置
        HeroNode temp=head;
        boolean flag=false;//flag标志添加的编号是否存在，默认是false
        while (true){
            if (temp.next==null){//说明temp已经在链表的最后
                break;
            }
            if (temp.next.no>heroNode.no){//这种情况下的话，就找到新节点的位置了，就在temp的后面
                break;
            }else if (temp.next.no==heroNode.no){//说明希望添加的节点的位置编号已经存在了，添加失败，并给出提示信息
                flag=true;//说明已经有这个位置编号了
                break;
            }
            temp=temp.next;//后移
        }
        //判断flag的值
        if (flag){//不能添加，说明编号存在
            System.out.printf("准备添加的英雄的编号%d已经存在,不能加入\n",heroNode.no);
        }else {
            //插入到temp后面
            heroNode.next=temp.next;
            temp.next=heroNode;
        }
    }
    //完成修改节点的信息.
    // 1.根据编号来进行修改，即no编号不能改
    public void update(HeroNode heroNode){
        //判断是否为空
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点,根据no编号
        //先定义一定辅助变量
        HeroNode temp=head.next;
        boolean flag=false;//表示是否找到该节点
        while (true){
            if (temp==null){
                break;//到链表的最后,已经遍历结束
            }
            if (temp.no==heroNode.no){//找到此节点了
                flag=true;
                break;
            }
            temp=temp.next;
        }
        //根据flag判断是否找到要修改的节点]
        if (flag){
            temp.name=heroNode.name;
            temp.nickname=heroNode.nickname;
        }else {//没有这个节点
            System.out.printf("没有找到编号为：%d 的节点\n",heroNode.no);
        }
    }
    //删除节点
    //1.head节点不能动，所以需要一个temp辅助节点来找到待删除节点的前一个节点
    //2.我们在比较时,是temp.next.no和heroNode.no来比较
    public void delete(int delNo){
        HeroNode temp=head;
        boolean flag=false;//标志着是否找到待删除的节点
        if (temp.next==null){
            System.out.println("链表为空");
            return;
        }
        while (true){
            if (temp.next==null){//已经到链表的最后了
                break;
            }
            if (temp.next.no==delNo){//已经找到了
                flag=true;
                break;
            }
            temp=temp.next;//temp后移,实现遍历
        }
        if (flag){
            temp.next=temp.next.next;//注意：136的temp.next与143的temp.next的意义不一样。
                                    // 前一个指待删除的节点，后一个指待删除节点的前一个节点的next域
        }else {//没有这个节点
            System.out.printf("没有编号为%d的节点\n",delNo);
        }
    }
    //通过遍历显示链表
    public void list(){
        //判断链表是否为空
        if (head.next==null){
            System.out.println("链表为空！");
            return;
        }
        //因为头节点不能动，所有需要一个辅助变量来遍历
        HeroNode temp=head.next;
        while (true){
            //判断是否到链表最后
            if (temp==null){
                break;
            }
            System.out.println(temp);
            //将temp后移
            temp=temp.next;
        }
    }
}
//定义一个HeroNode,每个HeroNode对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;
    //构造器
    public HeroNode(int hNo,String hName,String hNickname){
        this.no=hNo;
        this.name=hName;
        this.nickname=hNickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname +
                '}';
    }
}