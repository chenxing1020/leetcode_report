# 138.复制带随机指针的链表

## 问题描述  

给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。  

要求返回这个链表的深拷贝。  

![1](./138.png)

```c
输入：
{"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}
解释：
节点 1 的值是 1，它的下一个指针和随机指针都指向节点 2 。
节点 2 的值是 2，它的下一个指针指向 null，随机指针指向它自己。
```

## 解题思路

&emsp;&emsp;第一次遍历的时候将原始链表的每一个节点都拷贝一遍，然后将对应节点存进map中。第二次遍历再利用map处理random节点。

```java
class Solution {
    public Node copyRandomList(Node head) {
        if (head==null) return null;
        Map<Node,Node> map=new HashMap<>();
        Node newHead=new Node(head.val,null,null);
        Node p=newHead,q=head;
        while (q!=null){
            map.put(q,p);
            if (q.next!=null) {
                Node newNext=new Node(q.next.val,null,null);
                p.next=newNext;
            }
            q=q.next;
            p=p.next;
        }
        p=newHead;
        q=head;
        while (q!=null){
            if (q.random!=null) p.random=map.get(q.random);
            q=q.next;
            p=p.next;
        }
        return newHead;
    }
}
```