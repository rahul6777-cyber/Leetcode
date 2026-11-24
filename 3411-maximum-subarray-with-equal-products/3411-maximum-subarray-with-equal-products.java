class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length, ans = 1;

        for (int i = 0; i < n; i++) {
            long p = 1, g = 0, l = 1;

            for (int j = i; j < n; j++) {
                int x = nums[j];

                p *= x;
                g = gcd(g, x);

                long d = gcd(l, x);
                l = (l / d) * x;

                if (p == g * l) {
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