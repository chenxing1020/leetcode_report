# 460.LFU缓存机制

## 问题描述

设计并实现最不经常使用（LFU）缓存的数据结构。它应该支持以下操作：get 和 put。

get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。
put(key, value) - 如果键不存在，请设置或插入值。当缓存达到其容量时，它应该在插入新项目之前，使最不经常使用的项目无效。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，最近最少使用的键将被去除。

**进阶:**

你是否可以在 O(1) 时间复杂度内完成这两种操作？

```c
示例:
LFUCache cache = new LFUCache( 2 /* capacity (缓存容量) */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // 返回 1
cache.put(3, 3);    // 去除 key 2
cache.get(2);       // 返回 -1 (未找到key 2)
cache.get(3);       // 返回 3
cache.put(4, 4);    // 去除 key 1
cache.get(1);       // 返回 -1 (未找到 key 1)
cache.get(3);       // 返回 3
cache.get(4);       // 返回 4
```

## 解决方案

本题和LRU缓存机制很相似，但是加入了使用次数的限制，所以为了保证O(1)的时间复杂度，必须使用一个新的hash表来维护链表的次数区间，健为使用次数，值就是该区间的左端点。

因此，对于每次移动之前，都要判断要移动的节点是否为某一区间的左端点，并更新端点的hash表。

```java

import java.util.Hashtable;

class LFUCache {

    private DNode head = new DNode();
    private DNode end = new DNode();
    private int capacity;
    private Hashtable<Integer, DNode> cache;
    //规定新插入的值插在队列左侧
    //timeCacheLeft用来维护对应次数的最左侧的值
    private Hashtable<Integer, DNode> timesLeft = new Hashtable<>();

    public LFUCache(int capacity) {
        if (capacity == 0) return;
        this.capacity = capacity;
        cache = new Hashtable<>(capacity);
        head.next = end;
        end.pre = head;
    }

    public int get(int key) {
        if (capacity == 0) return -1;
        if (cache.containsKey(key)) {
            DNode keyNode = cache.get(key);
            checkNode(keyNode);
            keyNode.times++;
            updateNode(keyNode);
            return cache.get(key).value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;
        if (cache.containsKey(key)) {
            DNode keyNode = cache.get(key);
            checkNode(keyNode);
            keyNode.value = value;
            keyNode.times++;
            updateNode(keyNode);
            cache.put(key, keyNode);
        } else {
            //容量上限
            if (cache.size() == capacity) {
                checkNode(end.pre);
                cache.remove(end.pre.key);
                removeNode(end.pre);
            }
            DNode newNode = new DNode(1, key, value);
            addNode(newNode, end);
            updateNode(newNode);
            cache.put(key, newNode);

        }
    }

    //更新节点的位置
    private void updateNode(DNode node) {
        //当前次数有左端点
        if (timesLeft.containsKey(node.times)) {
            removeNode(node);
            addNode(node, timesLeft.get(node.times));
        } else if (timesLeft.containsKey(node.times - 1)) {
            //当前次数没有左端点，但次数减一有左端点
            //否则不需要改变当前节点的位置
            removeNode(node);
            addNode(node, timesLeft.get(node.times - 1));
        }
        //更新端点
        timesLeft.put(node.times, node);
    }

    //检查当前节点是否为端点
    private void checkNode(DNode node) {
        if (timesLeft.get(node.times) == node) {
            //如当前节点的次数和next节点次数相等，更新左端点
            //否则删除端点信息
            if (node.next != end && node.next.times == node.times) {
                timesLeft.put(node.times, node.next);
            } else {
                timesLeft.remove(node.times);
            }
        }
    }

    private void removeNode(DNode node) {
        DNode preNode = node.pre;
        preNode.next = node.next;
        node.next.pre = preNode;
    }

    private void addNode(DNode newNode, DNode timesLeftNode) {
        DNode preNode = timesLeftNode.pre;
        preNode.next = newNode;
        newNode.pre = preNode;
        newNode.next = timesLeftNode;
        timesLeftNode.pre = newNode;
    }

    class DNode {
        int times;
        int key;
        int value;
        DNode pre;
        DNode next;

        public DNode() {
        }

        public DNode(int times, int key, int value) {
            this.times = times;
            this.key = key;
            this.value = value;
        }
    }
}
```