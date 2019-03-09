# 231.2的幂

## **问题描述**  

给定一个整数，编写一个函数来判断它是否是 2 的幂次方。

**示例1**：  
输入: 1  
输出: true  

**示例2**：  
输入: 218  
输出: false

## **解决方案**

&emsp;&emsp;方案就是不断地 *对2取余*，如果余数不为0就`return false`，边界条件就是被除数为1的时候。另外，开头判断一下非负即可。

```java
public boolean isPowerOfTwo(int n) {
    if (n<1) return false;
    while (n>1) {
        if (n%2!=0) return false;
        n/=2;
    }
    return true;
}
```