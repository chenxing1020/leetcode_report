# 230.二叉搜索树中第k小的元素

## **问题描述**  

给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。  
**说明**:  
你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。  
**示例 1**：  

```c
输入: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
输出: 1
```

**示例 2**：  

```c
输入: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
输出: 3
```

**进阶**：  
如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？

## **解决方案**  

&emsp;&emsp;看到二叉搜索树很容易想到，bst在中序遍历的情况下得到的结点的值是顺序增大的，所以我的第一个想法就是直接进行中序遍历，然后将结点依次存进ArrayList中，最后返回数组中的第k-1个结点值即可。

```java
class Solution {
    private List<TreeNode> list=new ArrayList<>();
    public int kthSmallest(TreeNode root, int k) {
        MidSearch(root);
        return (list.get(k-1).val);
    }
    public void MidSearch(TreeNode root){
        if (root==null) return ;
        if (root.left!=null) MidSearch(root.left);
        list.add(root);
        if (root.right!=null) MidSearch(root.right);
    }
}
```

## **优化**

&emsp;&emsp;因为要频繁的查询，所以用集合来存放所有结点显然是一笔不小的开销。其实我们只需要记录遍历过的结点个数，然后记录第k个结点的值即可，这样空间复杂度就降到常数级别，也节省了集合操作的时间开销。

```java
class Solution {
    private int count=0;
    private int result;
    public int kthSmallest(TreeNode root, int k) {
        MidSearch(root,k);
        return result;
    }
    public void MidSearch(TreeNode root,int k){
        if (root==null) return ;
        if (result!=0) return ;
        if (root.left!=null) MidSearch(root.left,k);
        count++;
        if (count==k) result=root.val;
        if (root.right!=null) MidSearch(root.right,k);
    }
}
```