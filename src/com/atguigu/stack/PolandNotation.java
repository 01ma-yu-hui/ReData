package com.atguigu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: PolandNotation    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/9/9 15:56   // 时间
 * @Version: 1.0     // 版本
 */
public class PolandNotation {
    public static void main(String[] args) {
        //完成将一个中缀表达式转为后缀表达式的功能
        //说明
        //1. 1+（（2+3）*4）-5=>转成 1 2 3 + 4 * + 5 -
        //2. 因为直接去扫描字符串不太方便，因此 先将这个字符串1+（（2+3）*4）-5=>转为对于的list
        //3.将得到的中缀表达式转为后缀表达式 并存放在list集合中
        String expression = "2*9+5*(4+7/3)";


        //先定义一个逆波兰表达式
        //(30+4)*5-6---->3 4 + 5 * 6 -
        //为了方便，逆波兰表达式的数字和符号使用空格隔开
        //String suffixExpression = "2 9 * 5 4 7 3 / + * +";
        //思路
        //1.先将30 4 + 5 * 6 - 放入ArrayList中。
        //2.将ArrayList传给一个方法，遍历ArrayList 配合栈完成计算
        //List<String> listString = getListString(suffixExpression);
        //int cal = cal(listString);
        //System.out.println("计算的结果："+cal);
        List<String> list = toInfixExpressionList(expression);
        System.out.println("中缀表达式："+list);
        List<String> list1 = parseSuffixExpressionList(list);
        System.out.println("后缀表达式："+list1);
        System.out.println("计算结果："+cal(list1));
    }
    //方法：将中缀表达式转为对于的list
    public static List<String> toInfixExpressionList(String s){
        //先定义一个list 来存放中缀表达式对于的内容
        List<String> ls =new ArrayList<String>();
        int i = 0;//这是一个指针,用于遍历s
        String str;//对多位数的拼接工作
        char c;//每遍历到一个字符，就放入到c中
        do {
            if ((c=s.charAt(i)) < 48 || (c=s.charAt(i)) > 57){//非数字的情况
                ls.add(c + "");
                i++;//i后移
            }else {//是一个数字，需要考虑多位数的情况
                str = "";//将str制空
                while (i < s.length() && (c=s.charAt(i)) >= 48 && (c=s.charAt(i)) <= 57){
                    str +=c;//拼接
                    i++;
                }
                ls.add(str);
            }
        }while (i < s.length());
        return ls;
    }
    //方法：将得到的中缀表达式转为后缀表达式 并存放在list集合中
    public static List<String> parseSuffixExpressionList(List<String> ls){
        //对于两个栈
        Stack<String> s1 = new Stack<String>();//符号栈
        //说明：因为s2栈 在整个转换过程中，没有pop操作,而且后面还需要逆序输出，因此比较麻烦，这里我们用集合代替
            //Stack<String> s2 = new Stack<String>();//用于存放中间结果
        List<String> s2 = new ArrayList<String>();//用于存放中间结果
        //遍历ls
        for (String item : ls) {
            //如果是一个数，就加入到s2
            if (item.matches("\\d+")){
                s2.add(item);
            }else if (item.equals("(")){
                s1.push(item);
            }else if (item.equals(")")){//如果是），则依次弹出s1栈顶的运算符，并压入s2，直到遇到(为止，此时一对括号丢弃
                while (!s1.peek().equals("(")){//peek()查看栈顶的元素，但不弹出
                    s2.add(s1.pop());
                }
                s1.pop();//将一对括号丢弃
            }else {//item为运算符的情况
                //当item的优先级下于等于s1栈顶的运算符,将s1栈顶的运算符弹出并压入到s2中，再次转到4.1于s1中新的栈顶运算符相比较
                    //缺少比较运算符优先级高低的方法
                while (s1.size() != 0 && !s1.peek().equals("(")&&Operation.getValue(item) <= Operation.getValue(s1.peek())){
                    s2.add(s1.pop());
                }
                s1.push(item);//如果s1为空，或者栈顶运算符为（,或者当item的优先级下于等于s1栈顶的运算符,将item压入到s1中
                                //或者item的优先级比栈顶的高则入s1
            }
        }
        //将s1中剩余的运算符 加入到s2
        while (s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2;//因为是存放到一个list集合的，所以正常输出
    }

    //将逆波兰表达式，依次将数字和运算符放入ArrayList
        //已经被前面的操作淘汰了
    public static List<String> getListString(String suffixExpression){
        //将suffixExpression分割
            //Java中的我们可以利用split把字符串按照指定的分割符进行分割，然后返回字符串数组
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split) {
            list.add(ele);//通过遍历的方式 将数组中的元素存入集合
        }
        return list;
    }
    //完成对逆波兰表达式的运算
    /*
    * 1.从左到右扫描，将3与4压入堆栈(已经扫描完成，且数据在list集合中)
    * 2.遇到+运算符，因此弹出3和4 ，计算3+4，得7 ，将7入栈
    * 3.将5入栈
    * 4.接下来是* ，所以弹出5和7，计算出5*7=35，将35入栈
    * 5.将6入栈
    * 6.最后是 - ，计算35 - 6 的指，=29
    * */
    public static int cal (List<String> ls){
        //创建一个栈（只需一个栈）
        Stack<String> stack = new Stack<>();
        //遍历 ls（类似于综合计算器中的index扫描操作）
        for (String item : ls) {
            //这里使用正则表达式来取出数
                //matches() 方法用于检测字符串是否匹配给定的正则表达式
                    //  \\d+ 就表示多个数字，形如 12、44、6763……
            if (item.matches("\\d+")){
                //入栈
                stack.push(item);
            }else {//是一个运算符
                //pop出两个数，并运算,再入栈
                int num2 = Integer.parseInt(stack.pop());//将其转为int
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")){
                    res = num1 + num2;
                }else if (item.equals("-")){
                    res = num1 - num2;
                }else if (item.equals("/")){
                    res = num1 / num2;
                } else if (item.equals("*")){
                    res = num1 * num2;
                }else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(Integer.toString(res));//将res转为String并入栈
            }
        }
        return Integer.parseInt(stack.pop());//最后留在栈中的数据就是运算结果
    }
}
//编写一个类Operation 可以返回一个运算符对应的优先级
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;
    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation){
        int result = 0;
        switch (operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}
