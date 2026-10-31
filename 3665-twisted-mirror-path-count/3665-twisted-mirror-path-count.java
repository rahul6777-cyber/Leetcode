class Solution {
    static final int MOD = 1_000_000_007;

    public int uniquePaths(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int size = m * n;

        int[] right = new int[size];
        int[] down = new int[size];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int p = i * n + j;

                if (grid[i][j] == 0) {
                    right[p] = p;
                    down[p] = p;
                } else {
                    right[p] = (i + 1 < m)
                        ? down[(i + 1) * n + j] : -1;

                    down[p] = (j + 1 < n)
                        ? right[i * n + j + 1] : -1;
                }
            }
        }

        long[] dp = new long[size];
        dp[0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int p = i * n + j;
                long ways = dp[p];

                if (ways == 0) continue;

                if (j + 1 < n) {
                    int q = right[i * n + j + 1];
                    if (q != -1) {
                        dp[q] = (dp[q] + ways) % MOD;
                    }
                }

                if (i + 1 < m) {
                    int q = down[(i + 1) * n + j];
                    if (q != -1) {
                        dp[q] = (dp[q] + ways) % MOD;
                    }
                }
            }
        }

        return (int) dp[size - 1];
    }
}