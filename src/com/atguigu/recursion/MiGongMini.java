package com.atguigu.recursion;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: MiGongMini    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/9/14 21:04   // 时间
 * @Version: 1.0     // 版本
 */
public class MiGongMini {
    public static void main(String[] args) {
        //创建地图
        int[][] map =  new int[5][4];
        //设置障碍物
        for (int i = 0; i < 4; i++) {
            map[0][i] = 1;
            map[4][i] = 1;
        }
        for (int i = 0; i < 5; i++) {
            map[i][0] = 1;
            map[i][3] = 1;
        }
        map[3][1] = 1;
        System.out.println("初始状态：");
        for (int[] ints : map) {
            for (int ints1 : ints) {
                System.out.print(ints1+"    ");
            }
            System.out.println();
        }
        setM(map,1,1);
        System.out.println("路径：");
        for (int[] ints : map) {
            for (int ints1 : ints) {
                System.out.print(ints1+"    ");
            }
            System.out.println();
        }

    }
    //创建迷宫方法
    public static boolean setM(int[][] map,int i,int j){
        if (map[3][2] == 2){
            return true;
        }else {
            if (map[i][j] == 0){
                map[i][j] = 2;//假定可以走
                if (setM(map,i+1,j)){//向下走
                    return true;
                }else if (setM(map ,i,j+1)){//向右走
                    return true;
                }else if (setM(map,i-1,j)){//向上走
                    return true;
                }else if (setM(map,i,j-1)){//向左走
                    return true;
                }else {
                    map[i][j] = 3;
                    return false;
                }
            }else {
                return false;
            }
        }
    }
}
