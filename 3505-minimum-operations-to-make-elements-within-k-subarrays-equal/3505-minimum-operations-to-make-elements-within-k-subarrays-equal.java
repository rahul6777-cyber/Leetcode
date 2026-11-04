
import java.util.*;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int[] vals = new int[n];
        int size = 0;

        for (int v : sorted) {
            if (size == 0 || vals[size - 1] != v) {
                vals[size++] = v;
            }
        }

        int[] bit = new int[size + 1];
        long[] sum = new long[size + 1];

        long[] cost = new long[n - x + 1];

        long total = 0;
        int count = 0;

        for (int i = 0; i < n; i++) {
            int id = Arrays.binarySearch(vals, 0, size, nums[i]) + 1;
            update(bit, sum, id, 1, nums[i]);
            total += nums[i];
            count++;

            if (count > x) {
                int old = nums[i - x];
                int idx = Arrays.binarySearch(vals, 0, size, old) + 1;
                update(bit, sum, idx, -1, -old);
                total -= old;
                count--;
            }

            if (count == x) {
                int mid = kth(bit, (x + 1) / 2);
                long median = vals[mid - 1];

                long leftCount = query(bit, mid);
                long leftSum = querySum(sum, mid);

                cost[i - x + 1] =
                    median * leftCount - leftSum
                    + (total - leftSum)
                    - median * (x - leftCount);
            }
        }

        long INF = Long.MAX_VALUE / 4;
        long[] dp = new long[n + 1];
        long[] next = new long[n + 1];

        Arrays.fill(dp, 0);

        for (int j = 1; j <= k; j++) {
            Arrays.fill(next, INF);

            for (int i = 1; i <= n; i++) {
                next[i] = next[i - 1];

                if (i >= x && dp[i - x] != INF) {
                    next[i] = Math.min(
                        next[i],
                        dp[i - x] + cost[i - x]
                    );
                }
            }

            long[] temp = dp;
            dp = next;
            next = temp;
        }

        return dp[n];
    }

    private void update(int[] bit, long[] sum,
                        int i, int delta, long value) {
        while (i < bit.length) {
            bit[i] += delta;
            sum[i] += value;
            i += i & -i;
        }
    }

    private int query(int[] bit, int i) {
        int res = 0;
        while (i > 0) {
            res += bit[i];
            i -= i & -i;
        }
        return res;
    }

    private long querySum(long[] bit, int i) {
        long res = 0;
        while (i > 0) {
            res += bit[i];
            i -= i & -i;
        }
        return res;
    }

    private int kth(int[] bit, int k) {
        int idx = 0;
        int step = Integer.highestOneBit(bit.length - 1);

        for (; step > 0; step >>= 1) {
            int next = idx + step;

            if (next < bit.length && bit[next] < k) {
                idx = next;
                k -= bit[next];
            }
        }

        return idx + 1;
    }
}
