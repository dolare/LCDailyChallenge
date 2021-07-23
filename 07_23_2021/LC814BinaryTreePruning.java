/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return root;

        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        // set left/right -> null when it is 0 from leaf to the root
        if (root.left == null && root.right == null) {
            if (root.val == 0) {
                return null;
            }
        }
        return root;
    }
}
