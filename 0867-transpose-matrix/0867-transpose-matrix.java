class Solution {
    public int[][] transpose(int[][] matrix) {
        if(matrix == null || matrix.length==0){
            return new int[0][0];
        }
        int row= matrix.length;
        int col =matrix[0].length;

        int newrow = col;
        int newcol = row;
        int ans[][] = new int[newrow][newcol];
        
        //actual logic
        for(int i=0; i<row;i++){
            for(int j=0;j<col;j++){
                ans[j][i]= matrix[i][j];
            }
        }
        return ans;
    }
}