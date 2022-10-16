package com.atguigu.tree.threadedbinarytree;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: ThreadedBinaryTreeDemo    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/10/16 15:36   // 时间
 * @Version: 1.0     // 版本
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {

    }
}

//定义ThreadedBinaryTree 实现了线索化功能的二叉树
class BinaryTree{

    private HeroNode root;

    //为了实现线索化，需要创建要给指向当前节点的前驱节点的指针
        //即在递归进行线索化时，pre总是保留前一个节点
    private HeroNode pre = null;

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //编写对二叉树进行中序线索化的方法
    /**
     * @param node 该节点就是当前需要线索化的节点
     */
    public void threadedBinaryTree(HeroNode node){

        //如果node == null,不能线索化
        if (node == null){
            return;
        }

        //(一)先线索化左子树
        threadedBinaryTree(node.getLeft());
        //(二)线索化当前节点

        //处理当前节点的前驱节点
        if (node.getLeft() == null){
            //当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左值针的类型
            node.setLeftType(1);
        }

        //处理当前节点的后继节点
        if (pre != null && pre.getRight() == null){
            //让前驱节点的右指针指向当前的节点
            pre.setRight(node);
            //修改前驱节点的右值针的类型
            pre.setRightType(1);
        }
        //!!!!!每处理一个节点后，让当前节点是下一个节点的前驱节点
        pre = node;
        //(三)线索化右子树
        threadedBinaryTree(node.getRight());
    }

    //前序遍历
    public void preOrder(){
        if (this.root != null){
            this.root.preOrder();
        }else {
            System.out.println("当前二叉树为空，不能遍历");
        }
    }
    //中序遍历
    public void infixOrder(){
        if (this.root != null){
            this.root.infixOrder();
        }else {
            System.out.println("当前二叉树为空，不能遍历");
        }
    }
    //后序遍历
    public void postOrder(){
        if (this.root != null){
            this.root.postOrder();
        }else {
            System.out.println("当前二叉树为空，不能遍历");
        }
    }

    //前序查找
    public HeroNode preOrderSearch(int no){
        if (this.root != null){
            return this.root.preOrderSearch(no);
        }else {
            return null;
        }
    }
    //中序查找
    public HeroNode infixOrderSearch(int no){
        if (this.root != null){
            return this.root.infixOrderSearch(no);
        }else {
            return null;
        }
    }
    //后序查找
    public HeroNode postOrderSearch(int no){
        if (this.root != null){
            return this.root.postOrderSearch(no);
        }else {
            return null;
        }
    }

    //删除二叉树的节点
    public void delNode(int no){
        if (this.root != null && this.root.getNo() != no){
            this.root.delNode(no);
        }else if (this.root != null && this.root.getNo() == no){
            this.root = null;
        }else {
            System.out.println("二叉树为空数");
        }
    }
}

//创建HeroNode
class HeroNode{

    private int no;

    private String name;

    private HeroNode left;  //因为默认为null

    private HeroNode right;  //因为默认为null

    //说明
    //1.如果leftType == 0，表示指向的是左子树，如果是1表示指向的是前驱节点
    //2.如果rightType == 0，表示指向的是右子树，如果是1表示指向的是后继节点
    private int leftType;

    private int rightType;

    public HeroNode(int no, String name) {  //左右指针不需要给，因为默认为null
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //递归删除节点
    public void delNode(int no){

        //思路
        //1.如果当前节点的左子节点不为空，并且左子节点，就是要删除的节点，就将this.left = null;并且就返回，结束递归删除
        if (this.left != null && this.left.no == no){
            this.left = null;
            return;
        }
        //2.向左子树递归删除
        if (this.left != null){
            this.left.delNode(no);
        }
        //3.如果当前节点的
        // 子节点不为空，并且右子节点，就是要删除的节点，就将this.right = null;并且就返回，结束递归删除
        if (this.right != null && this.right.no == no){
            this.right = null;
            return;
        }
        ////4.向右子树递归删除
        if (this.right != null){
            this.right.delNode(no);
        }
    }


    //编写前序遍历的方法
    public void preOrder(){
        System.out.println(this);   //先输出父节点
        //递归向左子树前序遍历
        if (this.left != null){
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null){
            this.right.preOrder();
        }
    }

    //编写中序遍历的方法
    public void infixOrder(){
        //先递归向左子树中序遍历
        if (this.left != null){
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);
        //递归向右子树中序遍历
        if (this.right != null){
            this.right.infixOrder();
        }
    }

    //编写后序遍历的方法
    public void postOrder(){
        if (this.left != null){
            this.left.postOrder();
        }
        if (this.right != null){
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * @param no 查找no
     * @return 如果找到返回该Node，如果没有找到返回NULL
     */
    //前序遍历查找
    public HeroNode preOrderSearch(int no){
        //比较当前节点是不是
        if (this.no == no){
            return this;
        }
        //如果不是，则判断当前节点的左子节点是否为空，如果不为空，则递归前序查找。
        //如果左递归找到则返回节点。
        HeroNode resNode = null;    //定义一个结果节点
        if (this.left != null){
            resNode = this.left.preOrderSearch(no);   //进行递归,并接收结果节点
        }
        if (resNode != null){   //说明:要是没有这个方法，则一直为null,因为递归停不下来。
            return resNode;
        }
        //1.左递归前序查找，找到节点，则返回，否则继续判断
        //2.当前节点的右子节点是否为空，如果不空，则继续向右递归前序查找
        if (this.right != null){
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no){
        HeroNode resNode = null;    //定义一个变量，用于接收节点
        //判断当前节点的左子节点是否为空，如果不为空，则递归中序查找
        if (this.left != null){
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        if (this.no == no){
            return  this;
        }
        if (this.right != null){
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no){
        HeroNode resNode = null;
        if (this.left != null){//说明在左子树找到了
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        if (this.right != null){
            resNode = this.right.postOrderSearch(no);
        }
        if (this.no == no){
            return this;
        }
        return resNode;
    }
}
