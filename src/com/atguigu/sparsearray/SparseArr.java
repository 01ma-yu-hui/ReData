package com.atguigu.sparsearray;

/**
 * @Description: // 类说明，在创建类时要填写
 * @ClassName: SparseArr    // 类名，会自动填充
 * @Author: MYH          // 创建者
 * @Date: 2022/8/20 22:58   // 时间
 * @Version: 1.0     // 版本
 */
public class SparseArr {
    public static void main(String[] args) {
        //先创建一个原始的二维数组11*11
        //0表示没有子，1表示黑子，2表示蓝子
        int[][] chessArr1=new int[11][11];//对数组进行初始化
        chessArr1[1][2]=1;
        chessArr1[2][3]=2;
        chessArr1[10][9]=1;
        System.out.println("原始的二维数组！");
        for (int[] row:chessArr1){
            for (int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        //将二维数组 转 为稀疏数组
        //1.先遍历二维数组 得到非零数据的个数
        int sum=0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1.length; j++) {
                if (chessArr1[i][j]!=0){
                    sum++;
                }
            }
        }
        System.out.println("sum="+sum);

        //2.创建对应的稀疏数组
        int[][] sparseArr =new int[sum+1][3];
        //3.给稀疏数组赋值
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=sum;
        //4.遍历二维数组，将非零的值存放到稀疏数组中。
        int count=0;//用于记录是第几个非零数据
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1.length; j++) {
                if (chessArr1[i][j]!=0){
                    count++;
                    sparseArr[count][0]=i;
                    sparseArr[count][1]=j;
                    sparseArr[count][2]=chessArr1[i][j];
                }
            }
        }
        System.out.println("稀疏数组为:");
        for (int[] row:sparseArr){
            for (int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        //将稀疏数组恢复为原来的二维数组
        //1.先读取稀疏数组的第一行 得到二维数组。
        int[][] chessArr2=new int[sparseArr[0][0]][sparseArr[0][1]];
        //2.在读取稀疏数组后几行的数据(从第二行开始)，并赋值给原始的二维数组。
        for (int i = 1; i < sparseArr.length; i++) {
                    chessArr2[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }
        System.out.println("原始数组为：");
        for (int[] row :chessArr2){
            for (int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}