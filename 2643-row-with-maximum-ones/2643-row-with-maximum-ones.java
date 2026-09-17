class Solution {
    public int[] rowAndMaximumOnes(int[][] mat) {

        int maxOnes = -1;
        int maxRow = 0;

        for (int row = 0; row < mat.length; row++) {

            int count = 0;

            for (int col = 0; col < mat[0].length; col++) {
                if (mat[row][col] == 1) {
                    count++;
                }
            }

            if (count > maxOnes) {
                maxOnes = count;
                maxRow = row;
            }
        }

        return new int[]{maxRow, maxOnes};
    }
}