# 062.不同路径

## 问题描述  

一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。  

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。  

问总共有多少条不同的路径？  

说明：m 和 n 的值均不超过 100。  

```c
示例 1:
输入: m = 3, n = 2
输出: 3
解释:
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -> 向右 -> 向下
2. 向右 -> 向下 -> 向右
3. 向下 -> 向右 -> 向右

示例 2:
输入: m = 7, n = 3
输出: 28
```

## 解决方案

&emsp;&emsp;看到题目就能联想到动态规划，因为每一个格子的状态都可以是由有限个状态决定的（它左边的格子和上边的格子），状态方程也很简单`f(i,j)=f(i-1,j)+f(i,j-1)`。当然这里在遍历的时候需要注意(0,0)的位置，因为这个错误排查了好几遍orz

```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] res=new int[m][n];
        res[0][0]=1;
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (i==0 && j==0) continue;
                res[i][j]=(i>0?res[i-1][j]:0) + (j>0?res[i][j-1]:0);
            }
        }
        return res[m-1][n-1];
    }
}
```