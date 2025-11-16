package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_1761_Node_Distance {
    static int N;
    static int M;
    static ArrayList<Edge>[] graph;
    static boolean[] visited;
    static int[] answer;
    static int[] parent;
    static int[] depth;

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

         N= Integer.parseInt(st.nextToken());
         graph= new ArrayList[N + 1];
         for(int i=1;i<=N;i++){
             graph[i]= new ArrayList<Edge>();
         }
         visited= new boolean[N+1];
         answer= new int[N+1];
         parent= new int[N+1];
         depth= new int[N+1];

         for(int i=0; i<N-1; i++){
             st = new StringTokenizer(br.readLine());
             int a = Integer.parseInt(st.nextToken());
             int b = Integer.parseInt(st.nextToken());
             int c = Integer.parseInt(st.nextToken());
             graph[a].add(new Edge(b, c));
             graph[b].add(new Edge(a, c));
         }

         M= Integer.parseInt(br.readLine());
         answer= new int [M];
         dfs(1,0);

         for(int i=0; i<M; i++){
             st = new StringTokenizer(br.readLine());
             int start = Integer.parseInt(st.nextToken());
             int end = Integer.parseInt(st.nextToken());
             answer[i]=lca(start, end);
         }
         for(int i=0; i<M; i++){
             System.out.println(answer[i]);
         }
    }

    static void dfs(int cur, int d){
        visited[cur]=true;
        depth[cur]=d;
        for (Edge next : graph[cur]) {
            if (!visited[next.node]) {
                parent[next.node] = cur;
                dfs(next.node, d + 1);
            }
        }
    }
    static int lca(int a, int b) {

        int total=0;

        // 1. 깊이를 맞추기 (더 깊은 쪽을 위로 올리기)
        if (depth[a] < depth[b]) { // b가 더 깊은 경우
            int temp = a;
            a = b;
            b = temp;
        }

        while (depth[a] > depth[b]) {
            total += find(parent[a], a);
            a = parent[a];
        }

        while (a != b) {
            total += find(parent[a], a);
            a = parent[a];
            total += find(parent[b], b);
            b = parent[b];
        }
        return total;
    }

    static int find(int p, int a){
        for(int i=0; i<graph[p].size(); i++){
            if(graph[p].get(i).node==a){
                return graph[p].get(i).distance;
            }
        }
        return -1;
    }
}
