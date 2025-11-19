package Tree;

import java.io.*;
import java.util.*;

public class B_1761_Node_Distance {
    static int N;
    static int M;
    static ArrayList<Edge>[] graph;
    static boolean[] visited;
    static int[][] parent;
    static int[] depth;
    static int[] dist;
    static int height = 17;

    static class Edge {
        int node;
        int distance;
        public Edge(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        visited = new boolean[N + 1];
        parent = new int[N + 1][height];
        depth = new int[N + 1];
        dist = new int[N + 1];

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }

        setTree(1, 0);
        parentInit();

        M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            sb.append(getDist(start, end)).append("\n");
        }

        System.out.print(sb);
    }

    // DFS: depth, parent[][0], dist[] 초기화
    static void setTree(int cur, int d) {
        visited[cur] = true;
        depth[cur] = d;
        for (Edge e : graph[cur]) {
            if (!visited[e.node]) {
                parent[e.node][0] = cur;
                dist[e.node] = dist[cur] + e.distance;
                setTree(e.node, d + 1);
            }
        }
    }

    static void parentInit() {
        for (int k = 1; k < height; k++) {
            for (int v = 1; v <= N; v++) {
                int mid = parent[v][k - 1];
                parent[v][k] = parent[mid][k - 1];
            }
        }
    }

    static int lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        for(int i = height-1; i>=0; i--){
            if(Math.pow(2, i) <= depth[a] - depth[b]){
                a=parent[a][i];
            }
        }

        if (a == b) return a;

        for (int i = height - 1; i >= 0; i--) {
            if (parent[a][i] != parent[b][i]) {
                a = parent[a][i];
                b = parent[b][i];
            }
        }

        return parent[a][0];
    }

    static int getDist(int a, int b) {
        int c = lca(a, b);
        return dist[a] + dist[b] - 2 * dist[c];
    }
}