class Solution {
    public int removeDuplicates(int[] nums) {
        int k = nums.length;
        int i=0;
        int j=i+1;
        while(j<k){
            if(nums[i]==nums[j]){
                j++;
            }
            else{
                i++;
                nums[i]=nums[j];
                j++;
            }
        }
        return i+1;
        
    }
}