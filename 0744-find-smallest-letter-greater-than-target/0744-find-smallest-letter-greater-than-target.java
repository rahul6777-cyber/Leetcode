class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int n= letters.length;
        int s=0;
        int e=n-1;
        int ans =n;

        while(s<=e){
            int mid = s+(e-s)/2;
            if(letters[mid] > target){
                ans =mid;
                e=mid-1;
            }
            else{
                s=mid+1;
            }

        }
        return letters[ans % n];

        
    }
}