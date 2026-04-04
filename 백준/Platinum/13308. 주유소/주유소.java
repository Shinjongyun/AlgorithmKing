import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] oil;
    static List<Edge>[] graph;
    static long[][] dist;
    static final long INF = Long.MAX_VALUE;

    static class Edge {
        int to;
        int len;

        Edge(int to, int len) {
            this.to = to;
            this.len = len;
        }
    }

    static class State implements Comparable<State> {
        int node;
        int minOil;
        long cost;

        State(int node, int minOil, long cost) {
            this.node = node;
            this.minOil = minOil;
            this.cost = cost;
        }

        @Override
        public int compareTo(State o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        oil = new int[N + 1];
        graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        int maxOil = 0;
        for (int i = 1; i <= N; i++) {
            oil[i] = Integer.parseInt(st.nextToken());
            maxOil = Math.max(maxOil, oil[i]);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }

        dist = new long[N + 1][maxOil + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= maxOil; j++) {
                dist[i][j] = INF;
            }
        }

        dijkstra();

        long answer = INF;
        for (int price = 0; price <= maxOil; price++) {
            answer = Math.min(answer, dist[N][price]);
        }

        System.out.println(answer);
    }

    static void dijkstra() {
        PriorityQueue<State> pq = new PriorityQueue<>();

        dist[1][oil[1]] = 0;
        pq.offer(new State(1, oil[1], 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            if (dist[cur.node][cur.minOil] < cur.cost) {
                continue;
            }

            for (Edge next : graph[cur.node]) {
                long nextCost = cur.cost + (long) cur.minOil * next.len;
                int nextMinOil = Math.min(cur.minOil, oil[next.to]);

                if (dist[next.to][nextMinOil] > nextCost) {
                    dist[next.to][nextMinOil] = nextCost;
                    pq.offer(new State(next.to, nextMinOil, nextCost));
                }
            }
        }
    }
}