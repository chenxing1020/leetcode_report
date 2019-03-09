# 104.二叉树的最大深度

## **问题描述**  

给定一个二叉树，找出其最大深度。

二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

**说明**: 叶子节点是指没有子节点的节点。

给定二叉树[3,9,20,null,null,15,7]

```java
    3
   / \
  9  20
    /  \
   15   7
```

返回它的最大深度3.

## **解决方案**  

&emsp;&emsp;简单的二叉树深度问题，思路有递归和非递归两种。  

---

### 方案1：递归的方案，采用Dfs的策略。

```java
public int maxDepth(TreeNode root) {
  if (root == null) {
    return 0;
  } else {
    int left_height = maxDepth(root.left);
    int right_height = maxDepth(root.right);
    return java.lang.Math.max(left_height, right_height) + 1;
  }
}
```

#### 复杂度分析

* 时间复杂度：每个结点只访问一次，时间复杂度O(N);
* 空间复杂度：最坏的情况，只有左子结点，树的高度为N，栈的存储空间为O(N)；最好的情况，树的高度为log(N)，空间复杂度为O(log(N)).

---

### 方案2：非递归的方案，采用Bfs的策略。  

&emsp;&emsp;在Bfs的时候，记录每一层节点的数目（即初始队列元素的个数），然后按层将每一次层的父结点的子结点推入队列，同时将当前父结点推出队列。最后遍历出，树的层数=树的最大深度。

```java
public int maxDepth(TreeNode root) {
  if (root==null) return 0;
  Queue<TreeNode> nodeQueue=new LinkedList<>();
  nodeQueue.add(root);
  int layer=0;
  while(!nodeQueue.isEmpty()){
    int layerNum=nodeQueue.size();  //当前层的元素个数 
    layer++;
    while (layerNum>0){
      TreeNode node=nodeQueue.remove();
      if (node.left!=null) nodeQueue.add(node.left);
      if (node.right!=null) nodeQueue.add(node.right);
      layerNum--;
    }
  }
  return layer;
}
```

#### 复杂度分析

* 时间复杂度：每个节点都访问了一次，时间复杂度为O(N);
* 空间复杂度：最好的情况，树为完全不平衡，队列的元素始终只有一个，空间复杂度为O(1)；最坏的情况，树为完全平衡树，队列最多的元素为N/2，空间复杂度为O(N/2).
