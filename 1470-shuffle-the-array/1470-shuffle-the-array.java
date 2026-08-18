class Solution {
    public int[] shuffle(int[] nums, int n) {
        int[] ans = new int[nums.length];
        int i=0;
        int j=n;
        for(int x=0;x<n;x++){
            ans[2*x] =nums[i];
            ans[2*x+1] = nums[j];
            i++;
            j++;
        }
        return ans;

        
    }
}