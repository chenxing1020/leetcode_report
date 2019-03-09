# 078.子集

## 问题描述  

给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。  

说明：解集不能包含重复的子集。  

```c
示例:
输入: nums = [1,2,3]
输出:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
```

## 解决方案

&emsp;&emsp;我的思路就是首先建立一个空集，然后按序取数，然后将当前的结果集拷贝一份，新拷贝的集合中每一个元素都加上刚取的数，再将新集合添加到结果集中，这样就能保证遍历所有子集了。

```java
class Solution {
    List<List<Integer>> list;
    public List<List<Integer>> subsets(int[] nums) {
        list=new LinkedList<>();
        list.add(new LinkedList());
        if (nums.length==0) return list;
        for (int i=0;i<nums.length;i++){
            int len=list.size();
            for (int j=0;j<len;j++){
                List<Integer> t1=new LinkedList(list.get(j));
                t1.add(nums[i]);
                list.add(t1);
            }
        }
        return list;
    }
}
```