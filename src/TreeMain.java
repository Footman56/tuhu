import java.util.ArrayList;
import java.util.Stack;

/**
 * @PackageName:PACKAGE_NAME
 * @ClassName: TreeMain
 * @Description:
 * @author:裴立志
 * @date: 2020/9/130:26
 */
class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;
    
    public TreeNode(int val) {
        this.val = val;
    }
    
    public int getVal() {
        return val;
    }
    
    public void setVal(int val) {
        this.val = val;
    }
    
    public TreeNode getLeft() {
        return left;
    }
    
    public void setLeft(TreeNode left) {
        this.left = left;
    }
    
    public TreeNode getRight() {
        return right;
    }
    
    public void setRight(TreeNode right) {
        this.right = right;
    }
}

public class TreeMain {
    
    int maxValue = 0;
    
    public static void main(String[] args) {
        
        TreeMain treeMain = new TreeMain();
        TreeNode root = new TreeNode(1);
        
        TreeNode treeNode = new TreeNode(2);
        root.setLeft(treeNode);
//        root.setRight(new TreeNode(3));
//
//        treeNode.setLeft(new TreeNode(4));
//        treeNode.setRight(new TreeNode(5));
        
//        for (Integer integer : treeMain.preorderTraversal(root)) {
////            System.out.println(integer);
////        }
    
        int run = treeMain.run(root);
        System.out.println("run = " + run);
    
    }
    
    /**
     * 求节点之和最大，任意路径
     * 左子树的最大节点和、右子树的最大节点和、当前节点
     *
     * @param root TreeNode类
     * @return int整型
     */
    public int maxPathSum(TreeNode root) {
        // write code here
        
        if (root == null) {
            return 0;
        }
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }
    
    // 递归求解最大路径值
    public int maxPathDown(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(0, maxPathDown(root.left));
        int right = Math.max(0, maxPathDown(root.right));

//如果将当前root作为根节点，那么最大值是root.val+左子树最大值+右子树最大值
        maxValue = Math.max(maxValue, root.val + left + right);
        //只能返回左右子树中较大值加上root.val
        return Math.max(0, root.val + Math.max(left, right));
        
    }
    
    /**
     * 递归求接后序遍历
     *
     * @param root TreeNode类
     * @return int整型ArrayList
     */
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        // write code here
        if (null == root) {
            return new ArrayList<>();
        }
        ArrayList<Integer> integers = posted(new ArrayList<>(), root);
        ;
        
        return integers;
        
    }
    
    public ArrayList<Integer> posted(ArrayList<Integer> arrayList, TreeNode root) {
        if (null == root) {
            return new ArrayList<>();
        }
        if (null != root.left) {
            posted(arrayList, root.left);
        }
        if (null != root.right) {
            posted(arrayList, root.right);
        }
        arrayList.add(root.val);
        
        return arrayList;
    }
    
    /**
     * 树的借助遍历，借助栈
     *
     * @param root TreeNode类
     * @return int整型ArrayList
     */
    
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        
        if (root == null) {
            return new ArrayList<Integer>();
        }
        
        ArrayList<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.add(root);
        
        while (!stack.isEmpty()) {
            TreeNode tmp = stack.pop();
            result.add(tmp.val);
            
            if (tmp.right != null) {
                stack.add(tmp.right);
            }
            
            if (tmp.left != null) {
                stack.add(tmp.left);
            }
        }
        return result;
    }
    
    
    
    
     /**
     * 二叉树的最小深度
     * @param root TreeNode类
     * @return int整型
     */
    public int run (TreeNode root) {
        // write code here
        if(root==null){
            return 0;
        }
        
        if(root.left==null&&root.right!=null){
            return run(root.right)+1;
        }
        
        if (root.right==null&&root.left!=null){
            return run(root.left)+1;
        }
        
        return Math.min(run(root.left),run(root.right))+1;
    }
}

