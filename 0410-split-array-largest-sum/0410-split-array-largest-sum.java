class Solution {
    static boolean isValidAnswer(int nums[],int k,int mid){

        int painterCount=1;
        int paintedlength=0;
        
        for(int i=0;i<nums.length; i++){
            if(paintedlength + nums[i] <= mid){
                //assing that nums
                paintedlength += nums[i];
            }
            else{
                painterCount++;
                paintedlength=0;
                if(painterCount > k || nums[i] > mid){
                    return false;
                }
                else{
                    paintedlength += nums[i];
                }
            }
        }
        return true;

    }
    public int splitArray(int[] nums, int k) {
        int sum=0;
        for(int i=0;i<nums.length;i++){
            sum += nums[i];
        }
        int s=0;
        int e= sum;
        int ans=-1;
        while(s<=e){
            int mid=s+(e-s)/2;
            if(isValidAnswer(nums,k, mid)){
                ans=mid;
                e=mid-1;
            }
            else{
                s=mid+1;
            }
        }
        return ans;
    }
}