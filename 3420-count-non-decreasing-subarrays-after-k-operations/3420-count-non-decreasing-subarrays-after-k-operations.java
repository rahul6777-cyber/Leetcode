class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] dq = new int[n];
        int head = 0, tail = 0;
        int j = n - 1;
        long cost = 0, ans = 0;

        for (int i = n - 1; i >= 0; i--) {
            int x = nums[i];

            while (head < tail && nums[dq[tail - 1]] < x) {
                int p = dq[--tail];
                int r = head < tail ? dq[tail - 1] : j + 1;
                cost += (long) (r - p) * (x - nums[p]);
            }

            dq[tail++] = i;

            while (cost > k) {
                cost -= nums[dq[head]] - nums[j];

                if (dq[head] == j) head++;
                j--;
            }

            ans += j - i + 1;
        }

        return ans;
    }
}