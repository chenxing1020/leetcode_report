# 235.二叉搜索树的最近公共祖先

## **问题描述**  

给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。  
百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”  
例如，给定如下二叉搜索树:root = [6,2,8,0,4,7,9,null,null,3,5]。  

![image](./bst.png)  

**示例 1**：  
输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8  
输出: 6  
解释: 节点 2 和节点 8 的最近公共祖先是 6。  
**示例 2**：  
输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4  
输出: 2  
解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。  
**说明**:  
所有节点的值都是唯一的。  
p、q 为不同节点且均存在于给定的二叉搜索树中。  

## **解决方案**  

&emsp;&emsp;首先这个题目中的关键词是`二叉搜索树`，很明显首先我们要知道二叉搜索树的性质和特征：

* bst所有结点的值大于它的左结点（如果存在）的值，小于它的右结点（如果存在）的值

&emsp;&emsp;知道了这些特征就可以分析本题的情况了，首先我们假设p.val < q.val，那么从根结点开始向下遍历每个结点node都会出现五种情况：

1. p.val < node.val < q.val
2. p.val < q.val < node.val
3. node.val < p.val < q.val
4. p.val = node.val < q.val
5. p.val < node.val = q.val

&emsp;&emsp;如果p和q都在node结点的同一侧（情况2和情况3），那么，node和p，q处在同一侧的子结点必然也是p和q的公共祖先结点。所以可以反推出，p和q处在node结点的不同侧时（情况1），node必然是p，q最近的公共祖先结点。  
&emsp;&emsp;最后，考虑情况4和情况5，即p，q中有一个结点是另一个结点的祖先结点，又一个结点是它自己的公共结点，故此时node也是最近的公共祖先结点。所以可以将情况1和情况4,5合并为 p.val <= node.val <= q.val。代码如下：

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val>q.val) {
            TreeNode temp=p;
            p=q;
            q=temp;
        }
        return findFirstMid(root,p,q);
    }
    
    public TreeNode findFirstMid(TreeNode node,TreeNode p,TreeNode q){
        if (p.val<=node.val && q.val>=node.val) return node;
        else if (q.val<node.val) return findFirstMid(node.left,p,q);
        else return findFirstMid(node.right,p,q);
    }
}
```
