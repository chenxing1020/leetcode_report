import java.io.*;
import java.util.*;

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}

class Solution {
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
}

public class MainClass {
    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }
    
        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
    
        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();
    
            if (index == parts.length) {
                break;
            }
    
            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }
    
            if (index == parts.length) {
                break;
            }
    
            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);
            
            int ret = new Solution().maxDepth(root);
            
            String out = String.valueOf(ret);
            
            System.out.print(out);
        }
    }
}