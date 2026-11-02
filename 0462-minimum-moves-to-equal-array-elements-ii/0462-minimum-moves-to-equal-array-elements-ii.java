class Solution {
    public int minMoves2(int[] nums) {
        int n = nums.length;
        int median = quickSelect(nums, 0, n - 1, n / 2);

        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - median);
        }

        return moves;
    }

    private int quickSelect(int[] nums, int left, int right, int k) {
        while (left < right) {
            int pivot = nums[right];
            int i = left;

            for (int j = left; j < right; j++) {
                if (nums[j] < pivot) {
                    int temp = nums[i];
                    nums[i++] = nums[j];
                    nums[j] = temp;
                }
            }

            int temp = nums[i];
            nums[i] = nums[right];
            nums[right] = temp;

            if (i == k) {
                return nums[i];
            } else if (i < k) {
                left = i + 1;
            } else {
                right = i - 1;
            }
        }

        return nums[left];
    }
}