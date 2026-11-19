class Solution {
    public int minimizeSum(int[] nums) {
        int a = Integer.MAX_VALUE, b = a, c = a;
        int x = Integer.MIN_VALUE, y = x, z = x;

        for (int n : nums) {
            if (n < a) { c = b; b = a; a = n; }
            else if (n < b) { c = b; b = n; }
            else if (n < c) c = n;

            if (n > x) { z = y; y = x; x = n; }
            else if (n > y) { z = y; y = n; }
            else if (n > z) z = n;
        }

        return Math.min(x - c, Math.min(y - b, z - a));
    }
}