class Solution {
    class Result {
        TreeNode node;
        int dist;

        Result(TreeNode node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).node;
    }

    private Result dfs(TreeNode node) {
        if (node == null) {
            return new Result(null, 0);
        }

        Result left = dfs(node.left);
        Result right = dfs(node.right);

        if (left.dist > right.dist) {
            return new Result(left.node, left.dist + 1);
        }
        if (right.dist > left.dist) {
            return new Result(right.node, right.dist + 1);
        }

        return new Result(node, left.dist + 1);
    }
}