public class LC108ConvertSortedArrayToBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        int left = 0, right = nums.length - 1;
        TreeNode root = new TreeNode();
        constructTree(nums, left, right, root);
        return root;
    }

    public TreeNode constructTree(int[] nums, int left, int right, TreeNode root) {
        if (left > right) return null;;
        if (left == right) return new TreeNode(nums[left]);
        int mid = left + (right - left)/2;
        root.val = nums[mid];
        root.left = constructTree(nums, left, mid - 1, new TreeNode());
        root.right = constructTree(nums, mid + 1, right, new TreeNode());
        // System.out.println(root.val);
        return root;
    }
}
