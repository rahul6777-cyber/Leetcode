class Solution {
    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[4];

        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;

            for (int j = 1; j <= 3 && j <= i; j++) {
                min = Math.min(min, dp[(i - j) % 4] + costs[i - 1] + j * j);
            }

            dp[i % 4] = min;
        }

        return dp[n % 4];
    }
}