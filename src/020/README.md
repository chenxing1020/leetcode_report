# 020.有效的括号

## **问题描述**  

给定一个只包括 `'('`，`')'`，`'{'`，`'}'`，`'['`，`']'` 的字符串，判断字符串是否有效。  

有效字符串需满足：  

1. 左括号必须用相同类型的右括号闭合。
2. 左括号必须以正确的顺序闭合。

注意空字符串可被认为是有效字符串。

```c
示例 1：
输入: "()"
输出: true

示例 2：
输入: "()[]{}"
输出: true

示例 3:
输入: "(]"
输出: false

示例 4:
输入: "([)]"
输出: false

示例 5:
输入: "{[]}"
输出: true
```

## **解决方案**

&emsp;&emsp;思路基本上就是，遇到左括号就推进栈；遇到右括号就从栈里推出，如不匹配就退出。  
&emsp;&emsp;ps：要考虑每次推出栈是否为空（只有右括号没有左括号）或者结束时栈是否为空（只有左括号没有右括号）的情况。

```java
class Solution {
    public boolean isValid(String s) {
        if (s=="") return true;
        Stack<Character> stack=new Stack<>();

        for (int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if (ch=='[' || ch=='(' || ch=='{') stack.push(ch);
            else{
                if (stack.isEmpty()) return false;
                char cp=stack.pop();
                if ((ch==']' && cp!='[') || (ch==')' && cp!='(') || (ch=='}' && cp!='{'))
                    return false;
            }
        }
        return stack.isEmpty();
    }
}
```