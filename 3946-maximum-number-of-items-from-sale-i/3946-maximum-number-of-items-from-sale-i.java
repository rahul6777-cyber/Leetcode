class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int[] freq = new int[1501];
        int minPrice = 1501;

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        int[] count = new int[1501];

        for (int f = 1; f <= 1500; f++) {
            for (int m = f; m <= 1500; m += f) {
                count[f] += freq[m];
            }
        }

        int[] dp = new int[budget + 1];

        for (int[] item : items) {
            int f = item[0], p = item[1];

            for (int b = budget; b >= p; b--) {
                dp[b] = Math.max(dp[b], dp[b - p] + count[f]);
            }
        }

        int ans = 0;
        for (int b = 0; b <= budget; b++) {
            ans = Math.max(ans, dp[b] + (budget - b) / minPrice);
        }

        return ans;
    }
}