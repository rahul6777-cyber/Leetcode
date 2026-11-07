
import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length, m = tasks.length;

        PriorityQueue<Integer> free = new PriorityQueue<>(
            (a, b) -> servers[a] != servers[b]
                ? Integer.compare(servers[a], servers[b])
                : Integer.compare(a, b)
        );

        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> a[0] != b[0]
                ? Long.compare(a[0], b[0])
                : Long.compare(a[1], b[1])
        );

        for (int i = 0; i < n; i++) free.offer(i);

        int[] ans = new int[m];
        long time = 0;

        for (int i = 0; i < m; i++) {
            time = Math.max(time, i);

            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                free.offer((int) busy.poll()[1]);
            }

            if (free.isEmpty()) {
                time = busy.peek()[0];

                while (!busy.isEmpty() && busy.peek()[0] <= time) {
                    free.offer((int) busy.poll()[1]);
                }
            }

            int s = free.poll();
            ans[i] = s;

            busy.offer(new long[]{time + tasks[i], s});
        }

        return ans;
    }
}

