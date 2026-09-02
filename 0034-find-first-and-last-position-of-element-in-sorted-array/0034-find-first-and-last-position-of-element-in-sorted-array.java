class Solution {
    
    static int getlowerbound(int[] nums, int target){
        int n= nums.length;
        int s=0;
        int e=n-1;
        int ans =n;
        while(s<=e){
            int mid = s+(e-s)/2;
            if(nums[mid] >= target){
                ans = mid;
                e = mid-1;
            }
            else{
                s=mid+1;
            }
        }
        return ans;
    }

    static int getUpperbound(int[] nums, int target){
        int n=nums.length;
        int s=0;
        int e=n-1;
        int ans =n;
        while(s<=e){
            int mid = s+(e-s)/2;
            if(nums[mid] > target){
                ans = mid;
                e = mid-1;
            }
            else{
                s=mid+1;
            }
        }
        return ans;
    }


    public int[] searchRange(int[] nums, int target) {
         int first = getlowerbound(nums, target);

        // target exists hi nahi karta
        if (first == nums.length || nums[first] != target) {
            return new int[]{-1, -1};
        }

        int last = getUpperbound(nums, target) - 1;

        return new int[]{first, last};

        
    }
}