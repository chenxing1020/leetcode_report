# 031.下一个排列

## 问题描述  

实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

必须原地修改，只允许使用额外常数空间。

以下是一些例子，输入位于左侧列，其相应输出位于右侧列。

```c
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
```  

## 解决方案  

可以总结出字典序排列的规则，每次从排列的最右边开始遍历，当找到第一个当前数字比前面一个数字大的，说明从当前数字（假设为`nums[i]`)后面均是降序排列的（也就是说区间`[i,nums.length]`已经无法再继续排列了），所以这个时候需要更新`nums[i-1]`的值，这个时候更新的策略是将区间`[i,nums.length]`重新升序排序，然后将该区间内第一个比`nums[i-1]`值大的数字与`nums[i-1]`交换位置。

下面用一组数据来模拟这个过程。

```c
当前序列[4,2,5,3,1]

首先找到降序排列的头
[4,2,5,3,1]
     ⬆
重新排序，找到比nums[i-1]=2大的值
[4,2,1,3,5]
       ⬆
交换位置
[4,3,1,2,5]
即找到下一个排列
```

```java
class Solution {
    public void nextPermutation(int[] nums) {
        int i=nums.length-1;
        while (i>=1 && nums[i]<=nums[i-1]) i--;
        for (int j=i;j<nums.length-1;j++){
            for (int k=nums.length-1;k>j;k--){
                if (nums[k]<nums[k-1]) {
                    int temp=nums[k];
                    nums[k]=nums[k-1];
                    nums[k-1]=temp;
                }
            }
        }
        if (i!=0) {    
            int flag=i;
            while (nums[flag]<=nums[i-1]) flag++;
            int temp=nums[flag];
            nums[flag]=nums[i-1];
            nums[i-1]=temp;
        }
    }
}
```

这题的测试用例里面存在数值相等的值，所以在找降序排列头和最后交换位置的地方需要加上判断相等的条件。