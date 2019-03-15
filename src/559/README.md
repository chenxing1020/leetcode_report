# 559.N叉树的最大深度

## 问题描述

给定一个 N 叉树，找到其最大深度。  

最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。  

## 解决方案

### BFS

&emsp;&emsp;按层遍历，记录每一层的元素个数，然后将该层的子节点加入队列，并记录层数。

```java
class Solution {
    public int maxDepth(Node root) {
        if (root==null) return 0;
        int layer=0,laynum=0;
        Queue<Node> queue=new LinkedList<>();
        queue.add(root);
        while (queue.size()!=0){
            laynum=queue.size();
            layer++;
            while (laynum>0){
                Node temp=queue.poll();
                if (temp.children!=null){
                    for (Node node : temp.children){
                       queue.add(node);
                    }
                }
                laynum--;
            }
        }
        return layer;
    }
}
```

### DFS

&emsp;&emsp;递归调用。

```java
class Solution {
    public int maxDepth(Node root) {
        if (root==null) return 0;
        int max=0;
        if (root.children!=null){
            for (Node node : root.children){
                max=Math.max(max,maxDepth(node));
            }
            max+=1;
        }
        return max;
    }
}
```