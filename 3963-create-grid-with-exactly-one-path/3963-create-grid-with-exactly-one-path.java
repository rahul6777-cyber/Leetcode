class Solution {
    public String[] createGrid(int m, int n) {
        String[] grid = new String[m];

        for (int i = 0; i < m; i++) {
            char[] row = new char[n];
            java.util.Arrays.fill(row, '#');
            grid[i] = new String(row);
        }

        if (m == 1) {
            char[] row = new char[n];
            java.util.Arrays.fill(row, '.');
            grid[0] = new String(row);
            return grid;
        }

        if (n == 1) {
            for (int i = 0; i < m; i++) {
                char[] row = new char[]{'.'};
                grid[i] = new String(row);
            }
            return grid;
        }

        char[] first = new char[n];
        java.util.Arrays.fill(first, '#');

        for (int j = 0; j < n - 1; j++) {
            first[j] = '.';
        }
        grid[0] = new String(first);

        char[] second = new char[n];
        java.util.Arrays.fill(second, '#');
        second[n - 2] = '.';
        second[n - 1] = '.';
        grid[1] = new String(second);

        for (int i = 2; i < m; i++) {
            char[] row = new char[n];
            java.util.Arrays.fill(row, '#');
            row[n - 1] = '.';
            grid[i] = new String(row);
        }

        return grid;
    }
}