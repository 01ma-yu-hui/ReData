package com.atguigu.hashtab;

import java.util.Scanner;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: HashTabDemo    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/10/5 18:24   // 时间
 * @Version: 1.0     // 版本
 */
public class HashTabDemo {
    public static void main(String[] args) {
        //创建一个哈希表
        HashTab hashTab = new HashTab(7);
        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("add：添加雇员");
            System.out.println("list：显示雇员");
            System.out.println("find：根据id来查找雇员");
            System.out.println("exit：退出系统");
            key = scanner.next();
            switch (key){
                case "add" :
                    System.out.print("输入用户的id与名字：");
                    int empId = scanner.nextInt();
                    String empName = scanner.next();
                    Emp emp = new Emp(empId, empName);
                    hashTab.add(emp);
                    System.out.println("添加成功");
                    break;
                case "list" :
                    hashTab.list();
                    System.out.println("已遍历完成");
                    break;
                case "find" :
                    System.out.print("输入员工id：");
                    int findEmpById = scanner.nextInt();
                    hashTab.findEmpById(findEmpById);
                    break;
                case "exit" :
                    scanner.close();
                    System.out.println("已退出");
                    return;
            }
        }
    }
}

//创建哈希表,管理多条链表
class HashTab{
    private EmpLinkedList[] empLinkedListArray; //先声明
    private int size;   //因为散列函数当中用到这个size，所以这里要定义出来。表示有多少条链表
    //创建构造器，并且初始化数组
    public HashTab(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];   //我实例化的是一个数组，但是数组当中的元素是我的一条条链表。
        //分别初始化每一条链表,否则会出现空指针异常
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp){
        //根据员工id，得到该员工应当添加到哪条链表
        int empLinkedListNo = hashFun(emp.id);
        //将emp添加到对于的链表中
        empLinkedListArray[empLinkedListNo].add(emp);
    }
    //遍历hashtab
    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    //根据输入的id查找雇员
    public void findEmpById(int id){
        //使用散列函数来确定到哪条链表去查找
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].findEmpById(id);
        if (emp != null){
            System.out.printf("在第%d条链表中找到该雇员 id为%d\n",empLinkedListNo+1,emp.id);
        }else {
            System.out.println("在哈希表中没有该雇员");
        }
    }

    //编写散列函数,使用一个简单的取模法。目的是根据散列函数，将节点散开。
    public int hashFun(int id){
        return id % size;
    }
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
    public void list(int no){
        if (head == null){//说明链表为空
            System.out.printf("第%d条链表为空\n",no+1);
            return;
        }
        System.out.printf("第%d条链表的信息为：",no+1);
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

    //根据id来查找雇员
        //如果查找到就返回Emp，如果没有查找到就返回null
    public Emp findEmpById(int id){
        //判断链表是否为null
        if (head == null){
            System.out.println("链表为空");
            return null;
        }
        //定义一个辅助指针来帮助我们进行查找
        Emp curEmp = head;
        while (true){
            if (curEmp.id == id){//找到了
                break;  //这时curEmp就指向要查找的雇员
            }
            //退出的条件
            if (curEmp.next == null){
                curEmp = null;  //老师的做法是将其制空 ，但是我认为这样做会改变节点中原有的数据
                break;
            }
            //在退出时，curEmp要么指向我要查找到的节点，要么指向最后一个节点。
            curEmp = curEmp.next;
        }
        return curEmp;
    }
}
