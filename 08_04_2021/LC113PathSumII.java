public class LC113PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList();
        helper(root, targetSum, new ArrayList(), res);

        return new ArrayList(res);
    }

    public void helper(TreeNode root, int target, List<Integer> solution, List<List<Integer>> res) {
        if (root == null) return;

        solution.add(root.val);

        helper(root.left, target - root.val, solution, res);
        helper(root.right, target - root.val, solution, res);

        if (root.left == null && root.right == null && target == root.val) {
            res.add(new ArrayList(solution));
        }
        solution.remove(solution.size() - 1);
    }
}