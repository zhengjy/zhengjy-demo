package com.zhengjy.offer.graph;

/**
 * Created by Jiyang.Zheng on 2018/7/8 12:08.
 */
public class MatrixUDG2 {

    private static int[][]  test(char[] vexs,char[][] edges){

        int[][] matrixs = new int[vexs.length][vexs.length];

        for (int i=0;i<vexs.length;i++){
            int c0 = getPoint(vexs,edges[i][0]);
            int c1 = getPoint(vexs,edges[i][1]);
            matrixs[c0][c1] = 1;
            matrixs[c1][c0] = 1;
        }
        return matrixs;

    }

    private static int getPoint(char[] vexs,char c){
        for (int i=0;i<vexs.length;i++){
            if (c == vexs[i]){
                return i;
            }
        }

        return -1;
    }

    private static void print(char[] vexs,int[][] matrixs){
        System.out.print("\t");
        for (int i=0;i<vexs.length;i++){
            System.out.print( vexs[i] +"\t");
        }
        System.out.printf("\n");
        for (int i=0;i<vexs.length;i++){
            System.out.print( vexs[i] +"\t");
            for (int j = 0; j < vexs.length; j++){
                System.out.print( matrixs[i][j] + "\t");
            }
            System.out.printf("\n");
        }
    }


    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        char[][] edges = new char[][]{
                {'A', 'C'},
                {'A', 'D'},
                {'A', 'F'},
                {'B', 'C'},
                {'C', 'D'},
                {'E', 'G'},
                {'F', 'G'}};


//        char[][] edges = new char[][]{
//                {'A', 'B'},
//                {'B', 'C'},
//                {'B', 'E'},
//                {'B', 'F'},
//                {'C', 'E'},
//                {'D', 'C'},
//                {'E', 'B'},
//                {'E', 'D'},
//                {'F', 'G'}};
        int[][] matrixs =  test(vexs,edges);

        print(vexs,matrixs);
    }
}
