# 061.旋转链表

## 问题描述

给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。  

```c
示例 1:
输入: 1->2->3->4->5->NULL, k = 2
输出: 4->5->1->2->3->NULL
解释:
向右旋转 1 步: 5->1->2->3->4->NULL
向右旋转 2 步: 4->5->1->2->3->NULL

示例 2:
输入: 0->1->2->NULL, k = 4
输出: 2->0->1->NULL
解释:
向右旋转 1 步: 2->0->1->NULL
向右旋转 2 步: 1->2->0->NULL
向右旋转 3 步: 0->1->2->NULL
向右旋转 4 步: 2->0->1->NULL
```

## 解决方案

&emsp;&emsp;使用两个指针，第一个指针用来计算整个链表的长度，顺便找到尾结点。然后第二个指针用于定位`len-len%k-1`位置的结点，即断链的位置，然后整体移动即可。

```java
class Solution{
    public ListNode rotateRight(ListNode head,int k){
        if (head==null) return null;
        int i;
        ListNode fast=head,slow=head;
        for (i=0;fast.next!=null;i++) fast=fast.next;
        i++;

        for (int j=0;j<i-k%i-1;j++) slow=slow.next;

        fast.next=head;
        head=slow.next;
        slow.next=null;
        return head;
    }
}
```