class Solution {
    public int[] runningSum(int[] nums) {
        int Sum=0;
        for(int i=0;i<nums.length;i++){
            Sum= Sum + nums[i];
            nums[i] = Sum;
        }
        return nums;

    }
     static void main(String[] args){
            int [] nums ={1,2,3,4};
             for(int i=0; i<nums.length;i++){
                System.out.print(nums[i] + " ");
             }
            
        }
}