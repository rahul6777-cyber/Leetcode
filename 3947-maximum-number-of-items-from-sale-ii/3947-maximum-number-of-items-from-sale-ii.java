import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freq = new int[n + 1];

        int minPrice = Integer.MAX_VALUE;
        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        int[] gain = new int[n + 1];

        for (int f = 1; f <= n; f++) {
            for (int j = f; j <= n; j += f) {
                gain[f] += freq[j];
            }
            gain[f]--;
        }

        Arrays.sort(items, (a, b) -> Integer.compare(a[1], b[1]));

        long ans = 0;
        long b = budget;

        for (int[] item : items) {
            int price = item[1];
            int extra = gain[item[0]];

            if (extra <= 0 || price >= 2L * minPrice)
                continue;

            long count = Math.min(extra, b / price);

            ans += count * 2;
            b -= count * price;
        }

        ans += b / minPrice;

        return (int) ans;
    }
}