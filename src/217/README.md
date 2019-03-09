# 217.存在重复元素

## **问题描述**  

给定一个整数数组，判断是否存在重复元素。  

如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。  

**示例 1**：  
输入: [1,2,3,1]  
输出: true  

**示例 2**：  
输入: [1,2,3,4]  
输出: false  

**示例 3**：  
输入: [1,1,1,3,3,4,3,2,4,2]  
输出: true  

## **解决方案**  

&emsp;&emsp;两个思路，一个是对数组排序，然后遍历数组;另一个就是用hashset来实现。

```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> hs=new HashSet<>();
        for (int item:nums){
            if (!hs.contains(item)) hs.add(item);
            else return true;
        }
        return false;
    }
}
```