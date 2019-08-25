# 146.LRU缓存机制

## 问题描述

运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。

获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。

**进阶:**

你是否可以在 O(1) 时间复杂度内完成这两种操作？

```c
示例:
LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // 返回  1
cache.put(3, 3);    // 该操作会使得密钥 2 作废
cache.get(2);       // 返回 -1 (未找到)
cache.put(4, 4);    // 该操作会使得密钥 1 作废
cache.get(1);       // 返回 -1 (未找到)
cache.get(3);       // 返回  3
cache.get(4);       // 返回  4
```

## 解决方案

&emsp;&emsp;力扣上的题解很详细。

```java
import java.util.Hashtable;
class LRUCache {
    class DNode{
        int key;
        int val;
        DNode pre;
        DNode next;
        public DNode(){}
        public DNode(int key,int val){
            this.key=key;
            this.val=val;
        }
    }

    
    private Hashtable<Integer,DNode> cache;
    private int capacity;
    private DNode head=new DNode();
    private DNode end=new DNode();

    public LRUCache(int capacity) {
        this.capacity=capacity;
        cache=new Hashtable<Integer,DNode>(capacity);
        end.pre=head;
        head.next=end;
    }
    
    public int get(int key) {
        if (cache.containsKey(key)){
            DNode keyNode=cache.get(key);
            removeNode(keyNode);
            addNode(keyNode);
            return keyNode.val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        //存在key值即覆盖
        if (cache.containsKey(key)){
            DNode keyNode=cache.get(key);
            removeNode(keyNode);
            addNode(keyNode);
            keyNode.val=value;
        }else{
            //缓存容量达到上限
            //删除最不常使用的key
            if (cache.size()==capacity){
                cache.remove(head.next.key);
                removeNode(head.next);
            }
            DNode newNode=new DNode(key,value);
            addNode(newNode);
            cache.put(key,newNode);
        }
    }
    
    //删除节点
    public void removeNode(DNode node){
        DNode preNode=node.pre;
        preNode.next=node.next;
        node.next.pre=node.pre;
    }
    
    //添加节点
    public void addNode(DNode node){
        node.pre=end.pre;
        node.pre.next=node;
        node.next=end;
        end.pre=node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```