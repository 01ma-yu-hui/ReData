package com.atguigu.algorithm.kmp;

import java.util.Arrays;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: KMPAlgorithm    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/11/6 16:59   // 时间
 * @Version: 1.0     // 版本
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";//主串
        String str2 = "ABCDABD";//子串
        int[] kmpNext = kmpNext(str2);
        int search = kmpSearch(str1, str2, kmpNext);
        System.out.println(search);
    }

    //写出KMP搜索算法

    /**
     * @param str1 文本串
     * @param str2 模式串
     * @param next 部分匹配值(公共前后缀)
     * @return 如果是-1就没有匹配到，否则返回第一个匹配的索引下标
     */
    public static int kmpSearch(String str1, String str2, int[] next){
        
        //遍历
        //i指向文本串，j指向模式串
        for (int i = 0, j = 0; i < str1.length(); i++) {

            //需要处理str1.charAt(i) != str2.charAt(j),去调整j的大小
            while (j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j - 1];    //当文本串与模式串匹配不上时，应该将J的前一个位置的next值赋给J
                // (即，将前缀的字符串放在后缀的位置上),然后继续看
            }
            if (str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if (j == str2.length()){
                return i - j + 1;
            }
        }
        return -1;
    }


    //获取到子串的部分匹配值

    /**
     * @param dest 将子串传入
     * @return 返回一个int[]数组的部分匹配值
     */
    public static int[] kmpNext(String dest){

        //创建一个next数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;//如果dest的长度为1，则部分匹配值为0。因为既没有前缀也没有后缀。
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //j指向公共前后缀前缀末尾位置(如果j=0，则无公共前后缀),可以当作最长相等前后缀长度
            //i指向后缀末尾位置，可以当作next数组的索引值
            //当dest.charAt(i) != dest.charAt(j)时，我们需要从next[j-1]获取新的j
            //直到dest.charAt(i) == dest.charAt(j)时，退出
            while (j > 0 && dest.charAt(i) != dest.charAt(j)){
                //当遇见冲突时，找前一位j对应的next数组的值，该值就是我们要跳到的位置。
                // （找next数组的原因：在前后缀相等的情况下，把前缀放在后缀的位置上，自然就匹配上了。）
                j = next[j - 1];//表示的含义：回溯，即当前3个字符的前后缀不相等，我得看看前面的2个字符的前后缀是否相等。
            }

            //当dest.charAt(i) == dest.charAt(j)时，部分匹配值就需要+1
            if (dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
