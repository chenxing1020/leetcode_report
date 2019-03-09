# 033.搜索旋转排序数组

## 问题描述

假设按照升序排序的数组在预先未知的某个点上进行了旋转。  

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。  

搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。  

你可以假设数组中不存在重复的元素。  

你的算法时间复杂度必须是 O(log n) 级别。  

```c
示例 1:
输入: nums = [4,5,6,7,0,1,2], target = 0
输出: 4

示例 2:
输入: nums = [4,5,6,7,0,1,2], target = 3
输出: -1
```

## 解决方案

&emsp;&emsp;因为看到题目的要求，时间的复杂度要在`O(logn)`级别，所以很容易想到二分法。观察旋转数列可以发现，从任何一个位置将数组一分为二，必定有一半是有序的，所以就以这个为前提进行二分。  
&emsp;&emsp;每次取中间点mid，如果`nums[start]<nums[mid]`，那么说明区间`[start,mid]`是有序的，否则`[mid,end]`是有序的，以此为条件不断进行二分查找。  
&emsp;&emsp;不过在我写完程序的时候，连着提交了4遍都出现了wrong answer，而且都是数组只有两个数的情况，排查之后发现在比较start和mid的值的时候，因为`mid=(start+end)/2`，所以只剩两个数的时候，start总是和mid相等的，而我刚开始的比较条件是`nums[start]<nums[mid]`，条件很有可能直接落到`binSearch(nums,start,mid-1,target)`上，从而导致并没有比对`nums[end]`和`target`，所以出现了4次错误，以后要谨慎考虑二分的退出情况（考虑只剩两个数的情况即可）。

```java
class Solution {
    public int search(int[] nums, int target) {
        if (nums.length==0) return -1;
        return binSearch(nums,0,nums.length-1,target);
    }
    
    public int binSearch(int[] nums,int start,int end,int target){
        if (start>end) return -1;
        int mid=(start+end)/2;
        if (nums[mid]==target) return mid;
       
        //区间[start,mid]有序
        if (nums[start]<=nums[mid]){
            if (nums[start]<=target && target<nums[mid]) return binSearch(nums,start,mid-1,target);
            else return binSearch(nums,mid+1,end,target);
        }
        //区间[mid,end]有序
        else{
            if (nums[mid]<target && target<=nums[end]) return binSearch(nums,mid+1,end,target);
            else  return binSearch(nums,start,mid-1,target);
        }
    }
}
```