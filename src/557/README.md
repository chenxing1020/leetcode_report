# 557.反转字符串中的单词 III

## **问题描述**  

给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。

**样例**：  
输入: "Let's take LeetCode contest"  
输出: "s'teL ekat edoCteeL tsetnoc"  

**注意**：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。  

## **解决方案**  

&emsp;&emsp;最开始的想法就是直接用`split()`对空格进行分割，然后对分割后的字符串进行拼接，代码如下：

```java
String[] arr=s.split(" ");
int aLen=arr.length;
for (int i=0;i<aLen;i++){
     char[] c=arr[i].toCharArray();
     for (int j=c.length-1;j>=0;j--){
          result+=c[j];
     }
     if (i!=aLen-1) result+=" ";
}
```

&emsp;&emsp;结果第一次提交直接TLE，将result换成`StringBuilder`类型之后，用`append()`连接，再次提交就通过了。  
>这里列出一些参考文档：  
>1.[Java字符串相加](https://blog.csdn.net/momocmg/article/details/52693298)  
>2.[String、StringBuffer、StringBuilder源码解读](https://www.cnblogs.com/snifferhu/p/5903958.html)  

&emsp;&emsp;为了保证线程安全，字符串的连接每次返回的都是一个新的String对象，在编译的时候，+号会被转成StringBuilder的append，连接完成后再执行toString()，所以在迭代拼接操作过程中会消耗极长的时间。

## **改进方案**  

&emsp;&emsp;提交之后，发现用时短的程序都是直接将字符串转成字符数组进行操作，比用split()方法的效率显然高了很多，所以，改进也是通过遍历字符数组记录空格位置，然后根据空格位置标记重组字符数组。

```java
public String reverseWords(String s) {
     if (s==null) return null;
     char[] c=s.toCharArray();
     int start=0;
     for (int i=0;i<c.length;i++){
          if (c[i]==' '){
               reverseWord(c,start,i);
               start=i+1;
          }
     }
     if (start<c.length) reverseWord(c,start,c.length);
     return String.copyValueOf(c);
}
public static void reverseWord(char[] ch,int start,int end){
     for (int i=start;i<(end+start)/2;i++){
          char temp=ch[end+start-1-i];
          ch[end+start-1-i]=ch[i];
          ch[i]=temp;
     }
}
```
