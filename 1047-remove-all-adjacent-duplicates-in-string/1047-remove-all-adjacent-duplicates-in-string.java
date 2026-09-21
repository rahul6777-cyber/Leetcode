class Solution {
    public String removeDuplicates(String s) {
        StringBuilder ans = new StringBuilder();
        for(int i=0; i<s.length(); i++){
            //current char
            char ch = s.charAt(i);
            //compare ans k rightmost character ko current character se
            if(ans.length()>0 && ans.charAt(ans.length()-1) == ch){
                ans.deleteCharAt(ans.length()-1);
            }
            else{
                //if these 2 are different characters
                ans.append(ch);
            }
        }
        return ans.toString();
        
    }
}