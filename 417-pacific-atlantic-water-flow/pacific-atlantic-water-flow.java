import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private int m;
    private int n;
    private int[][] heights;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return new ArrayList<>();
        }

        this.m = heights.length;
        this.n = heights[0].length;
        this.heights = heights;

        boolean[][] pacificReachable = new boolean[m][n];
        boolean[][] atlanticReachable = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            dfs(i, 0, pacificReachable);
            dfs(i, n - 1, atlanticReachable);
        }

        for (int j = 0; j < n; j++) {
            dfs(0, j, pacificReachable);
            dfs(m - 1, j, atlanticReachable);
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacificReachable[i][j] && atlanticReachable[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;
    }

    private void dfs(int row, int col, boolean[][] visited) {
        if (visited[row][col]) {
            return;
        }
        visited[row][col] = true;

        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};

        for (int i = 0; i < 4; i++) {
            int newRow = row + dr[i];
            int newCol = col + dc[i];

            if (newRow < 0 || newRow >= m || newCol < 0 || newCol >= n) {
                continue;
            }
            if (visited[newRow][newCol]) {
                continue;
            }
            if (heights[newRow][newCol] >= heights[row][col]) {
                dfs(newRow, newCol, visited);
            }
        }
    }
}