package com.atguigu.algorithm.kmp;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: ViolenceMatch    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/11/6 15:13   // 时间
 * @Version: 1.0     // 版本
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        //测试暴力匹配算法
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";
        int index = violenceMatch(str1, str2);
        System.out.println(index);
    }

    //暴力匹配算法的实现
    public static int violenceMatch(String str1, String str2){
        char[] s1 = str1.toCharArray();//将主串转化为字符数组
        char[] s2 = str2.toCharArray();//将子串转化为字符数组

        int s1Len = s1.length;//得到主串数组的长度
        int s2Len = s2.length;//得到子串数组的长度

        int i = 0;  //i索引指向s1
        int j = 0;  //j索引指向s2

        while (i < s1Len && j < s2Len) {//保证在检索时不越界
            if (s1[i] == s2[j]){//说明匹配成功
                i++;//主串的指针后移
                j++;//子串的指针后移
            } else { //没有匹配成功,则i指针回溯(i=i-j+1),j指针置0(j=0)
                i = i-j+1;
                j = 0;
            }
        }
        //判断是否匹配成功
        if (j == s2Len){
            return i - j;
        }
            return -1;
    }
}
