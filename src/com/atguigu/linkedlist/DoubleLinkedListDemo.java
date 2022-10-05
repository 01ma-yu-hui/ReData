package com.atguigu.linkedlist;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: DoubleLinkedListDemo    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/8/30 20:51   // 时间
 * @Version: 1.0     // 版本
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        System.out.println("双向链表的测试");
        //先创建几个节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        HeroNode2 hero6 = new HeroNode2(6, "曹操", "三姓家奴");
        HeroNode2 hero5 = new HeroNode2(5, "张飞", "张翼德");
        //创建一个双向链表对象
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        //给双向链表添加节点
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.add(hero4);
//        //测试显示
//        doubleLinkedList.list();
//        //测试修改
//        HeroNode2 upNode = new HeroNode2(2, "小卢", "小尾巴");
//        System.out.println("修改2号节点");
//        doubleLinkedList.update(upNode);
//        doubleLinkedList.list();
//        //测试删除
//        System.out.println("删除节点");
//        doubleLinkedList.delete(4);
//        doubleLinkedList.list();
        System.out.println("====================================");
        System.out.println("测试按顺序添加节点");
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero6);
        doubleLinkedList.addByOrder(hero5);
        doubleLinkedList.list();
    }
}
//创建一个双向链表的类
class DoubleLinkedList{
    //先初始化一个头节点,头节点不要动
    private HeroNode2 head=new HeroNode2(0,"","");
    //返回head节点
    public HeroNode2 getHead(){
        return head;
    }
    //遍历双向链表
    public void list(){
        //判断链表是否为空
        if (head.next==null){
            System.out.println("链表为空！");
            return;
        }
        //因为头节点不能动，所有需要一个辅助变量来遍历
        HeroNode2 temp=head.next;
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
    //第一种添加方式：直接添加到末尾
    public void add(HeroNode2 heroNode){
        //因为head节点不动，所有需要一个辅助节点temp
        HeroNode2 temp=head;
        //遍历链表，找到最后
        while (true){
            if (temp.next==null){//直到双向链表为空的时候跳出循环
                break;
            }
            temp=temp.next;
        }
        //一定要退出循环后在添加
        //当退出while循环时，temp就指向链表的最后
        //将最后这个节点的next域指向新的节点
        temp.next=heroNode;
        heroNode.pre=temp;
    }
    //第二种添加节点到双向链表的方式（考虑排名）
    //如果有这个排名，则添加失败，并给出提示信息
    public void addByOrder(HeroNode2 heroNode){
        //因为head节点不动，所有需要一个辅助节点temp，遍历链表，找到添加的位置
        HeroNode2 temp=head;
        boolean flag=false;//flag标志添加的编号是否存在，默认是false

        while (true){
            if (temp.next==null){//说明temp已经在链表的最后。temp指的是待添加节点的前一个节点。
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
            if (temp.next!=null){
                temp.next.pre=heroNode;
                heroNode.pre=temp;
            }
        }
    }
    //修改双向链表的节点
    // 1.根据编号来进行修改，即no编号不能改
    public void update(HeroNode2 heroNode){
        //判断是否为空
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点,根据no编号
        //先定义一定辅助变量
        HeroNode2 temp=head.next;
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
    //删除双向链表中的某个节点
    //1.head节点不能动，所以需要一个temp辅助节点来找到待删除的节点
    //2.我们在比较时,是temp.no和heroNode.no来比较(即直接找待删除节点本身)
    public void delete(int delNo){
        HeroNode2 temp=head;
        boolean flag=false;//标志着是否找到待删除的节点
        if (temp.next==null){
            System.out.println("链表为空");
            return;
        }
        while (true){
            if (temp==null){//已经到链表的最后了
                break;
            }
            if (temp.no==delNo){//已经找到了
                flag=true;
                break;
            }
            temp=temp.next;//temp后移,实现遍历
        }
        if (flag){
            temp.pre.next=temp.next;//前一个节点的next域指向后一个节点
            if (temp.next!=null){
                temp.next.pre=temp.pre;//如果是最后一个节点就不需要指向这句话,否则会出现空指针异常。后一个节点的pre域指向后一个节点
            }
        }else {//没有这个节点
            System.out.printf("没有编号为%d的节点\n",delNo);
        }
    }
}
//定义一个HeroNode2,每个HeroNode2对象就是一个节点
class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;//指向后一个节点,默认为null
    public HeroNode2 pre;//指向前一个节点,默认为null
    //构造器
    public HeroNode2(int hNo,String hName,String hNickname){
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