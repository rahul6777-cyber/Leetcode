class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int[] a = new int[n];

        for (int i = 0; i < n; i++)
            a[i] = nums.get(i);

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int sum = 0;

            for (int j = i; j < n && j - i < r; j++) {
                sum += a[j];

                if (j - i + 1 >= l && sum > 0 && sum < ans)
                    ans = sum;
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}