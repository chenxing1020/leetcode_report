# 344.反转字符串

## **问题描述**  

编写一个函数，其作用是将输入的字符串反转过来。

**示例 1**:  
输入: "hello"  
输出: "olleh"  

**示例 2**:  
输入: "A man, a plan, a canal: Panama"  
输出: "amanaP :lanac a ,nalp a ,nam A"  

## **解决方案**  

&emsp;&emsp;这题没有什么实质性的内容，转换为字符数组后按下标操作即可，额外需要了解一下java的字符串和字符数组的互相转换`s.toCharArray()`以及`String.copyValueOf(c)`。

```java
class Solution {
    public String reverseString(String s) {
        int len=s.length();
        char[] c=new char[len];
        c=s.toCharArray();
        for (int i=0;i<len/2;i++){
            char temp=c[len-i-1];
            c[len-i-1]=c[i];
            c[i]=temp;
        }
        return String.copyValueOf(c);
    }
}
```