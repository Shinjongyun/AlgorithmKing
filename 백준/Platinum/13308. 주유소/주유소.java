

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int M;
    static int[] oil;
    static long[][] dist;
    static List<Edge>[] edges;
    static int answer = 0;
    static int INF = Integer.MAX_VALUE;

    public static class Edge {
        int to;
        int weight;

        public Edge(int a, int b) {
            this.to = a;
            this.weight = b;
        }
    }

    static class State {
        int node;
        int minOil;
        long cost;

        State(int node, int minOil, long cost) {
            this.node = node;
            this.minOil = minOil;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        oil = new int[N + 1];
        edges = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        int maxOil=0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            oil[i] = Integer.parseInt(st.nextToken());
            maxOil = Math.max(maxOil, oil[i]);
        }

        dist = new long[N + 1][maxOil + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= maxOil; j++) {
                dist[i][j] = INF;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[a].add(new Edge(b, c));
            edges[b].add(new Edge(a, c));
        }

        dij(1);
        long answer = INF;
        for (int i = 0; i <= maxOil; i++) {
            answer = Math.min(answer, dist[N][i]);
        }

        System.out.print(answer);
    }

    public static void dij(int start) {
        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> a.minOil - b.minOil);

        dist[1][oil[1]] = 0;
        pq.offer(new State(1, oil[1], 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            if (dist[cur.node][cur.minOil] < cur.cost) continue;

            for (Edge next : edges[cur.node]) {
                int nextMinOil = Math.min(cur.minOil, oil[next.to]);
                long nextCost = cur.cost + (long) cur.minOil * next.weight;

                if (dist[next.to][nextMinOil] > nextCost) {
                    dist[next.to][nextMinOil] = nextCost;
                    pq.offer(new State(next.to, nextMinOil, nextCost));
                }
            }
        }
    }
}
