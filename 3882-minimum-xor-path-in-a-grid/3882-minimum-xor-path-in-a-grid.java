class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] dp = new boolean[n][1024];

        // Starting state: XOR = 0 before visiting the first cell
        dp[0][0] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = grid[i][j];
                boolean[] next = new boolean[1024];

                if (i == 0 && j == 0) {
                    next[val] = true;
                } else {
                    boolean[] up = dp[j];
                    boolean[] left = j > 0 ? dp[j - 1] : null;

                    for (int x = 0; x < 1024; x++) {
                        if (up[x]) {
                            next[x ^ val] = true;
                        }

                        if (left != null && left[x]) {
                            next[x ^ val] = true;
                        }
                    }
                }

                dp[j] = next;
            }
        }

        for (int x = 0; x < 1024; x++) {
            if (dp[n - 1][x]) {
                return x;
            }
        }

        return 0;
    }
}
