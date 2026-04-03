

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

public class Main {

    static int V;
    static int E;
    static int K;
    static int T;
    static int[] dist;
    static List<Edge>[] edges;
    static int INF = Integer.MAX_VALUE;
    static StringBuilder sb = new StringBuilder();

    public static class Edge{
        int to;
        int weight;
        public Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());
        
        edges = new List[V+1];
        for(int i = 1; i <= V; i++){
            edges[i] = new ArrayList<>();
        }

        dist = new int[V+1];
        for(int i = 1; i <= V; i++){
            dist[i]=INF;
        }

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[a].add(new Edge(b, c));
        }

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        dij(K);
        System.out.print(dist[T]);
    }

    public static void dij(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        boolean[] visited = new boolean[V+1];

        dist[start] = 0;
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            int cur = e.to;

            if (visited[cur]) continue;
            visited[cur] = true;

            for (Edge edge : edges[cur]) {
                if (dist[edge.to] > dist[cur] + edge.weight) {
                    dist[edge.to] = dist[cur] + edge.weight;
                    pq.add(new Edge(edge.to, dist[edge.to]));
                }
            }
        }
    }
}
