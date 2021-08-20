public class LC1339MaximumProductOfSplittedBinaryTree {
    private final long MOD = 1000000007;
    long sum = 0, res = 0;

    public int maxProduct(TreeNode root) {
        getSum(root);
        getSubSum(root);
        return (int)(res % MOD);
    }

    public long getSubSum(TreeNode root) {
        if (root == null) return 0;

        long subSum = getSubSum(root.left) + getSubSum(root.right) + root.val;
        res = Math.max(res, (sum - subSum) * (subSum));
        return subSum;
    }

    public void getSum(TreeNode root) {
        if (root == null) {
            return;
        }
        sum += root.val;
        getSum(root.left);
        getSum(root.right);
    }
}