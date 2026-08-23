class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int n= nums.length;
        int SumMax=0;
        int SumMin=0;
        int maxi = 0;
        int mini =0;

        for(int i=0; i<n;i++){

            SumMax = Math.max(0,SumMax + nums[i]);
            maxi = Math.max(maxi,SumMax);

            SumMin = Math.min(0,SumMin + nums[i]);
            mini = Math.min(mini,SumMin);


        }
        return Math.max(maxi,-mini);
        
    }
}