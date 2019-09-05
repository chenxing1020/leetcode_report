# 718.最长重复子数组

## 问题描述

给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。  

### 示例

```c
输入:
A: [1,2,3,2,1]
B: [3,2,1,4,7]
输出: 3
解释:
长度最长的公共子数组是 [3, 2, 1]。
```

#### 说明

1. 1 <= len(A), len(B) <= 1000
2. 0 <= A[i], B[i] < 100

## 解决方案

昨天做了哈啰的内推笔试题，让求两个字符串的最长公共子串的长度，很长时间不刷题，完全没了思路。跟室友聊天才发现这道题以前做过，在力扣上找到了一个类似的题目，dp数组的含义即是dp[i][j]为末尾为A[i]和末尾为B[j]的子串的匹配长度，这样状态方程很容易就写出来了，说明刷题还是要持续，这样才能保持感觉。

```java
class Solution {
    public int findLength(int[] A, int[] B) {
        int la=A.length,lb=B.length;
        int[][] dp=new int[la][lb];
        int max=0;
        for (int j=0;j<lb;j++)
            if (A[0]==B[j]) {
                dp[0][j]=1;
                max=1;
            }
        for (int i=1;i<la;i++){
            for (int j=0;j<lb;j++){
                if (A[i]==B[j]) {
                    dp[i][j]=j>0?dp[i-1][j-1]+1:1;
                    if (dp[i][j]>max) max=dp[i][j];
                }
            }
        }
        return max;
    }
}
```
