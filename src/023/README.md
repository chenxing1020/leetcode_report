# 023.合并K个排序链表

## 问题描述

合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。  

```c
示例:
输入:
[
  1->4->5,
  1->3->4,
  2->6
]
输出: 1->1->2->3->4->4->5->6
```

## 解决方案

### 两两拼接

&emsp;&emsp;拿到题目没多想，我就直接按照顺序两两拼接了，这样一算，比较了k-1次，结点总数为n，时间复杂度为`O(kn)`，空间复杂度则为`O(n)`。

```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length==0) return null;
        ListNode res=new ListNode(0);
        res.next=lists[0];
        for (int i=1;i<lists.length;i++) res=merge(res,lists[i]);
        return res.next;
    }
    public ListNode merge(ListNode res,ListNode l){
        ListNode temp=res.next;
        if (temp==null) {
            res.next=l;
            return res;
        }
        ListNode pre=res;
        while (temp!=null && l!=null){
            if (temp.val>l.val) {
                ListNode ll=l;
                pre.next=ll;
                l=l.next;
                ll.next=temp;
                pre=ll;
            }else{
                if (temp.next==null) break;
                pre=temp;
                temp=temp.next;
            }
        }
        if (l!=null) temp.next=l;
        return res;
    }
}
```