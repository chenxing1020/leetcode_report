# 005.最长回文子串

## 问题描述  

给定一个字符串`s`，找到`s`中最长的回文子串。你可以假设`s`的最大长度为 1000。  

```c
示例 1：
输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。

示例 2：
输入: "cbbd"
输出: "bb"  
```  

## 解决方案  

### 方案1：暴力搜索

&emsp;&emsp;时间复杂度`O(n^3)`。

### 方案2：动态规划

&emsp;&emsp;用`p(i,j)`来表示从i到j位置的子串是否是回文串，然后分别确定只含1个字符的回文串和含有2个相同字符的回文串，这样一来，3个以上的子串就可以通过以下状态方程确定：

    p(i,j)=p(i+1,j-1) && s(i)==s(j)

&emsp;&emsp;这种方法可以有效利用已知短串的信息，而不用每次搜索都重新开始，这种算法的时间复杂度为`O(n^2)`。

```java
class Solution {
    public String longestPalindrome(String s) {        
        char[] ss=s.toCharArray();
        int len=ss.length;
        if (len==0) return "";
        int [][] p=new int[len][len];
        //确定1回文和2回文
        for (int i=0;i<len;i++) {
            p[i][i]=1;
            if (i+1<len && ss[i]==ss[i+1]) p[i][i+1]=1;
        }
        //类推3回文、4回文......
        for (int i=2;i<len;i++)
            for (int j=0;j<len-i;j++)
                if (p[j+1][j+i-1]==1 && ss[j]==ss[j+i]) p[j][j+i]=1;
        //这里的循环可以和上边整合，为了看着方便再写一次
        int max=0,maxi=0,maxj=0;
        for (int i=0;i<len;i++)
            for (int j=i;j<len;j++)
                if (p[i][j]==1 && j-i+1>max){
                    max=j-i+1;
                    maxi=i;
                    maxj=j;
                }
        return s.substring(maxi,maxj+1);
    }
}
```

### 方案3：中心拓展

&emsp;&emsp;确定中心然后用左右指针两边走，不过要注意有2n-1个中心，因为要考虑整个回文串长度是奇数还是偶数的情况，这种算法的时间复杂度也是`O(n^2)`。

```java
public String longestPalindrome(String s) {
    if (s == null || s.length() < 1) return "";
    int start = 0, end = 0;
    for (int i = 0; i < s.length(); i++) {
        int len1 = expandAroundCenter(s, i, i);
        int len2 = expandAroundCenter(s, i, i + 1);
        int len = Math.max(len1, len2);
        if (len > end - start) {
            start = i - (len - 1) / 2;
            end = i + len / 2;
        }
    }
    return s.substring(start, end + 1);
}

private int expandAroundCenter(String s, int left, int right) {
    int L = left, R = right;
    while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
        L--;
        R++;
    }
    return R - L - 1;
}
```