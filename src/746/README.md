# 746.使用最小花费爬楼梯

## 问题描述

数组的每个索引做为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i] (索引从0开始)。  

每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。  

您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。  

```c
示例 1:
输入: cost = [10, 15, 20]
输出: 15
解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。

示例 2:
输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
输出: 6
解释: 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。

注意：
cost 的长度将会在 [2, 1000]。
每一个 cost[i] 将会是一个Integer类型，范围为 [0, 999]。
```

## 解决方案

&emsp;&emsp;这题是一个dp常规题，状态方程为`f(n)=min(f(n-1)+cost(n-1),f(n-2)+cost(n-2) n>=2`，然后需要注意，结束的点应该在`cost.length`即可。

```java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int[] p=new int[cost.length+1];
        p[0]=0;
        p[1]=0;
        for (int i=2;i<=cost.length;i++){
            p[i]=Math.min(p[i-1]+cost[i-1],p[i-2]+cost[i-2]);
        }
        return p[cost.length];
    }
}
```