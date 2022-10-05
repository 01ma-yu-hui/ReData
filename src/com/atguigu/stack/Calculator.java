package com.atguigu.stack;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: Calculator    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/9/6 17:27   // 时间
 * @Version: 1.0     // 版本
 */
public class Calculator {
    public static void main(String[] args) {
        //根据老师的思路完成表达式的运算。
        String expression = "30*2-6*9+1";//如何处理多位数的问题
        //创建两个栈，一个数栈，一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0; //用于扫描
        int num1 = 0; //用于接收弹栈
        int num2 = 0; //用于接收弹栈
        char oper = ' '; //用于接收弹栈的符号
        int res = 0; //用于接收结果
        char ch = ' '; //将每次扫描得到数字或者的操作符保存到ch
        String keepNum = "";//用于拼接多位数的
        //开始while循环的扫描expression
        while (true){
            //依次得到expression的每一个字符
            ch = expression.substring(index,index+1).charAt(0);
            //str.substring(indexStart,indexEnd)功能为：
                // 截取方法调用者（即str）的从indexStart到indexEnd（包括indexStart，不包括indexEnd；
                // 即含头不含尾、或左闭右开区间内的子字符串）
            //str.charAt(int index) ：返回指定索引处的字符。
            //判断ch是什么，然后做相应的处理
            if (operStack.isOper(ch)){//如果是操作符
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()){
                    //如果符号栈有操作符，就进行比较，如果当前的操作符小于等于栈中的操作符，就需要从数栈中pop出两个数，
                    //在符号栈中pop出一个符号，进行运算，将得到的结果如数栈，将当前的操作符如符号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = (char) operStack.pop();
                        res = numStack.cal(num1,num2,oper);
                        numStack.push(res);
                        operStack.push(ch);
                    }else {
                        //如果当前的操作符大于栈中的操作符，直接入栈
                        operStack.push(ch);
                    }
                }else {
                    //符号栈为空，直接入栈
                    operStack.push(ch);
                }
            }else {//如果是数，则直接如数栈（有瑕疵）
                //numStack.push(ch - 48);//字符1 与数字1 的ASC码相差48
                //分析思路：
                //1.当处理多位数时，不能发现是一个数就立即如数栈，因为可能是多位数。
                //2.需要向expression的表达式的index 后再看一位，如果是数 就继续扫描，如果是符号就入栈
                //3.我们需要定义一个变量 字符串，用于拼接
                //处理多位数
                keepNum +=ch;

                //如果ch已经是expression的最后一位，就直接入数栈
                if (index==expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                    //注意是看后一位，不是index++
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));//把字符串转为int类型
                        //重要！！！！！！keepNum得清空
                        keepNum = "";
                    }
                }
            }
            //让index+1 ，并判断是否扫描到expression的最后了
            index++;
            if (index >= expression.length()){//因为index是从0开始，而字符串的长度length()是从1开始。所以可以正常跳出
                break;
            }
        }
        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运算
        while (true){
            //如果符号栈为空，则计算到最后的结果,数栈中只有一个数
            if (operStack.isEmpty()){
                break;
            }else {                                 //老师这里没有else
                num1 = numStack.pop();
                num2 = numStack.pop();
                oper = (char) operStack.pop();
                res = numStack.cal(num1,num2,oper);
                numStack.push(res);
            }
        }
        //循环结束后，数栈中只有一个数，为最后的结果
        System.out.printf("表达式：%s = %d",expression,numStack.pop());
    }
}
//先创建一个栈，直接使用前面创建好的
    //用数组模拟栈,需要扩展功能
class ArrayStack2{
    private int maxSize;//栈的大小
    private int[] stack;// 用数组模拟栈，数据村在该数组中
    private int top=-1;//栈顶。初始化为-1
    public  ArrayStack2 (int maxSize){
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
    //返回运算符的优先级，优先级由程序员来确定，优先级使用数字表示，数字越大，则优先级就越高
    public int priority(char oper){
        if (oper == '*' || oper == '/'){
            return 1;
        }else if (oper == '+' || oper == '-'){
            return 0;
        }else {
            return -1;//假定目前的表达式只有加减乘除。
        }
    }
    //判断是不是一个操作符
    public boolean isOper(char val){
        return val=='+' || val=='-' || val=='*' || val=='/';
    }
    //计算方法
    public int cal(int num1,int num2,char oper){
        int res = 0; //用于存放计算的结果
        switch (oper){
            case '+' :
                res = num1 + num2;
                break;
            case '-' :
                res = num2 - num1; //注意顺序
                break;
            case '*' :
                res = num1 * num2;
                break;
            case '/' :
                res = num2 / num1; //注意顺序
                break;
        }
        return res;
    }
    //返回当前栈顶的值，但不是真正的pop
    public char peek(){
        return (char) stack[top];
    }
}
