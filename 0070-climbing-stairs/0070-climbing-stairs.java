class Solution {
    public int climbStairs(int n) {

        //recursion solution
        
        //base case
       // if(n==1){
         //   return 1;
       // }
       // if(n==2){
       //     return 2; 
       // }
     //   int ans= climbStairs(n-1)+climbStairs(n-2);
       // return ans;
      
        int prev2 = 1; // ways(0)
        int prev1 = 1; // ways(1)

        for (int i = 2; i <= n; i++) {
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }
}
        
 
