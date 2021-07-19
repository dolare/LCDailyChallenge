/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class LC235LowestCommonAncestOrOfABinarySearchTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // conquer/merge the p, null or q
        if (root == null || root == p || root == q) {
            return root;
        }
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        // only 2 different situations: p and q are on different subtree, p q on same subtree
        if ((left == p && right == q) || (left == q && right == p)) {
            return root;
        } else if (left == null) {
            return right;
        } else {
            return left;
        }
    }
}
