class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        int[] head = new int[n];
        int[] to = new int[n - 1];
        int[] next = new int[n - 1];

        java.util.Arrays.fill(head, -1);

        for (int i = 1; i < n; i++) {
            to[i - 1] = i;
            next[i - 1] = head[parent[i]];
            head[parent[i]] = i - 1;
        }

        int[] last = new int[26];
        java.util.Arrays.fill(last, -1);

        int[] newParent = parent.clone();
        int[] prev = new int[n];

        int[] stack = new int[2 * n];
        int top = 0;
        stack[top++] = 0;

        while (top > 0) {
            int u = stack[--top];

            if (u < 0) {
                u = ~u;
                last[s.charAt(u) - 'a'] = prev[u];
                continue;
            }

            int c = s.charAt(u) - 'a';
            prev[u] = last[c];

            if (last[c] != -1) {
                newParent[u] = last[c];
            }

            last[c] = u;

            stack[top++] = ~u;

            for (int e = head[u]; e != -1; e = next[e]) {
                stack[top++] = to[e];
            }
        }

        // Build final tree
        java.util.Arrays.fill(head, -1);

        for (int i = 1; i < n; i++) {
            next[i - 1] = head[newParent[i]];
            head[newParent[i]] = i - 1;
        }

        // Iterative traversal
        int[] order = new int[n];
        top = 0;
        int count = 0;

        stack[top++] = 0;

        while (top > 0) {
            int u = stack[--top];
            order[count++] = u;

            for (int e = head[u]; e != -1; e = next[e]) {
                stack[top++] = to[e];
            }
        }

        // Calculate subtree sizes
        int[] ans = new int[n];
        java.util.Arrays.fill(ans, 1);

        for (int i = count - 1; i > 0; i--) {
            int u = order[i];
            ans[newParent[u]] += ans[u];
        }

        return ans;
    }
}