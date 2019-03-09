# 043.字符串相乘

## 问题描述

给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。  

```c
示例 1:
输入: num1 = "2", num2 = "3"
输出: "6"

示例 2:
输入: num1 = "123", num2 = "456"
输出: "56088"
```

## 解决方案

&emsp;&emsp;按位逆序相乘相加，再加上移位即可。

```java
class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        char[] n1=new StringBuffer(num1).reverse().toString().toCharArray();
        char[] n2=new StringBuffer(num2).reverse().toString().toCharArray();
        int[] res=new int[n1.length+n2.length+1];
        int temp=0;
        for (int i=0;i<n1.length;i++){
            temp=i;
            int add=0;
            for (int j=0;j<n2.length;j++){
                int cout=(n1[i]-48)*(n2[j]-48)+res[temp]+add;
                res[temp++]=cout % 10;
                add=cout/10;
            }
            if (add!=0) res[temp]+=add;
        }
        if (res[temp]==0) temp--;
        String s=new String();
        for (int i=temp;i>=0;i--) s+=res[i]+"";
        return s;
    }
}
```