class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int W = 16;

        long[][] dp = new long[n][W];
        long[][] next = new long[n][W];

        dp[0][0] = 1L;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                long[] cur = next[j];
                java.util.Arrays.fill(cur, 0L);

                int v = grid[i][j];

                if (i == 0 && j == 0) {
                    cur[v >> 6] |= 1L << (v & 63);
                    continue;
                }

                long[] up = dp[j];

                for (int b = 0; b < W; b++) {
                    long bits = up[b];
                    while (bits != 0) {
                        int bit = Long.numberOfTrailingZeros(bits);
                        int x = (b << 6) | bit;
                        int y = x ^ v;
                        cur[y >> 6] |= 1L << (y & 63);
                        bits &= bits - 1;
                    }
                }

                if (j > 0) {
                    long[] left = next[j - 1];

                    for (int b = 0; b < W; b++) {
                        long bits = left[b];
                        while (bits != 0) {
                            int bit = Long.numberOfTrailingZeros(bits);
                            int x = (b << 6) | bit;
                            int y = x ^ v;
                            cur[y >> 6] |= 1L << (y & 63);
                            bits &= bits - 1;
                        }
                    }
                }
            }

            long[][] temp = dp;
            dp = next;
            next = temp;
        }

        for (int x = 0; x < 1024; x++) {
            if ((dp[n - 1][x >> 6] & (1L << (x & 63))) != 0) {
                return x;
            }
        }

        return 0;
    }
}