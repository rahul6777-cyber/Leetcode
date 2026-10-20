class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length, ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;

            boolean ok = true;
            int prev = Integer.MIN_VALUE;

            for (int i = 0; i < n && ok; i += k) {
                int start = i, drops = 0;

                for (int j = i; j < i + k; j++) {
                    int next = i + (j - i + 1) % k;
                    if (nums[j] > nums[next]) {
                        drops++;
                        start = next;
                    }
                }

                if (drops > 1 || nums[start] < prev) {
                    ok = false;
                    break;
                }

                prev = nums[i + (start - i + k - 1) % k];
            }

            if (ok) ans += k;
        }

        return ans;
    }
}