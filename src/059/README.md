# 059.螺旋矩阵 II

## **问题描述**  

给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。

**示例**:  

```java
输入: 3
输出:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
```

## **解决方案**  

&emsp;&emsp;这题和054题目的思路完全一致，按层处理然后考虑边界条件即可，部分代码如下：

```java
int nLen=n-2*i;
//toRight
for (int j=0;j<nLen;j++){
    result[i][i+j]=num;
    num++;
}
//toDown
for (int j=1;j<nLen;j++){
    result[i+j][i+nLen-1]=num;
    num++;
}
```