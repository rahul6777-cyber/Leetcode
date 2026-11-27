class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] dp = new int[budget + 1];
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];
            minPrice = Math.min(minPrice, price);

            int count = 0;
            for (int[] x : items) {
                if (x[0] % factor == 0) count++;
            }

            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b], dp[b - price] + count);
            }
        }

        int ans = 0;
        for (int b = 0; b <= budget; b++) {
            ans = Math.max(ans, dp[b] + (budget - b) / minPrice);
        }

        return ans;
    }
}