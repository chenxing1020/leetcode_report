# 142.环形链表II

## 问题描述  

给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 `null`。  

为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。  

说明：不允许修改给定的链表。

```c
示例 1：
输入：head = [3,2,0,-4], pos = 1
输出：tail connects to node index 1
解释：链表中有一个环，其尾部连接到第二个节点。
```

![image](./142_1.png)

```c
示例 2：
输入：head = [1,2], pos = 0
输出：tail connects to node index 0
解释：链表中有一个环，其尾部连接到第一个节点。  
```

![image](./142_2.png)

```c
示例 3：
输入：head = [1], pos = -1
输出：no cycle
解释：链表中没有环。
```

![image](./142_3.png)

**进阶**：你能用`O(1)`（即常量）内存解决此问题吗？

## 解决方案  

&emsp;&emsp;鉴于141题已经给这题铺好路了，所以首先还是用快慢指针判断链表是否存在环路。  
&emsp;&emsp;接下来的处理就比快慢指针更加巧妙了，假设慢指针从头结点`Head`到环的入结点`Enter`之间的距离为`A`，慢指针从`Enter`到和快指针相遇的结点`Meet`之间走过的距离为`B`（长度可能大于环的长度，即可能绕了若干圈）。由于快指针的走过的路程永远是慢结点的两倍，所以快指针走过的路程自然就是`2(A+B)`。  
&emsp;&emsp;接着进行逻辑推理，从`Enter`经过长度`B`可以到达`Meet`，而通过快指针的路程说明，从`Enter`经过长度`A+2B`也可以到达`Meet`，那么说明`A+B`即为环的长度的整数倍。  
&emsp;&emsp;所以，此时处在`Meet`位置上的慢指针再走`A`长度就又可以回到`Enter`结点，正好从`Head`到`Enter`的路程也为`A`，所以只要再设置一个指针p从头结点开始和慢指针一起走过`A`路程就一定可以在`Enter`结点处相遇了。(这个方法真的是太巧妙了Orz！)

```java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode left=head,right=head;
        while (left!=null && right!=null){
            left=left.next;
            right=right.next;
            if (right!=null) right=right.next;
            if (left==right) break;
        }
        if (left==right && left!=null){
            ListNode left1=head;
            while (true){
                if (left==left1) return left;
                left1=left1.next;
                left=left.next;
            }
        }
        return null;
    }
}
```