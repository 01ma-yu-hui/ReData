package com.atguigu.binarysorttree;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: BinarySortTreeDemo    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/10/24 15:17   // 时间
 * @Version: 1.0     // 版本
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = new int[]{7,3,10,12,5,1,9,2};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环的添加节点到二叉排序树
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        //遍历
        binarySortTree.infixOrder();
        //测试删除叶子节点
        System.out.println("------测试删除------");
        binarySortTree.delNode(10);
        binarySortTree.infixOrder();
    }
}

/**
 *
 */
//创建二叉排序树
class BinarySortTree{
    private Node root;//根节点

    //查找要删除的节点
    public Node search(int value){
        if (root == null){
            return null;
        }else {
            return root.search(value);
        }
    }

    //查找要删除节点的父节点
    public Node searchParent(int value){
        if (root == null){
            return null;
        }else {
            return root.searchParent(value);
        }
    }

    //编写方法:1.返回以node为根节点的二叉排序树的最小节点的值 2.以node为根节点的二叉排序树的最小节点
    /**
     * @param node 传入的节点(当作二叉排序树的根节点)
     * @return 返回的是以node为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Node node){
        Node target = node;
        //循环的查找左子节点，就会找到最小值.(因为只要左子节点不为空，那最小的值，一定在左子节点上)
        while (target.left != null){
            target = target.left;
        }
        //这是target就指向了最小的节点
        //删除最小节点
        delNode(target.value);
        return target.value;    //疑惑：都已经删除了，为什么还能调用target
        // 解惑：删除的是树的节点，target只是一个临时变量，用来保存数据的。
    }


    //删除节点
    public void delNode(int value){
        if (root == null){
            return;
        }else {
            //1.先去找到要删除的节点targetNode
            Node targetNode = search(value);
            //判断targetNode是否为null
            if (targetNode == null){
                return;
            }
            //程序走到这，说明我们已经确实找到了要删除的节点
            if (root.left == null && root.right == null){//程序能进来，说明我们要删除的节点就是root节点
                root = null;
                return;
            }
            //2.去找到要删除节点的父节点
            Node parent = searchParent(value);
            //如果要删除的节点是叶子节点
            if (targetNode.left == null && targetNode.right == null){
                //3.判断targetNode是父节点的左子节点还是右子节点
                if (parent.left != null && parent.left.value == value){//说明targetNode是父节点的左子节点
                    parent.left = null;
                }else {
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null){    //删除有两颗子树的节点
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            } else {    //删除只有一颗子树的节点
                //如果要删除的节点有左子节点
                if (targetNode.left != null){
                    //如果要删除的节点是parent的左子节点
                    if (parent.left.value == value){
                        parent.left = targetNode.left;
                    }else {//如果要删除的节点是parent的右子节点
                        parent.right = targetNode.left;
                    }
                }else { //要删除的节点有右子节点
                    //如果要删除的节点是parent的左子节点
                    if (parent.left.value == value){
                        parent.left = targetNode.right;
                    }else {//如果要删除的节点是parent的右子节点
                        parent.right = targetNode.right;
                    }
                }
            }
        }
    }


    //添加节点的方法
    public void add(Node node){
        if (root == null){
            root = node;//如果root为空，直接让root指向node
        }else {
            root.add(node);
        }
    }

    //中序遍历的方法
    public void infixOrder(){
        if (root != null){
            root.infixOrder();
        }else {
            System.out.println("当前的二叉排序树为空,无法遍历");
        }
    }
}

//创建节点
class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //查找要删除的节点
    /**
     * @param value 要删除节点的值
     * @return 如果找到返回该节点，否则返回null
     */
    public Node search(int value){
        if (value == this.value){   //找到了，就是该节点
            return this;
        }else if (value < this.value){  //如果查找的值小于当前节点，则向左子树递归查找
            if (this.left == null){     //要保证左子树有节点，若没有节点，则返回null
                return null;
            }
            return this.left.search(value);
        } else {    //如果查找的值不小于当前节点，则向右子树递归查找
            if (this.right == null){
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找要删除节点的父节点
    /**
     * @param value 要找的节点的值
     * @return  返回的是要删除的节点的父节点，如果没有则返回null
     */
    public Node searchParent(int value){
        //如果当前节点就是要删除节点的父节点，就返回
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)){
            return this;
        } else {
            //如果查找的值小于当前节点的值，并且当前节点的左子节点不为空,则向左递归
            if (value < this.value && this.left != null){
                return this.left.searchParent(value);
                //如果查找的值大于等于当前节点的值，并且当前节点的右子节点不为空,则向右递归
            }else if (value >= this.value && this.right != null){
                return this.right.searchParent(value);
            }else { //左子树或者右子树可能为空
                return null;
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //添加节点的方法
    //以递归的形式添加节点，注意需要满足二叉排序树的要求
    public void add(Node node){
        if (node == null){
            return;
        }
        //判断传入的节点的值，和当前子树的根节点的关系
        if (node.value < this.value){
            //如果当前节点左子节点为null
            if (this.left == null){
                this.left = node;
            }else {
                //递归向左子树添加
                this.left.add(node);
            }
        }else {//添加节点的值大于当前子树根节点的值
            if (this.right == null){
                this.right = node;
            }else {
                //递归向右子树添加
                this.right.add(node);
            }
        }
    }


    //中序遍历
    public void infixOrder(){
        if (this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null){
            this.right.infixOrder();
        }
    }
}
