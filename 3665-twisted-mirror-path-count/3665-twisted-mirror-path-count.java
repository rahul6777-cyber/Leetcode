import java.util.Arrays;

class Solution {
    static final int MOD = 1_000_000_007;

    int[][][] memo;
    int[][] grid;
    int m, n;

    int resolve(int r, int c, int dir) {
        if (r >= m || c >= n) return -1;

        if (memo[r][c][dir] != -2) {
            return memo[r][c][dir];
        }

        if (grid[r][c] == 0) {
            return memo[r][c][dir] = r * n + c;
        }

        if (dir == 0) {
            // Entered mirror moving right: turn down
            return memo[r][c][dir] = resolve(r + 1, c, 1);
        } else {
            // Entered mirror moving down: turn right
            return memo[r][c][dir] = resolve(r, c + 1, 0);
        }
    }

    public int uniquePaths(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        memo = new int[m][n][2];

        for (int[][] row : memo) {
            for (int[] cell : row) {
                Arrays.fill(cell, -2);
            }
        }

        long[][] dp = new long[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0) continue;

                // Move right
                if (j + 1 < n) {
                    int pos = resolve(i, j + 1, 0);

                    if (pos != -1) {
                        int r = pos / n;
                        int c = pos % n;

                        dp[r][c] = (dp[r][c] + dp[i][j]) % MOD;
                    }
                }

                // Move down
                if (i + 1 < m) {
                    int pos = resolve(i + 1, j, 1);

                    if (pos != -1) {
                        int r = pos / n;
                        int c = pos % n;

                        dp[r][c] = (dp[r][c] + dp[i][j]) % MOD;
                    }
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }
}