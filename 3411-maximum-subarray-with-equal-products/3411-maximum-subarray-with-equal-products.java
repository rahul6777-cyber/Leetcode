class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length, ans = 1;

        for (int i = 0; i < n; i++) {
            long prod = 1, g = 0, l = 1;

            for (int j = i; j < n; j++) {
                prod *= nums[j];
                g = gcd(g, nums[j]);
                l = l / gcd(l, nums[j]) * nums[j];

                if (prod == g * l) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}