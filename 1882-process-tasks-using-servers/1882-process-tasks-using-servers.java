import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length, m = tasks.length;

        // free servers: pick smallest weight, then smallest index
        PriorityQueue<int[]> free = new PriorityQueue<>((a, b) ->
            a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
        for (int i = 0; i < n; i++) {
            free.offer(new int[]{servers[i], i});
        }

        // busy servers: {finishTime, weight, index}, ordered by finish time
        PriorityQueue<long[]> busy = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));

        int[] ans = new int[m];
        long t = 0;

        for (int j = 0; j < m; j++) {
            t = Math.max(t, j);

            // release servers finished by time t
            while (!busy.isEmpty() && busy.peek()[0] <= t) {
                long[] s = busy.poll();
                free.offer(new int[]{(int) s[1], (int) s[2]});
            }

            // no free server: jump to earliest finish time
            if (free.isEmpty()) {
                t = busy.peek()[0];
                while (!busy.isEmpty() && busy.peek()[0] <= t) {
                    long[] s = busy.poll();
                    free.offer(new int[]{(int) s[1], (int) s[2]});
                }
            }

            int[] s = free.poll();
            ans[j] = s[1];
            busy.offer(new long[]{t + tasks[j], s[0], s[1]});
        }

        return ans;
    }
}