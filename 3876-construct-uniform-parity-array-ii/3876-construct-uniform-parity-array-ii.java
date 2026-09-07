class Solution {
    public boolean uniformArray(int[] nums1) {

        int minOdd = Integer.MAX_VALUE;
        int minEven = Integer.MAX_VALUE;

        for (int num : nums1) {
            if (num % 2 == 0) {
                minEven = Math.min(minEven, num);
            } else {
                minOdd = Math.min(minOdd, num);
            }
        }

        // Agar saare numbers even hain
        if (minOdd == Integer.MAX_VALUE) {
            return true;
        }

        // Smallest odd ko har even se subtract kar sakte hain
        return minOdd < minEven;
    }
}