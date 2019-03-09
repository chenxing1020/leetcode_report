# 053.最大子序和

## **问题描述**  

给定一个整数数组 `nums` ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

**示例**:  

```java
输入: [-2,1,-3,4,-1,2,1,-5,4],
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
```

## **解决方案**  

&emsp;&emsp;用动态规划的思想来解决这个题目，用一个数组max[i]来记录以i为结尾的子序和的最大值，那么就可以写出状态方程`max[i]=max(max[i-1]+nums[i],nums[i])`，进一步分析则是，`max[i]=nums[i]+max[i-1]>0?max[i-1]:0`。然后在遍历的时候记录max[i]的最大值即为最后的结果。另外，写完之后发现，不需要建立一个新数组，只需要将max[i]抽象成max每一次都更新结果即可。

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int max=nums[0];
        int result=nums[0];
        for (int i=1;i<nums.length;i++){
            if (max<0) max=nums[i];
            else max+=nums[i];
            if (max>result) result=max;
        }
        return result;
    }
}
```