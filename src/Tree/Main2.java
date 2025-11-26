//package Tree;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.StringTokenizer;
//
//public class Main2 {
//
//    static int N;
//    static int K;
//    static ArrayList<Edge>[] graph;
//    static boolean[] visited;
//    static int[][] parent;
//    static int[] depth;
//    static int[] dist;
//    static int height = 17;
//    static int[][] min;
//    static int[][] max;
//
//    static class Edge {
//        int node;
//        int distance;
//        public Edge(int node, int distance) {
//            this.node = node;
//            this.distance = distance;
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        N = Integer.parseInt(st.nextToken());
//        graph = new ArrayList[N + 1];
//        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
//
//        visited = new boolean[N + 1];
//        parent = new int[N + 1][height];
//        depth = new int[N + 1];
//        dist = new int[N + 1];
//        min = new int[N + 1][height];
//        max = new int[N + 1][height];
//
//        for (int i = 0; i < N - 1; i++) {
//            st = new StringTokenizer(br.readLine());
//            int a = Integer.parseInt(st.nextToken());
//            int b = Integer.parseInt(st.nextToken());
//            int c = Integer.parseInt(st.nextToken());
//            graph[a].add(new Edge(b, c));
//            graph[b].add(new Edge(a, c));
//        }
//
//        setTree(1, 0);
//        parentInit();
//
//        K = Integer.parseInt(br.readLine());
//
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < K; i++) {
//            st = new StringTokenizer(br.readLine());
//            int start = Integer.parseInt(st.nextToken());
//            int end = Integer.parseInt(st.nextToken());
//
//            lca(start, end);
//            sb.append(min).append(" ");
//            sb.append(max);
//            sb.append("\n");
//        }
//
//        System.out.print(sb);
//    }
//
//    static void setTree(int cur, int d) {
//        visited[cur] = true;
//        depth[cur] = d;
//        min[cur][0]=1000000;
//        max[cur][0]=0;
//        for (Edge e : graph[cur]) {
//            if (!visited[e.node]) {
//                parent[e.node][0] = cur;
//                dist[e.node] = dist[cur] + e.distance;
//                setTree(e.node, d + 1);
//            }
//        }
//    }
//
//    static void parentInit(){
//        for(int n=1;n<height;n++){
//            for(int i=1;i<=N;i++){
//                parent[i][n]=parent[parent[i][n-1]][n-1];
//            }
//        }
//    }
//
//    static void lca(int a, int b) {
//        if (depth[a] < depth[b]) {
//            int tmp = a;
//            a = b;
//            b = tmp;
//        }
//
//        for(int i = height-1; i>=0; i--){
//            if(Math.pow(2, i) <= depth[a] - depth[b]){
//                a=parent[a][i];
//            }
//        }
//
//        if (a == b){
//
//        }
//
//        for (int k = height - 1; k >= 0; k--) {
//            if (parent[a][k] != 0 && parent[a][k] != parent[b][k]) {
//                minVal = Math.min(minVal, minDist[a][k]);
//                minVal = Math.min(minVal, minDist[b][k]);
//                maxVal = Math.max(maxVal, maxDist[a][k]);
//                maxVal = Math.max(maxVal, maxDist[b][k]);
//                a = parent[a][k];
//                b = parent[b][k];
//            }
//        }
//
//        // 3. 마지막 한 칸 (a, b의 부모가 LCA)
//        minVal = Math.min(minVal, minDist[a][0]);
//        minVal = Math.min(minVal, minDist[b][0]);
//        maxVal = Math.max(maxVal, maxDist[a][0]);
//        maxVal = Math.max(maxVal, maxDist[b][0]);
//
//        return new int[]{minVal, maxVal};
//
//    }
//}
