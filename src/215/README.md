# 215.数组中的第K个最大元素

## **问题描述**  

在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

**示例1**:  
输入: [3,2,1,5,6,4] 和 k = 2  
输出: 5  

**示例2**:  
输入: [3,2,3,1,2,4,5,5,6] 和 k = 4  
输出: 4  

**说明**:  
你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。  

## **解决方案**  

&emsp;&emsp;题目要找到数组中的第K个最大的元素，理所当然想到对这个数组进行降序排序，然后找到排序好的数组中的第k个元素即可，但是很多排序的时间复杂度都达到了`O(n^2)`，很显然有点暴力的感觉，所以可以用归并和快速排序来解决这个问题。  
&emsp;&emsp;对比归并和快排的思想，很快能发现快速排序的思想其实就是找到基准数在排序后数组中的位置，也就是说每一轮快排结束后我们都能确定一个数在有序数组中的位置。  
&emsp;&emsp;所以立马产生一个思路，确定了基准数的位置M，M左边都是比基准数大的数，右边都是比基准数小的数，如果M>K，那么要找的数就在基准数左边，否则就在基准数的右边，这样就可以不断地缩小快排的范围，直到找到位置是K的基准数。

```java
public int findKthLargest(int[] nums, int k) {
    return findK(nums, 0, nums.length - 1, k);
}

public int findK(int[] arr, int start, int end, int k) {

    int lo = start;
    int hi = end;
    //取子序列的第一个数字为基准数
    int pivot = arr[start];
    while (lo < hi) {
        while (lo < hi && arr[hi] <= pivot) hi--;
        while (lo < hi && arr[lo] >= pivot) lo++;
        if (lo < hi) {
            int temp = arr[lo];
            arr[lo] = arr[hi];
            arr[hi] = temp;
        }
    }
    arr[start] = arr[lo];
    arr[lo] = pivot;

    //当前基准数在子序列中的位置
    int n = lo - start + 1;
    if (n < k) return findK(arr, lo + 1, end, k - n);
    if (n > k) return findK(arr, start, lo - 1, k);
    return arr[lo];
}
```