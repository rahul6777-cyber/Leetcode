class Solution {

    public int findPeakElement(int[] nums) {
        int n=nums.length;
        int s=0;
        int e=n-1;
        int ans=n;

        while(s<e){
            int mid = s+ (e-s)/2;
            if(nums[mid] >= nums[mid+1]){
                ans=mid;
                e=mid;
            }
            else{
                s=mid+1;
            }

            
        }
        return s;
    }
}