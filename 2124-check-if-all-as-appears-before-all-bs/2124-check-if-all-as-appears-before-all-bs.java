class Solution {
    public boolean checkString(String s) {
        boolean bSeen = false;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'b') {
                bSeen = true;
            } else if (bSeen) {
                return false;
            }
        }
        return true;
    }
}