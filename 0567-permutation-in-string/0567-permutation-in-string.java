class Solution {
    static boolean compareFreq (int [] count1, int []count2){
        for(int i=0; i<26;i++){
            if(count1[i]!=count2[i]){
                return false;
            }
        }
        return true;
    }
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length()>s2.length()){
            return false;
        }
        //s1 ka  freq table ready karna hai
        int count1[] = new int[26];
        for(int i=0;i<s1.length();i++){
            char ch =s1.charAt(i);
            int index =ch -'a';
            count1[index]++;
        }
        int i=0;
        int windowLength =s1.length();
        int count2[] =new int [26];
        //first window ka freq table
        for(i=0;i<windowLength;i++){
            char ch =s2.charAt(i);
            int index = ch-'a';
            count2[index]++;
            
        }
        if(compareFreq(count1,count2)==true){
            return true;

        }
        else{
            //both the freq tables are not matching
            //process remaining 
            while(i<s2.length()){
                //new window pr move krna ho, toh new character ko freq table me add karo
                char newChar =s2.charAt(i);
                int newCharIndex = newChar -'a';
                count2[newCharIndex]++;
                int oldCharIndex = i - windowLength;
                int oldChar= s2.charAt(oldCharIndex);
                int freqTableIndexOfOldChar =oldChar-'a';
                count2[freqTableIndexOfOldChar]--;
                if(compareFreq(count1,count2)==true){
                    return true;
                }
                //yaha pr yaad se i ko aage badana hai
                i++;
            }



        }
       return false; 
    }
}