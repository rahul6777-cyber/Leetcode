
class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int[][] cost = new int[n][n];

        for (int l = 0; l < n; l++) {
            for (int r = l + 1; r < n; r++) {
                int len = r - l + 1;
                cost[l][r] = Integer.MAX_VALUE;

                for (int d = 1; d < len; d++) {
                    if (len % d != 0) continue;

                    int changes = 0;

                    for (int rem = 0; rem < d; rem++) {
                        int left = rem;
                        int right = rem + ((len - 1 - rem) / d) * d;

                        while (left < right) {
                            if (s.charAt(l + left) != s.charAt(l + right)) {
                                changes++;
                            }
                            left += d;
                            right -= d;
                        }
                    }

                    cost[l][r] = Math.min(cost[l][r], changes);
                }
            }
        }

        int INF = 1000000;
        int[][] dp = new int[k + 1][n + 1];

        for (int[] row : dp) {
            java.util.Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int g = 1; g <= k; g++) {
            for (int i = 2 * g; i <= n; i++) {
                for (int j = 2 * (g - 1); j <= i - 2; j++) {
                    if (dp[g - 1][j] == INF) continue;

                    dp[g][i] = Math.min(
                        dp[g][i],
                        dp[g - 1][j] + cost[j][i - 1]
                    );
                }
            }
        }

        return dp[k][n];
    }
}


