import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

class Solution {

    class DSU {
        int[] parent;

        public DSU(int n) {
            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }
        }

        public int find(int i) {
            if (parent[i] == i) {
                return i;
            }
            return parent[i] = find(parent[i]);
        }

        public void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) {
                parent[rootI] = rootJ;
            }
        }
    }

    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        DSU dsu = new DSU(c);
        for (int[] conn : connections) {
            dsu.union(conn[0], conn[1]);
        }

        boolean[] isOnline = new boolean[c + 1];
        Arrays.fill(isOnline, true);

        Map<Integer, TreeSet<Integer>> onlineNodesInGrid = new HashMap<>();
        for (int i = 1; i <= c; i++) {
            int root = dsu.find(i);
            onlineNodesInGrid.computeIfAbsent(root, k -> new TreeSet<>()).add(i);
        }

        List<Integer> results = new ArrayList<>();
        
        for (int[] query : queries) {
            int type = query[0];
            int x = query[1];

            if (type == 1) {
                if (isOnline[x]) {
                    results.add(x);
                } else {
                    int root = dsu.find(x);
                    TreeSet<Integer> onlineNodes = onlineNodesInGrid.get(root);
                    if (onlineNodes.isEmpty()) {
                        results.add(-1);
                    } else {
                        results.add(onlineNodes.first());
                    }
                }
            } else {
                if (isOnline[x]) {
                    isOnline[x] = false;
                    int root = dsu.find(x);
                    onlineNodesInGrid.get(root).remove(x);
                }
            }
        }

        int[] resultArray = new int[results.size()];
        for (int i = 0; i < results.size(); i++) {
            resultArray[i] = results.get(i);
        }
        return resultArray;
    }
}