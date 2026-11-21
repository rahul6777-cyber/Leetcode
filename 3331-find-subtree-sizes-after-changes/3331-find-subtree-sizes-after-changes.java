class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        int[] ans = new int[n];
        int[] last = new int[26];
        java.util.Arrays.fill(last, -1);

        java.util.List<Integer>[] tree = new java.util.ArrayList[n];
        for (int i = 0; i < n; i++) tree[i] = new java.util.ArrayList<>();
        for (int i = 1; i < n; i++) tree[parent[i]].add(i);

        int[] newParent = parent.clone();
        dfs(0, tree, s, last, newParent);

        java.util.List<Integer>[] adj = new java.util.ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new java.util.ArrayList<>();
        for (int i = 1; i < n; i++) adj[newParent[i]].add(i);

        calc(0, adj, ans);
        return ans;
    }

    private void dfs(int u, java.util.List<Integer>[] tree, String s, int[] last, int[] p) {
        int c = s.charAt(u) - 'a';
        int prev = last[c];

        if (prev != -1) p[u] = prev;
        last[c] = u;

        for (int v : tree[u]) dfs(v, tree, s, last, p);

        last[c] = prev;
    }

    private int calc(int u, java.util.List<Integer>[] adj, int[] ans) {
        int size = 1;
        for (int v : adj[u]) size += calc(v, adj, ans);
        return ans[u] = size;
    }
}