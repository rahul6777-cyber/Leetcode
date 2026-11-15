class Solution {
    public int numOfSubarrays(int[] arr) {
        long odd = 0, even = 1;
        long ans = 0;
        int mod = 1_000_000_007;

        for (int x : arr) {
            if ((x & 1) == 1) {
                long temp = odd;
                odd = even;
                even = temp;
            }

            ans = (ans + odd) % mod;
            even++;
        }

        return (int) ans;
    }
}