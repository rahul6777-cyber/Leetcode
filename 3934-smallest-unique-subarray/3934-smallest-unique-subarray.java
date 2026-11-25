import java.util.*;

class Solution {
    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 1_000_000_009L;
    private static final long BASE = 911382323L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        long[] p1 = new long[n + 1], p2 = new long[n + 1];
        long[] pow1 = new long[n + 1], pow2 = new long[n + 1];

        pow1[0] = pow2[0] = 1;

        for (int i = 0; i < n; i++) {
            long val = nums[i] + 1_000_000_001L;

            p1[i + 1] = (p1[i] * BASE + val) % MOD1;
            p2[i + 1] = (p2[i] * BASE + val) % MOD2;

            pow1[i + 1] = pow1[i] * BASE % MOD1;
            pow2[i + 1] = pow2[i] * BASE % MOD2;
        }

        int low = 1, high = n, ans = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (hasUnique(mid, n, p1, p2, pow1, pow2)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean hasUnique(int k, int n,
                             long[] p1, long[] p2,
                             long[] pow1, long[] pow2) {

        HashMap<Long, Integer> freq = new HashMap<>();

        for (int i = 0; i <= n - k; i++) {
            long h1 = (p1[i + k] - p1[i] * pow1[k] % MOD1 + MOD1) % MOD1;
            long h2 = (p2[i + k] - p2[i] * pow2[k] % MOD2 + MOD2) % MOD2;

            long key = h1 * MOD2 + h2;
            freq.put(key, freq.getOrDefault(key, 0) + 1);
        }

        for (int count : freq.values()) {
            if (count == 1) return true;
        }

        return false;
    }
}