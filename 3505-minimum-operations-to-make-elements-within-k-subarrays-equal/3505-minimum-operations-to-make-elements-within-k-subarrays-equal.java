
import java.util.*;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        int[] a = nums.clone();
        Arrays.sort(a);

        int[] vals = new int[n];
        int m = 0;

        for (int v : a) {
            if (m == 0 || vals[m - 1] != v)
                vals[m++] = v;
        }

        int[] id = new int[n];

        for (int i = 0; i < n; i++) {
            int l = 0, r = m - 1;
            while (l <= r) {
                int mid = (l + r) >>> 1;
                if (vals[mid] < nums[i]) l = mid + 1;
                else r = mid - 1;
            }
            id[i] = l + 1;
        }

        int[] bit = new int[m + 1];
        long[] sum = new long[m + 1];

        int[] freq = new int[m + 1];
        long[] sums = new long[m + 1];

        long[] cost = new long[n - x + 1];
        long total = 0;

        for (int i = 0; i < n; i++) {
            int p = id[i];
            int v = nums[i];

            total += v;

            for (int j = p; j <= m; j += j & -j) {
                bit[j]++;
                sum[j] += v;
            }

            if (i >= x) {
                int old = nums[i - x];
                int q = id[i - x];

                total -= old;

                for (int j = q; j <= m; j += j & -j) {
                    bit[j]--;
                    sum[j] -= old;
                }
            }

            if (i >= x - 1) {
                int target = (x + 1) / 2;
                int idx = 0;

                for (int step = Integer.highestOneBit(m);
                     step > 0; step >>= 1) {
                    int next = idx + step;

                    if (next <= m && bit[next] < target) {
                        idx = next;
                        target -= bit[next];
                    }
                }

                int rank = idx + 1;
                long median = vals[rank - 1];

                int cnt = 0;
                long leftSum = 0;

                for (int j = rank; j > 0; j -= j & -j) {
                    cnt += bit[j];
                    leftSum += sum[j];
                }

                cost[i - x + 1] =
                    median * cnt - leftSum
                    + total - leftSum
                    - median * (x - cnt);
            }
        }

        long INF = Long.MAX_VALUE / 4;

        long[] dp = new long[n + 1];
        long[] ndp = new long[n + 1];

        for (int j = 1; j <= k; j++) {
            Arrays.fill(ndp, INF);

            for (int i = x; i <= n; i++) {
                ndp[i] = ndp[i - 1];

                if (dp[i - x] != INF) {
                    ndp[i] = Math.min(
                        ndp[i],
                        dp[i - x] + cost[i - x]
                    );
                }
            }

            long[] temp = dp;
            dp = ndp;
            ndp = temp;
        }

        return dp[n];
    }
}
