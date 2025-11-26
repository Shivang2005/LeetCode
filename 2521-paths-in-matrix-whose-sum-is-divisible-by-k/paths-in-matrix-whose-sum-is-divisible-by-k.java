class Solution {
    public int numberOfPaths(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int mod = 1000000007;
        int[][][] dp = new int[m][n][k];

        dp[0][0][grid[0][0] % k] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                for (int r = 0; r < k; r++) {
                    int nextRem = (r + grid[i][j]) % k;
                    long ways = 0;

                    if (i > 0) {
                        ways += dp[i - 1][j][r];
                    }
                    if (j > 0) {
                        ways += dp[i][j - 1][r];
                    }

                    dp[i][j][nextRem] = (int)((dp[i][j][nextRem] + ways) % mod);
                }
            }
        }

        return dp[m - 1][n - 1][0];
    }
}