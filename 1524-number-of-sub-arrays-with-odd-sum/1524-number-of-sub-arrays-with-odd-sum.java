class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;
        long odd = 0, even = 1, ans = 0;

        for (int x : arr) {
            if ((x & 1) != 0) {
                long t = odd;
                odd = even;
                even = t;
            }

            ans += odd;
            if (ans >= MOD) ans -= MOD;
            even++;
        }

        return (int) ans;
    }
}