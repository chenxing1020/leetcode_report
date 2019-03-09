# 015.三数之和

## **问题描述**  

给定一个包含 n 个整数的数组 `nums`，判断 `nums` 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。  

**注意**：答案中不可以包含重复的三元组。  

```c
例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，

满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
]
```

## **解决方案**

&emsp;&emsp;因为要计算三个数的和，所以如果使用暴力搜索的话，时间复杂度就是`O(n^3)`，很显然不是一个好方法。所以就需要借助解答"two sum"的思路，想办法采用双指针的方法。  
&emsp;&emsp;要使三个数的和为0,那么三个数全0,否则必然存在负数。故，我们先将数组排序，然后依次处理负数`nums[i]`，将`target=0-nums[i]`作为[i+1,length]的目标和，就相当于转化成了一个求两数之和的题目。这样将指针`left`和`right`分别放置在i+1,和length位置处，不断比较指针位置数字之和与target的大小关系并移动指针，这样处理一个负数的时间复杂度为`o(n)`，整个程序时间复杂度就是`O(n^2)`。  
&emsp;&emsp;另外，题目要求不重复，所以在程序中要考虑去重，思路就是，如果当前遍历的值和它的前一个值相等，指针就沿着它移动的方向继续移动。  

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result=new ArrayList<>();
        Arrays.sort(nums);
        int j=0;
        while (j<nums.length && nums[j]<=0){
            //判重
            if (j-1 >= 0 && nums[j]==nums[j-1]) {
                j++;
                continue;
            }

            int target=0-nums[j], left=j+1, right=nums.length-1;;
            while (left<right){
                //判重
                if (left-1>j && nums[left]==nums[left-1]) {
                    left++;
                    continue;
                }
                if (right+1<nums.length && nums[right]==nums[right+1]) {
                    right--;
                    continue;
                }
                if (nums[left]+nums[right]==target) {
                    List<Integer> item=new ArrayList<>();
                    item.add(nums[j]);
                    item.add(nums[left]);
                    item.add(nums[right]);
                    result.add(item);
                    left++;right--;
                }else if (nums[left]+nums[right]<target) left++;
                else right--;
            }
            j++;
        }
        return result;
    }
}
```