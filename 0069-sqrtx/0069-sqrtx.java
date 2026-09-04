class Solution {
    public int mySqrt(int x) {
        int s=1;
        int e=x-1;
        int ans=x;

        while(s<=e){
            int mid=s+(e-s)/2;
            long square =(long) mid*mid;

            if(square == x){
                return mid;
            }
            else if(square > x){
                e=mid-1;
            }
            else{
                ans=mid;
                s=mid+1;
            }
        }
        return ans;
        
    }
}