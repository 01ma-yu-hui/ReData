package com.atguigu.algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: GreedyAlgorithm    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/11/10 11:40   // 时间
 * @Version: 1.0     // 版本
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建电台，放入到map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();  //HashSet可以去重
        //将各个电台放入到broadcasts
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //加入到map
        broadcasts.put("k1",hashSet1);
        broadcasts.put("k2",hashSet2);
        broadcasts.put("k3",hashSet3);
        broadcasts.put("k4",hashSet4);
        broadcasts.put("k5",hashSet5);

        //allAreas 存放所有的地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //创建一个ArrayList,存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();
        //定义一个临时的集合，在遍历过程中，存放遍历过程中的本电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<>();
        //定义一个maxKey，保存在一次遍历过程中，能够覆盖最大未覆盖地区对应的电台的key
        //如果maxKey不为null,则会加入到selects
        String maxKey = null;
        while (allAreas.size() != 0){//如果allAreas不为0，则表示还没有覆盖到所有的地区
            //遍历broadcasts，取出对应的key
            for (String key : broadcasts.keySet()) {//keySet():返回所有 key
                //当前key能够覆盖的地区
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);  //addAll():用于将指定 collection 中的所有元素添加到列表的尾部
                //求交集:将tempSet集合与allAreas集合中的交集元素都取出来，然后赋给tempSet
                tempSet.retainAll(allAreas);
                System.out.println("未完成");
            }
        }
    }
}
