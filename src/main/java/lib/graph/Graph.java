package lib.graph;

import java.util.*;

public class Graph<T> {
    private Map<T, ArrayList<T>> adjacencyList;
    public Graph() {

    }

    private void addEdge(T a, T b) {

    }


    public static void topologicalSort(int st, ArrayList<Integer> g[],
                                 boolean visited[], Stack<Integer> result) {
        if (visited[st]) return;
        visited[st] = true;
        for (int i = 0; i < g[st].size(); i++) {
            topologicalSort(g[st].get(i), g, visited, result);
        }
        result.push(st);
    }

    /**
     * Returns empty array if it has cycle
     */
    public static int[] topologicalSortKhansAlgo(int namVerities, int[][] edges) {
        Queue<Integer> queue = new LinkedList<>();
        int indegree[] = new int[namVerities];
        ArrayList<Integer> g[] = new ArrayList[namVerities];
        for (int i = 0;i < g.length; i++) {
            g[i] = new ArrayList<>();
        }
        for (int edge[] : edges) {
            indegree[edge[1]]++;
            g[edge[0]].add(edge[1]);
        }
        for (int i = 0;i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        int visitedCnt = 0;
        int ans[] = new int[namVerities];
        int ai = 0;
        while (!queue.isEmpty()) {
            int top = queue.poll();
            ans[ai++] = top;
            for (int neighbours : g[top]) {
                indegree[neighbours]--;
                if (indegree[neighbours] == 0) {
                    queue.add(neighbours);
                }
            }
            visitedCnt++;
        }
        if (visitedCnt != namVerities) {
            return new int[]{};
        }
        return ans;
    }

}
