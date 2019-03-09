# 141.环形链表

## 问题描述  

给定一个链表，判断链表中是否有环。  

为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。  

```c
示例 1：
输入：head = [3,2,0,-4], pos = 1
输出：true
解释：链表中有一个环，其尾部连接到第二个节点。
```

![image](./141_1.png)

```c
示例 2：
输入：head = [1,2], pos = 0
输出：true
解释：链表中有一个环，其尾部连接到第一个节点。  
```

![image](./141_2.png)

```c
示例 3：
输入：head = [1,2], pos = 0
输出：true
解释：链表中有一个环，其尾部连接到第一个节点。  
```

![image](./141_3.png)

**进阶**：你能用`O(1)`（即常量）内存解决此问题吗？

## 解决方案  

&emsp;&emsp;这个题目有一个很巧妙的方法，就是设置两个快慢指针，慢的指针每次只走一个结点，快的指针每次走两个结点，如果链表存在环路，那么这两个指针一定可以在环路中相遇。  

```java
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head==null) return false;
        ListNode a=head,b=head;
        while (a!=null && b!=null){
            a=a.next;
            b=b.next;
            if (b==null) return false;
            b=b.next;
            if (a==b) break;
        }
        if (a==b) return true;
        return false;
    }
}
```