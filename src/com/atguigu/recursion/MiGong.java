package com.atguigu.recursion;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: MiGong    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/9/14 16:19   // 时间
 * @Version: 1.0     // 版本
 */
public class MiGong {
    public static void main(String[] args) {
        //先创建一个二维数组，模拟迷宫
            //地图
        int[][] map = new int[8][7];//实例化二维数组
        //使用1表示墙
            //上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
            //把左右全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
            //创建中间障碍物
        for (int i = 0; i < 3; i++) {
            map[3][i] = 1;
        }
        map[1][2] = 1;
        map[2][2] = 1;
        //输出地图的情况：
        System.out.println("地图的情况：");
        for (int[] ints : map) {
            for (int ints1 : ints) {
                System.out.print(ints1+"    ");
            }
            System.out.println();
        }
        //使用递归回溯，给小球找路
        setWay(map,1,1);
        //输出新的地图,小球走过，并标识过的地图
        System.out.println("小球走过，并标识过的地图：");
        for (int[] ints : map) {
            for (int ints1 : ints) {
                System.out.print(ints1+"    ");
            }
            System.out.println();
        }
    }
    //使用递归回溯来给小球找路。
    //说明
        //1. map表示地图
        //2. i ,j 表示从地图的哪个位置开始出发.eg(1,1)
        //3.如果小球可以到map[6][5]位置，则说明通路找到
        //4.约定：当map[i][j]为0时，表示该点没有走过。为1时，表示墙。为2时，表示通路可以走。为3时，表示该位置已经走过，但是走不通。
        //5.在走之前先定一个策略（方法）下-右-上-左的顺序
    /**
     * @param map 表示地图
     * @param i 从哪个位置开始找
     * @param j
     * @return 如果找到通路，就返回true
     */
    public static boolean setWay(int[][] map,int i ,int j){
        if (map[6][5] == 2){//说明通路已经找到
            return true;
        } else {
            if (map[i][j] == 0){//如果当前这个点还没有走过
                //按照策略下-右-上-左的顺序 走
                map[i][j] = 2;//假定该点是可以走通的
                if (setWay(map,i+1,j)){//向下走
                    return true;
                } else if (setWay(map,i,j+1)){//向右走
                    return true;
                } else if (setWay(map,i-1,j)){//向上走
                    return true;
                } else if (setWay(map,i,j-1)){//向左走
                    return true;
                } else {
                    //说明该点是走不通的,是死路
                    map[i][j] = 3;
                    return false;
                }
            }else {//如果map[i][j] ！= 0，可能是1 2 3
                return false;
            }
        }
    }
}
