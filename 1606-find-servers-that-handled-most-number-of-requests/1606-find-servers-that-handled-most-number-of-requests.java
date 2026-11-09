
import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        TreeSet<Integer> available = new TreeSet<>();
        int n = arrival.length;

        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        // Custom min heap
        long[] heap = new long[n];
        int size = 0;

        int[] count = new int[k];
        int max = 0;

        for (int i = 0; i < n; i++) {

            // Release completed servers
            while (size > 0 && (heap[0] >>> 32) <= arrival[i]) {
                long val = heap[0];
                size--;

                if (size > 0) {
                    heap[0] = heap[size];
                    int p = 0;

                    while (true) {
                        int c = p * 2 + 1;
                        if (c >= size) break;

                        if (c + 1 < size && heap[c + 1] < heap[c])
                            c++;

                        if (heap[p] <= heap[c]) break;

                        long temp = heap[p];
                        heap[p] = heap[c];
                        heap[c] = temp;
                        p = c;
                    }
                }

                available.add((int) val);
            }

            if (available.isEmpty()) continue;

            int target = i % k;
            Integer server = available.ceiling(target);

            if (server == null)
                server = available.first();

            available.remove(server);

            count[server]++;
            max = Math.max(max, count[server]);

            // Insert into custom min heap
            long val = ((long) (arrival[i] + load[i]) << 32)
                       | (server & 0xffffffffL);

            int p = size++;

            while (p > 0) {
                int parent = (p - 1) / 2;
                if (heap[parent] <= val) break;

                heap[p] = heap[parent];
                p = parent;
            }

            heap[p] = val;
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == max)
                ans.add(i);
        }

        return ans;
    }
}
