import java.util.*;

class Solution {
    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int log = 1;
        while ((1 << log) <= n) {
            log++;
        }

        int[] parent = new int[n];
        int[] depth = new int[n];
        int[] tin = new int[n];
        int[] tout = new int[n];
        int[] nextChild = new int[n];

        // Iterative DFS assigns preorder indices; each subtree is contiguous.
        int[] stack = new int[n];
        int top = 0;
        int timer = 0;

        stack[top++] = 0;
        parent[0] = -1;
        tin[0] = timer++;

        while (top > 0) {
            int u = stack[top - 1];

            if (nextChild[u] < graph[u].size()) {
                int v = graph[u].get(nextChild[u]++);
                if (v == parent[u]) {
                    continue;
                }

                parent[v] = u;
                depth[v] = depth[u] + 1;
                tin[v] = timer++;
                stack[top++] = v;
            } else {
                tout[u] = timer - 1;
                top--;
            }
        }

        int[][] up = new int[log][n];
        for (int v = 0; v < n; v++) {
            up[0][v] = parent[v] == -1 ? 0 : parent[v];
        }
        for (int k = 1; k < log; k++) {
            for (int v = 0; v < n; v++) {
                up[k][v] = up[k - 1][up[k - 1][v]];
            }
        }

        int[] charMask = new int[n];
        RangeXorFenwick fenwick = new RangeXorFenwick(n);

        // Initially add each character to the root-path masks of its subtree.
        for (int v = 0; v < n; v++) {
            charMask[v] = 1 << (s.charAt(v) - 'a');
            fenwick.rangeXor(tin[v], tout[v], charMask[v]);
        }

        List<Boolean> answer = new ArrayList<>();

        for (String query : queries) {
            String[] parts = query.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                int newMask = 1 << (parts[2].charAt(0) - 'a');
                int delta = charMask[u] ^ newMask;

                // This character contributes to root paths of u and descendants.
                fenwick.rangeXor(tin[u], tout[u], delta);
                charMask[u] = newMask;
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int w = lca(u, v, depth, up);

                int rootMaskU = fenwick.pointQuery(tin[u]);
                int rootMaskV = fenwick.pointQuery(tin[v]);
                int pathMask = rootMaskU ^ rootMaskV ^ charMask[w];

                answer.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return answer;
    }

    private int lca(int u, int v, int[] depth, int[][] up) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];
        for (int k = 0; k < up.length; k++) {
            if (((diff >> k) & 1) != 0) {
                u = up[k][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int k = up.length - 1; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }

        return up[0][u];
    }

    private static class RangeXorFenwick {
        private final int[] tree;

        RangeXorFenwick(int n) {
            tree = new int[n + 1];
        }

        // XOR delta over the inclusive interval [left, right].
        void rangeXor(int left, int right, int delta) {
            pointXor(left, delta);
            if (right + 1 < tree.length - 1) {
                pointXor(right + 1, delta);
            }
        }

        private void pointXor(int index, int value) {
            for (int i = index + 1; i < tree.length; i += i & -i) {
                tree[i] ^= value;
            }
        }

        int pointQuery(int index) {
            int result = 0;
            for (int i = index + 1; i > 0; i -= i & -i) {
                result ^= tree[i];
            }
            return result;
        }
    }
}