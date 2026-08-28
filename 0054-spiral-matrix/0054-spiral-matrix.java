class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m= matrix.length;
        int n= matrix[0].length;

        List<Integer> result = new ArrayList<>();
        
        int sRow =0;
        int eRow =m-1;
        int sCol =0;
        int eCol =n-1;
        
        while(sRow<=eRow && sCol<=eCol){

            for(int col=sCol; col<=eCol; col++){
                result.add(matrix[sRow][col]);
            }
            sRow++;

            for(int row =sRow; row<=eRow; row++){
                result.add(matrix[row][eCol]);
            }
            eCol--;
            
            if(sRow <= eRow){
                for(int col=eCol; col>=sCol; col--){
                    result.add(matrix[eRow][col]);
                }
                eRow--;
            }

            if(sCol<=eCol){
                for(int row =eRow; row>= sRow; row--){
                    result.add(matrix[row][sCol]);
                }
                sCol++;
            }

        }
        return result;
        
    }
}