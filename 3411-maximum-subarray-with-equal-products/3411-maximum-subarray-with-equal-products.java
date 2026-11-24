class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int ans = 1;

        for (int i = 0; i < n; i++) {
            long product = 1;
            long gcd = 0;
            long lcm = 1;

            for (int j = i; j < n; j++) {
                int x = nums[j];

                if (product > Long.MAX_VALUE / x) break;

                product *= x;
                gcd = gcd(gcd, x);

                long g = gcd(lcm, x);
                if (lcm / g > Long.MAX_VALUE / x) break;

                lcm = (lcm / g) * x;

                if (product == gcd * lcm) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}