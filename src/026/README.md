# 026.删除排序数组中的重复项

## **问题描述**  

给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。

不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成

**示例1**：  
给定数组 nums = [1,1,2],  
函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。  
你不需要考虑数组中超出新长度后面的元素。  

**示例2**：  
给定 nums = [0,0,1,1,1,2,2,3,3,4],  
函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。  
你不需要考虑数组中超出新长度后面的元素。  

## **解决方案**

&emsp;&emsp;刚开始的思路是每次出现一个新的数，就记录该数字重复的次数`shift`，然后将后面的数字依次前移shift位。

```java
public int removeDuplicates(int[] nums) {
    int i = 1, ti = 0, len = nums.length, tmp;
    if (len == 0) return 0;
    else tmp = nums[0];
    while (i < len) {
        while (i < len && tmp == nums[i]) i++;
        int shift = i - ti - 1;
        len -= shift;
        for (int j = ti + 1; j < len; j++)
            nums[j] = nums[j + shift];
        ti++;
        tmp = nums[ti];
        i = ti + 1;
    }
    return len;
}
```

&emsp;&emsp;这种方法每次有一个新的数字都进行`shift`次数组的移动，浪费了很多运行时间，看用时较少的算法，采用了双指针的方法。

## **改进方案**  

&emsp;&emsp;实际上发现，不需要进行移位操作，只需要依次将非重复的数字填进数组即可。改进算法利用双指针，count变量所记录的非重复数字的个数，实际上也是非重复数字在新数组中的位置，依次填入即可，代码如下：

```java
public int removeDuplicates(int[] nums) {
    int count = 0, i = 0, len = nums.length;
    if (len == 0) return 0;
    while (i < len) {
        while (i < len && nums[count] == nums[i]) i++;
        if (i < len) nums[++count] = nums[i];
    }
    return count + 1;
}
```