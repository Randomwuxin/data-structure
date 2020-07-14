package com.zzy.sparsearray;

import java.io.*;

/**
 * @Description 稀疏数组
 * @Author Zeng.zy
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2020/7/13
 */
public class SparseArray {
    public static void main(String[] args) {
        //创建二维数组,1为黑子，2为白子
        int chessArr1[][] = new int [11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        System.out.println("原始二维数组为");
        for (int [] row :chessArr1) {
            for (int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //二维数组转稀疏数组
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0){
                    sum++;
                }
            }
        }
        System.out.println(sum);
        int [][] sparseArr = new int [sum + 1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        int count = 1;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0){
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                    count++;
                }
            }
        }
        //输出稀疏数组
        System.out.println("稀疏数组为");
        for (int [] row : sparseArr) {
            for ( int data: row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //将稀疏数组写入文件
        File file = new File("F:\\BaiduNetdiskDownload\\SparseArray.txt");
        try {
            if (!file.exists()){
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            for (int [] row : sparseArr) {
                for ( int data: row) {
                    fw.write(data + "\t");
                }
                fw.write("\n");
            }
            fw.close();
            System.out.println("写入成功");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //从文件中读取稀疏数组
        int [][] sparseArr2 = new int[sum+1][3];
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";
            int row = 0;
            while((line = br.readLine()) != null){
//                System.out.println(line);
                String[] tem = line.split("\t");
                System.out.println(tem);
                for (int i = 0; i < tem.length; i++) {
                    sparseArr2[row][i] = Integer.parseInt(tem[i]);
                }
                row++;
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //从读出来的稀疏数组转二维数组
        //1、先根据稀疏数组第一行确定二维数组几行几列
        int [][] chessArr2 = new int [sparseArr2[0][0]][sparseArr2[0][1]];
        //2、根据后几行数据，赋值给二维数组
        for (int i = 1; i < sparseArr2.length; i++) {
            chessArr2[sparseArr2[i][0]][sparseArr2[i][1]] = sparseArr2[i][2];
        }
        System.out.println("转换后的二维数组");
        for (int [] row :chessArr2) {
            for (int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}
