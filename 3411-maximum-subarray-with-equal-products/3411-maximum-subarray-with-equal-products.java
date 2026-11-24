class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int ans = 1;

        for (int i = 0; i < n; i++) {
            long prod = 1, gcd = 0, lcm = 1;

            for (int j = i; j < n; j++) {
                int x = nums[j];

                prod *= x;
                gcd = gcd(gcd, x);
                lcm = lcm / gcd(lcm, x) * x;

                if (prod == gcd * lcm) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}