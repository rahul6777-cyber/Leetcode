class Solution {

    int m, n;
    char[][] grid;
    Boolean[][][] memo;

    public boolean hasValidPath(char[][] grid) {

        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        // Path length must be even
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        // Valid parentheses string must start with '('
        if (grid[0][0] == ')') {
            return false;
        }

        // Valid parentheses string must end with ')'
        if (grid[m - 1][n - 1] == '(') {
            return false;
        }

        memo = new Boolean[m][n][m + n];

        return dfs(0, 0, 0);
    }

    private boolean dfs(int i, int j, int balance) {

        // Update balance according to current cell
        if (grid[i][j] == '(') {
            balance++;
        } else {
            balance--;
        }

        // Balance can never be negative
        if (balance < 0) {
            return false;
        }

        // Not enough cells left to reduce balance to 0
        int remaining = (m - 1 - i) + (n - 1 - j);

        if (balance > remaining) {
            return false;
        }

        // Destination reached
        if (i == m - 1 && j == n - 1) {
            return balance == 0;
        }

        // Already calculated
        if (memo[i][j][balance] != null) {
            return memo[i][j][balance];
        }

        boolean result = false;

        // Move Down
        if (i + 1 < m) {
            result = dfs(i + 1, j, balance);
        }

        // Move Right
        if (!result && j + 1 < n) {
            result = dfs(i, j + 1, balance);
        }

        memo[i][j][balance] = result;

        return result;
    }
}