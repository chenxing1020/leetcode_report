# 240.探索二维矩阵II

## 问题描述  

编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：

每行的元素从左到右升序排列。
每列的元素从上到下升序排列。

```c
示例:
现有矩阵 matrix 如下：
[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
给定 target = 5，返回 true。
给定 target = 20，返回 false。
```

## 解决方案  

### 方案1：分治

每次，找到所在区间的中心值，然后以该中心值作为判断，这样每次可以去掉1/4的区域检索范围。

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        return search(matrix, 0, matrix.length-1, 0, matrix[0].length-1, target);
    }
    public boolean search(int[][] matrix, int m0, int m1, int n0, int n1, int target) {
        if (m0>m1 || n0>n1) return false;
        int m=(m0+m1)>>1,
            n=(n0+n1)>>1;
        if (matrix[m][n] == target) return true;
        else if (matrix[m][n] > target) {
            boolean b1 = search(matrix, m0, m-1, n0, n1, target),
                    b2 = search(matrix, m, m1, n0, n-1, target);
            return (b1 || b2);
        } else {
            boolean b1 = search(matrix, m+1, m1, n0, n1, target),
                    b2 = search(matrix, m0, m, n+1, n1, target);
            return (b1 || b2);
        }
    }
}
```

时间复杂度`T(n) = 3T(n/4) + O(1)`。

### 方案2：双指针

因为行和列都是有序的，所以双指针可以从矩阵的左下角开始搜索，当target小于指针所在位置的数时，说明指针不需要往上走了（因为同一列往上递减），只需要往右走即可；当target大于指针所在位置的数时，说明指针不需要往右走了（因为同一行往右递增），只需要往上走即可。这样就能保证遍历m+n次就可以查找完毕。

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int i=matrix.length-1,
            j=0;
        while (i>=0 && j<matrix[0].length) {
            if (matrix[i][j] == target) return true;
            else if (matrix[i][j] < target) j++;
            else i--;
        }
        return false;
    }
}
```

时间复杂度：`O(m + n)`。