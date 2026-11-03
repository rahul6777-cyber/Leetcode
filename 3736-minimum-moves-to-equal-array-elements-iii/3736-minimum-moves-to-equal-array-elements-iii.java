class Solution {
    public int minMoves(int[] nums) {
        int max = 0, sum = 0;

        for (int n : nums) {
            sum += n;
            if (n > max) max = n;
        }

        return max * nums.length - sum;
    }
}