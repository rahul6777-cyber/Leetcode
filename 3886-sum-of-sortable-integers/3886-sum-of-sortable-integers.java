class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;

            boolean valid = true;

            for (int i = 0; i < n; i += k) {
                int breaks = 0;

                for (int j = i; j < i + k; j++) {
                    int next = i + (j - i + 1) % k;
                    if (nums[j] > nums[next]) breaks++;
                }

                if (breaks > 1) {
                    valid = false;
                    break;
                }
            }

            if (!valid) continue;

            int[] copy = nums.clone();
            for (int i = 0; i < n; i += k) {
                java.util.Arrays.sort(copy, i, i + k);
            }

            for (int i = 1; i < n; i++) {
                if (copy[i] < copy[i - 1]) {
                    valid = false;
                    break;
                }
            }

            if (valid) ans += k;
        }

        return ans;
    }
}