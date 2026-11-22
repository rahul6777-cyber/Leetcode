class Solution {
    public boolean checkString(String s) {
        return s.indexOf('b') < 0 || s.lastIndexOf('a') < s.indexOf('b');
    }
}