# 046.全排列

## 问题描述

给定一个没有重复数字的序列，返回其所有可能的全排列。  

```c
示例:
输入: [1,2,3]
输出:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
```

## 解决方案

### DFS+递归

&emsp;&emsp;我自己的思路就是用一个数组来记录当前数组已经取数的情况，然后记录当前的取数，继续递归直到这一轮所有的数都取完，退出递归，并且清除这一轮的取数。

```java
class Solution {
    static List<List<Integer>> result;
    public List<List<Integer>> permute(int[] nums) {
        int[] m=new int[nums.length];
        result=new LinkedList<List<Integer>>();
        List<Integer> l=new LinkedList<>();
        permute1(nums,m,l);
        return result;
    }
    public void permute1(int[] nums,int[] m,List<Integer> l){
        for (int i=0;i<nums.length;i++){
            if (m[i]==0) {
                l.add(nums[i]);
                m[i]=1;
                if (l.size()==nums.length) result.add(new LinkedList(l));
                else permute1(nums,m,l);
                l.remove(l.size()-1);
                m[i]=0;
            }
        }
    }
}
```