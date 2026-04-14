class Solution {
    public boolean isPalindrome(int x) {
        int rev=0;
        int dup = x;
        while(x>0)
        {
           int lastDigit = x %10;
           rev=(rev*10)+ lastDigit;
           x=x/10;

        }
        if (dup == rev)
             return true;
        else{
            return false;
        }


        
    }
}