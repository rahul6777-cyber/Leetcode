class Solution {
    public int singleNonDuplicate(int[] nums) {
        int n= nums.length;
        int s=0;
        int e=n-1;
        
        while(s<=e){
            int mid =s+(e-s)/2;
            //single element
            if(s==e){
                return nums[s];
            }
            //non-single element array
            //check whether mid elment is a ans or not
            int currentValue = nums[mid];
            int prevValue= -1;
            if(mid-1 >= 0){
                prevValue =nums[mid-1];
            }
            int nextValue =-1;
            if(mid+1 < n){
                nextValue = nums[mid+1];
            }
            if(currentValue != prevValue && currentValue != nextValue){
                return currentValue;
            }
            if(currentValue !=prevValue && currentValue ==nextValue){
                int startingIndexOfPair =mid;
                if((startingIndexOfPair & 1)==1){
                    //startingIndex -> odd wala case
                    e=mid-1;
                }
                else{
                    //starting index-> even wala case
                    s=mid+1;
                }

            }
            else if(currentValue==prevValue && currentValue !=nextValue){
                int endingIndexOfPair =mid;
                if((endingIndexOfPair & 1)==1 ){
                    //ending index is odd
                    //ans right me hoga
                    s=mid+1;
                }
                else{
                    e=mid-1;
                }
            }
        }
        return -1;
    }
}