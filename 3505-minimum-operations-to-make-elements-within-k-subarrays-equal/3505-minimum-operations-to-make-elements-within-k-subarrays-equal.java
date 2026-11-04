
import java.util.*;

class Solution {

    class WindowMedian {
        TreeMap<Integer, Integer> low = new TreeMap<>();
        TreeMap<Integer, Integer> high = new TreeMap<>();

        int lowSize = 0, highSize = 0;
        long lowSum = 0, highSum = 0;

        void add(int num) {
            if (low.isEmpty() || num <= low.lastKey()) {
                low.merge(num, 1, Integer::sum);
                lowSize++;
                lowSum += num;
            } else {
                high.merge(num, 1, Integer::sum);
                highSize++;
                highSum += num;
            }
            balance();
        }

        void remove(int num) {
            if (low.containsKey(num)) {
                erase(low, num);
                lowSize--;
                lowSum -= num;
            } else {
                erase(high, num);
                highSize--;
                highSum -= num;
            }
            balance();
        }

        void erase(TreeMap<Integer, Integer> map, int num) {
            int count = map.get(num);
            if (count == 1) map.remove(num);
            else map.put(num, count - 1);
        }

        void balance() {
            while (lowSize > highSize + 1) {
                int v = low.lastKey();
                erase(low, v);
                lowSize--;
                lowSum -= v;

                high.merge(v, 1, Integer::sum);
                highSize++;
                highSum += v;
            }

            while (lowSize < highSize) {
                int v = high.firstKey();
                erase(high, v);
                highSize--;
                highSum -= v;

                low.merge(v, 1, Integer::sum);
                lowSize++;
                lowSum += v;
            }
        }

        long getCost() {
            long median = low.lastKey();

            return median * lowSize - lowSum
                 + highSum - median * highSize;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        int m = n - x + 1;

        long[] cost = new long[m];

        // Calculate cost of every window
        WindowMedian wm = new WindowMedian();

        for (int i = 0; i < n; i++) {
            wm.add(nums[i]);

            if (i >= x) {
                wm.remove(nums[i - x]);
            }

            if (i >= x - 1) {
                cost[i - x + 1] = wm.getCost();
            }
        }

        // DP: minimum cost to select exactly j windows
        long INF = Long.MAX_VALUE / 4;

        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        for (int j = 1; j <= k; j++) {
            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {
                // Skip current element
                curr[i] = curr[i - 1];

                // Select window ending at i-1
                if (i >= x && prev[i - x] != INF) {
                    curr[i] = Math.min(
                        curr[i],
                        prev[i - x] + cost[i - x]
                    );
                }
            }

            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];
    }
}
