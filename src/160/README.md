# 160.相交链表

## **问题描述**  

编写一个程序，找到两个单链表相交的起始节点。  

如下面的两个链表：  

![image](./1.png)  

在节点c1开始相交。  

**示例 1**：  
![image](./2.png)  
输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3  
输出：Reference of the node with value = 8  
输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。  

**示例 2**：  
![image](./3.png)  
输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1  
输出：Reference of the node with value = 2  
输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。  

**示例 3**：  
![image](./4.png)  
输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2  
输出：null  
输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。  
解释：这两个链表不相交，因此返回 null。  

**注意**：

* 如果两个链表没有交点，返回 null.
* 在返回结果后，两个链表仍须保持原有的结构。
* 可假定整个链表结构中没有循环。
* 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。

## **解决方案**  

&emsp;&emsp;这题我没想到什么比较好的思路，就是直接来了一个暴力的搜索，果然用时超久，只超过了2%的解题方法orz

```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        while (headA!=null){
            if (isB(headA,headB)) return headA;
            headA=headA.next;
        }
        return null;
    }
    
    public boolean isB(ListNode a,ListNode headB){
        while(headB!=null){
            if (a==headB) return true;
            headB=headB.next;
        }
        return false;
    }
}
```

## 优化

&emsp;&emsp;看了比较高效的解法真的跪服！思路大概是这样的，假设A和B链表存在交点，记交点之后的链表为C，那么A和B链表各自可以拆分成`AA+C+Null`和`BB+C+Null`的形式。  
&emsp;&emsp;这时就会出现两种情况：

* AA和BB长度相等
* AA和BB长度不等

&emsp;&emsp;等长的情况很好考虑，直接设置两个指针pA和pB分别从两个链表的头结点开始遍历，当pA的结点和pB的结点相等时就找到了相交的结点，如果两个指针都等于null的时候就是没有相交结点。  
&emsp;&emsp;对于不等长的情况，就要想办法让它变成等长的情况进行处理，因为等长的情况处理起来非常简单。这里的思路是，在遍历到链表结尾的时候，就将指针指向另一个链表的头，就相当于遍历两个新链表：`AA+C+Null+BB+C+Null`和`BB+C+Null+AA+C+Null`，这两个链表是等长的，而且如果A和B链表存在相交的链表，那么指针pA和pB一定是在相交结点处相等的。

```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA==null || headB==null) return null;
        ListNode pA=headA;
        ListNode pB=headB;
        while(pA!=pB){
            if (pA==null) pA=headB;
            else pA=pA.next;
            
            if (pB==null) pB=headA;
            else pB=pB.next;
        }
        return pA;
    }
}
```