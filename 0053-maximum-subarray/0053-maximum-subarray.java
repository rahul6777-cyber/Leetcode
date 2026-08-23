class Solution {
    public int maxSubArray(int[] nums) {
        int n= nums.length;
        int Sum=0;
        int maxi = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){

        
          //STEP 1: SUM CREATE KARTE HAI
           Sum = Sum + nums[i]; 

           //step2: maxi update karne ke liye
           maxi = Math.max(maxi,Sum);

           //step3:sum check karne ke liye
           if(Sum<0){
            Sum=0;
           }

        }
        return maxi;
        
    }
}