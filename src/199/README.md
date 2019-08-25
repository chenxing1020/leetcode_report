# 199.二叉树的右视图

## 问题描述

给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

```c
示例:
输入: [1,2,3,null,5,null,4]
输出: [1, 3, 4]
解释:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
```

## 解决方案

层序遍历即可。

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result=new ArrayList<>();
        if (root==null) return result;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        int count=1;
        while (queue.size()!=0){
            for (int i=0;i<count;i++){
                TreeNode node=queue.poll();
                if (i==count-1) result.add(node.val);
                if (node.left!=null) queue.offer(node.left);
                if (node.right!=null) queue.offer(node.right);
            }
            count=queue.size();
        }
        return result;
    }
}
```