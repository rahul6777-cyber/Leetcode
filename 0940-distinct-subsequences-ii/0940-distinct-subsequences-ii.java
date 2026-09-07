class Solution {
    public int distinctSubseqII(String s) {

        int MOD = 1000000007;

        long dp = 1; // empty subsequence

        long[] last = new long[26];

        for (char ch : s.toCharArray()) {

            int index = ch - 'a';

            long newDp = (2 * dp - last[index] + MOD) % MOD;

            last[index] = dp;

            dp = newDp;
        }

        // Empty subsequence remove
        return (int)((dp - 1 + MOD) % MOD);
    }
}