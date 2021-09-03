public class LC95UniqueBinarySearchTreeII {
    public List<TreeNode> generateTrees(int n) {
        return dfs(1, n);
    }

    public List<TreeNode> dfs(int i, int j) {
        // Solution: need to return List<TreeNode> for each level in the recursive method
        // and handle all possibilities
        List<TreeNode> nodes = new ArrayList();

        if (i > j) nodes.add(null);

        for (int index = i; index <= j; index++) {
            List<TreeNode> leftNodes = dfs(i, index - 1);
            List<TreeNode> rightNodes = dfs(index + 1, j);
            for (TreeNode leftNode: leftNodes) {
                for (TreeNode rightNode: rightNodes) {
                    TreeNode node = new TreeNode(index);
                    node.left = leftNode;
                    node.right = rightNode;
                    nodes.add(node);
                }
            }
        }
        return nodes;
    }
}