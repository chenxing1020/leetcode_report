# 169.求众数

## **问题描述**  

给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。

你可以假设数组是非空的，并且给定的数组总是存在众数。

**示例1**：  
输入: [3,2,3]  
输出: 3  

**示例2**：  
输入: [2,2,1,1,1,2,2]  
输出: 2  

## **解决方案**

### **散列表**  

&emsp;&emsp;第一种方案就是直接用散列表(HashMap)来记录相同元素出现的次数，很显然这种算法的时间复杂度是 `O(n)`，空间复杂度也是 `O(n)`。

```java
public int majorityElement(int[] nums) {
    Map < Integer, Integer > map = new HashMap < Integer, Integer > ();
    int n = nums.length;
    if (n == 1) return nums[0];
    for (int num: nums) {
        if (map.containsKey(num)) {
            int tmp = map.get(num) + 1;
            if (tmp > n / 2) return num;
            map.put(num, tmp);
        } else map.put(num, 1);
    }
    return 0;
}
```

### **摩尔投票法**  

&emsp;&emsp;提交之后发现用时排到了很后面，看了一下大佬们提交的程序，原来很简单的题目也有一些很巧妙的算法，这一题由于必定存在众数，即次数出现大于 ⌊ n/2 ⌋。  
&emsp;&emsp;所以可以使用摩尔投票法 `Moore Voting`，这种方法是假设第一个数字为众数，并将计数器置1。之后开始比较数组的元素，如果当前众数和元素相同，则计数器自增1，否则就自减1。当计数器为零的时候，就将当前元素设为众数。这样做的前提就是，众数的个数是过半的。所以不论众数的位置在数组中的什么位置，被筛选出来的数都一定是正确的。这个算法的时间复杂度为 `O(n)`，空间复杂度为 `O(1)`。

```java
public int majorityElement(int[] nums) {
    int count = 0, result = 0;
    for (int num: nums) {
        if (count == 0) result = num;
        if (result == num) count++;
        else count--;
    }
    return result;
}
```