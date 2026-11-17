
import java.util.*;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        char[] arr = s.toCharArray();

        int[][] cost = new int[n][n];

        // Precompute valid divisors
        ArrayList<Integer>[] divisors = new ArrayList[n + 1];

        for (int len = 2; len <= n; len++) {
            divisors[len] = new ArrayList<>();

            for (int d = 1; d < len; d++) {
                if (len % d == 0) {
                    divisors[len].add(d);
                }
            }
        }

        // Calculate minimum changes for every substring
        for (int len = 2; len <= n; len++) {
            for (int d : divisors[len]) {
                int groups = len / d;

                for (int i = 0; i + len <= n; i++) {
                    int changes = 0;
                    int end = i + len;

                    for (int r = 0; r < d; r++) {
                        int left = i + r;
                        int right = end - d + r;

                        for (int t = 0; t < groups / 2; t++) {
                            if (arr[left] != arr[right]) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    int j = i + len - 1;
                    cost[i][j] = Math.min(
                        cost[i][j] == 0 && d == divisors[len].get(0)
                            ? changes : cost[i][j],
                        changes
                    );
                }
            }
        }

        // Dynamic Programming
        final int INF = 1000000;
        int[][] dp = new int[k + 1][n + 1];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int g = 1; g <= k; g++) {
            for (int i = 2 * g; i <= n; i++) {
                for (int j = 2 * (g - 1); j <= i - 2; j++) {
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
