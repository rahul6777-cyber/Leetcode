class Solution {
    public int totalNumbers(int[] digits) {
        int[] count = new int[10];
        for (int d : digits) {
            count[d]++;
        }
        
        int total = 0;
        
        // hundreds digit: 1-9 (no leading zero)
        for (int h = 1; h <= 9; h++) {
            // tens digit: 0-9
            for (int t = 0; t <= 9; t++) {
                // units digit: even (0,2,4,6,8)
                for (int u = 0; u <= 8; u += 2) {
                    if (canForm(count, h, t, u)) {
                        total++;
                    }
                }
            }
        }
        
        return total;
    }
    
    private boolean canForm(int[] count, int h, int t, int u) {
        int[] need = new int[10];
        need[h]++;
        need[t]++;
        need[u]++;
        
        return need[h] <= count[h] && need[t] <= count[t] && need[u] <= count[u];
    }
}