package com.lzhsite.leetcode.algoritom.practise.dynamicProgramming;
/*

01背包问题，是用来介绍动态规划算法最经典的例子，网上关于01背包问题的讲解也很多，我写这篇文章力争做到用最简单的方式，最少的公式把01背包问题讲解透彻。
01背包的状态转换方程 f[i,j] = Max{ f[i-1,j-Wi]+Pi( j >= Wi ),  f[i-1,j] }
f[i,j]表示在前i件物品中选择若干件放在承重为 j 的背包中，可以取得的最大价值。
Pi表示第i件物品的价值。
决策：为了背包中物品总价值最大化，第 i件物品应该放入背包中吗 ？ 

 题目描述：

有编号分别为a,b,c,d,e的五件物品，它们的重量分别是2,2,6,5,4，它们的价值分别是6,3,5,4,6，
现在给你个承重为10的背包，如何让背包里装入的物品具有最大的价值总和？

name 	weight 	value 	1 	2 	3 	4 	5 	6 	7 	8 	9 	10
a 	2 	6 	0 	6 	6 	9 	9 	12 	12 	15 	15 	15
b 	2 	3 	0 	3 	3 	6 	6 	9 	9 	9 	10 	11
c 	6 	5 	0 	0 	0 	6 	6 	6 	6 	6 	10 	11
d 	5 	4 	0 	0 	0 	6 	6 	6 	6 	6 	10 	10
e 	4 	6 	0 	0 	0 	6 	6 	6 	6 	6 	6 	6

只要你能通过找规律手工填写出上面这张表就算理解了01背包的动态规划算法。

首先要明确这张表是至底向上，从左到右生成的。


f[i-1,j]表示我有一个承重为8的背包，当只有物品b,c,d,e四件可选时，这个背包能装入的最大价值
f[i-1,j-Wi]表示我有一个承重为6的背包（等于当前背包承重减去物品a的重量），当只有物品b,c,d,e四件可选时，这个背包能装入的最大价值
f[i-1,j-Wi]就是指单元格b6,值为9，Pi指的是a物品的价值，即6
由于f[i-1,j-Wi]+Pi = 9 + 6 = 15 大于f[i-1,j] = 9，所以物品a应该放入承重为8的背包*/
public class BackPack {
	public static void main(String[] args) {
        int m = 10;
        int n = 3;
        int w[] = {3, 4, 5};
        int p[] = {4, 5, 6};
        int c[][] = BackPack_Solution(m, n, w, p);
        for (int i = 1; i <=n; i++) {
            for (int j = 1; j <=m; j++) {
                System.out.print(c[i][j]+"\t");
                if(j==m){
                    System.out.println();
                }
            }
        }
        //printPack(c, w, m, n);

    }

 /**
     * @param m 表示背包的最大容量
     * @param n 表示商品个数
     * @param w 表示商品重量数组
     * @param p 表示商品价值数组
     */
    public static int[][] BackPack_Solution(int m, int n, int[] w, int[] p) {
        //f[i][v]表示前i件物品恰放入一个重量为m的背包可以获得的最大价值
        int f[][] = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++)
            f[i][0] = 0;
        for (int j = 0; j < m + 1; j++)
            f[0][j] = 0;

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                //当物品为i件重量为j时，如果第i件的重量(w[i-1])小于重量j时，f[i][j]为下列两种情况之一：
                //(1)物品i不放入背包中，所以f[i][j]为f[i-1][j]的值
                //(2)物品i放入背包中，则背包剩余重量为j-w[i-1],所以f[i][j]为f[i-1][j-w[i-1]]的值加上当前物品i的价值
               
            	if (w[i - 1] <= j) {
                    //物品i放入背包中的产生的最大价值，是否超过物品i不放入背包中产生的最大价值	
                    if (f[i - 1][j] < (f[i - 1][j - w[i - 1]] + p[i - 1]))
                        f[i][j] = f[i - 1][j - w[i - 1]] + p[i - 1];
                    else
                        f[i][j] = f[i - 1][j];
                } else
                	//第i个物品已经放不下了
                    f[i][j] = f[i - 1][j];
            }
        }
        return f;
    }
}
