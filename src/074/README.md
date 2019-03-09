# 74.搜索二维矩阵

## **问题描述**  

编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：

* 每行中的整数从左到右按升序排列。
* 每行的第一个整数大于前一行的最后一个整数。

**示例1**:  

```java
输入:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
输出: true
```  

**示例2**:  

```java
输入:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 13
输出: false
```  

## **解决方案**  

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;
        if (n == 0) return false;

        int row = 0;
        while (row < m && matrix[row][0] <= target) row++;
        if (row > 0) row--;

        int begin = 0, end = n - 1, mid = end / 2;
        while (begin <= end) {
            if (target == matrix[row][mid]) return true;
            else if (target < matrix[row][mid]) end = mid - 1;
            else begin = mid + 1;
            mid = (begin + end) / 2;
        }

        return false;
    }
}
```