import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] cnt = new int[n + 1];
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            cnt[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        int[] gain = new int[n + 1];

        for (int f = 1; f <= n; f++) {
            for (int j = f; j <= n; j += f) {
                gain[f] += cnt[j];
            }
            gain[f]--;
        }

        int[][] deals = new int[n][2];
        int size = 0;

        for (int[] item : items) {
            int f = item[0], p = item[1];
            if (gain[f] > 0 && p < 2L * minPrice) {
                deals[size][0] = p;
                deals[size++][1] = gain[f];
            }
        }

        Arrays.sort(deals, 0, size, (a, b) -> Integer.compare(a[0], b[0]));

        long ans = 0;
        for (int i = 0; i < size && budget >= deals[i][0]; i++) {
            int price = deals[i][0];
            int count = Math.min(deals[i][1], budget / price);
            ans += 2L * count;
            budget -= count * price;
        }

        ans += budget / minPrice;
        return (int) ans;
    }
}