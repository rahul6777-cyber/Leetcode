
import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        int m = queries.length;
        int[] idx = new int[m];
        int[] ans = new int[m];

        for (int i = 0; i < m; i++) idx[i] = i;

        // Sort query indices by query time
        quickSort(idx, queries, 0, m - 1);

        int[] freq = new int[n + 1];
        int left = 0, right = 0, active = 0;

        for (int i : idx) {
            int t = queries[i];

            while (right < logs.length && logs[right][1] <= t) {
                if (freq[logs[right][0]]++ == 0) active++;
                right++;
            }

            int start = t - x;

            while (left < right && logs[left][1] < start) {
                if (--freq[logs[left][0]] == 0) active--;
                left++;
            }

            ans[i] = n - active;
        }

        return ans;
    }

    private void quickSort(int[] idx, int[] q, int l, int r) {
        while (l < r) {
            int i = l, j = r;
            int pivot = q[idx[(l + r) >>> 1]];

            while (i <= j) {
                while (q[idx[i]] < pivot) i++;
                while (q[idx[j]] > pivot) j--;

                if (i <= j) {
                    int temp = idx[i];
                    idx[i++] = idx[j];
                    idx[j--] = temp;
                }
            }

            if (j - l < r - i) {
                if (l < j) quickSort(idx, q, l, j);
                l = i;
            } else {
                if (i < r) quickSort(idx, q, i, r);
                r = j;
            }
        }
    }
}
