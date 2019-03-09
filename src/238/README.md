# 238.除自身以外数组的乘积

## **问题描述**  

给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。

**示例**：  
输入: [1,2,3,4]  
输出: [24,12,8,6]

**说明**：  
请**不要使用除法**，在O(n)时间复杂度内完成此题。  

**进阶**：  
你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）

## **解决方案**

&emsp;&emsp;因为题目要求不可以使用除法，又需要保证 `O(n)` 的复杂度，所以第一个思路就是利用多余的空间来实现。  
&emsp;&emsp;因为是线性的复杂度，所以可以想到的操作就是`累乘`，那么累乘操作可以得到什么呢？用示例数据举例，依次顺序累乘得到`left=[1,2,6,24]`，那么可以发现，第i个位置的数左边的结果就是`nums[0]*nums[1]*···*nums[i-1]`，写到这里是不是可以想到，如果倒序累乘，那么第i个位置的数的右边的结果就自然是`nums[n]*nums[n-1]*···*nums[i+1]`了。那么最后结果就是`result[i]=left[i-1]*right[i+1]`，这样就执行了2n次循环，满足 `O(n)` 的时间复杂度要求。

```java
public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    if (n == 1) return nums;
    int[] left = new int[n], right = new int[n];
    int[] result = new int[n];
    left[0] = nums[0];
    right[n - 1] = nums[n - 1];
    for (int i = 1; i < n; i++) {
        left[i] = nums[i] * left[i - 1];
        right[n - i - 1] = nums[n - i - 1] * right[n - i];
    }
    result[0] = right[1];
    result[n - 1] = left[n - 2];
    for (int i = 1; i < n - 1; i++) {
        result[i] = left[i - 1] * right[i + 1];
    }
    return result;
}
```

## **进阶**

&emsp;&emsp;其实第一个思路出来之后，就很容易想到进阶的算法了。只需要将顺序累乘的结果当作输出，然后倒序累乘的时候一边用变量记录，一边直接算出结果即可。这样就满足常数空间复杂度了。

```java
public int[] productExceptSelf(int[] nums) {
    int n = nums.length, left = 1, right = 1;
    int[] result = new int[n];
    for (int i = 0; i < n; i++) {
        result[i] = left;
        left *= nums[i];
    }
    for (int j = n - 1; j >= 0; j--) {
        result[j] *= right;
        right *= nums[j];
    }
    return result;
}
```