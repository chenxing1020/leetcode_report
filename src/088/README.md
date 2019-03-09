# 088.合并两个有序数组

## 问题描述  

给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。  

说明:

* 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
* 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。  

```c
示例:
输入:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3
输出: [1,2,2,3,5,6]
```

## 解决方案

&emsp;&emsp;我直接的用的最蠢的方法（后来看到效率高的方法从末位开始递减，这样不用移位）。

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i=0,j=0;
        while (i<m && j<n){
            while (i<m && nums1[i]<nums2[j]) i++;
            if (i<m) {
                m++;
                for (int k=m-1;k>i;k--) nums1[k]=nums1[k-1];
                nums1[i++]=nums2[j++];
            }
        }
        if (j<n) {
            for (int k=m;j<n;k++,j++){
                nums1[k]=nums2[j];
            }
        }
    }
}
```