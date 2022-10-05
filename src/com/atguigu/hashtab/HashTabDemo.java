package com.atguigu.hashtab;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: HashTabDemo    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/10/5 18:24   // 时间
 * @Version: 1.0     // 版本
 */
public class HashTabDemo {
    public static void main(String[] args) {
        System.out.println("myh");
    }
}

//创建哈希表,管理多条链表
class HashTab{

}


//表示一个雇员，即为一个节点
class Emp{
    public int id;
    public String name;
    public Emp next;    //指向下个节点的指针，且next默认为空

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//创建一个EmpLinkedList,表示一条链表。(链表由节点组成)
class EmpLinkedList{
    //定义一个头指针，指向第一个节点Emp,因此这个链表的head是直接指向第一个Emp
    private Emp head;   //默认为null

    //添加雇员到链表（即给链表添加节点）
        //说明
            //1.假定，当添加雇员是，id自增。因此将节点加入到本链表的最后即可。
    public void add(Emp emp){
        //如果是添加第一个节点
        if (head==null){
            head = emp;
            return;
        }
        //如果不是添加第一个雇员，则需要使用一个辅助的指针，帮助定位到最后。
        Emp curEmp = head;
        while (true){
            if (curEmp.next == null){   //说明现在已经到最后了。
                break;
            }
            curEmp = curEmp.next;   //让指针后移
        }
        //当循环退出时，curEmp指向链表的最后一个节点,则直接将emp节点加入链表的最后
        curEmp.next = emp;
    }

    //遍历链表的雇员信息，即遍历链表中的节点
    public void list(){
        if (head == null){//说明链表为空
            System.out.println("链表为空");
            return;
        }
        System.out.println("当前链表的信息为");
        //定义一个辅助指针
        Emp curEmp = head;
        while (true){
            //程序走到这一步肯定是有节点的，所以我们打印下面这句话.
            System.out.printf("=> id=%d  name=%s\t",curEmp.id,curEmp.name);
            if (curEmp.next == null){   //说明curEmp已经是最后的节点
                break;
            }
            curEmp = curEmp.next;   //让指针后移
        }
        System.out.println();   //换行
    }
}
