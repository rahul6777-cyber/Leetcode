import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] cnt = new int[n + 1];
        int min = Integer.MAX_VALUE;

        for (int[] x : items) {
            cnt[x[0]]++;
            min = Math.min(min, x[1]);
        }

        int[] gain = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j += i) {
                gain[i] += cnt[j];
            }
            gain[i]--;
        }

        long[] deals = new long[n];
        int k = 0;
        long limit = 2L * min;

        for (int[] x : items) {
            int p = x[1], g = gain[x[0]];

            if (g > 0 && p < limit) {
                deals[k++] = ((long) p << 32) | (g & 0xffffffffL);
            }
        }

        Arrays.sort(deals, 0, k);

        long ans = 0;

        for (int i = 0; i < k && budget > 0; i++) {
            int p = (int) (deals[i] >>> 32);
            int g = (int) deals[i];

            int take = Math.min(g, budget / p);
            ans += 2L * take;
            budget -= take * p;
        }

        ans += budget / min;

        return (int) ans;
    }
}