
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Edge>[] edges;
    static int N;
    static int M;
    static int[] dist;
    static int[][] wolf;
    static int[] parent;
    static int answer = 0;
    static int INF = Integer.MAX_VALUE;

    public static class Edge {
        int to;
        int weight;
        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static class Node {
        int to;
        int dist;
        int state; // 0: 다음엔 빠르게, 1: 다음엔 느리게

        public Node(int to, int dist, int state) {
            this.to = to;
            this.dist = dist;
            this.state = state;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new List[N+1];
        dist = new int[N+1];
        wolf = new int[N+1][2];
        parent = new int[N+1];

        for(int i=1; i<=N; i++){
            edges[i] = new ArrayList<>();
            dist[i] = INF;
            wolf[i][0] = INF;
            wolf[i][1] = INF;
        }

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken()) * 2;
            edges[from].add(new Edge(to, weight));
            edges[to].add(new Edge(from, weight));
        }

        dij(1);
        dij2(1);
        for(int i=2; i<=N; i++){
            int wolfMin = Math.min(wolf[i][0], wolf[i][1]);
            if (dist[i] < wolfMin) {
                answer++;
            }
        }
        System.out.print(answer);
    }

    public static void dij(int start){
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        boolean[] visited = new boolean[N+1];

        pq.add(new Edge(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Edge edge = pq.poll();
            int cur = edge.to;

            if(visited[cur]){
                continue;
            }
            visited[cur] = true;

            for(Edge e : edges[cur]){
                if(dist[e.to] > dist[cur] + e.weight){
                    dist[e.to] = dist[cur] + e.weight;
                    pq.add(new Edge(e.to, dist[e.to]));
                }
            }
        }
    }

    public static void dij2(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.dist, b.dist));
        boolean[][] visited = new boolean[N + 1][2];

        wolf[start][0] = 0;
        pq.add(new Node(start, 0, 0));

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            int cur = curNode.to;
            int dist = curNode.dist;
            int state = curNode.state;

            if (visited[cur][state]) continue;
            visited[cur][state] = true;

            for (Edge next : edges[cur]) {
                int nextDist;
                int nextState;

                if (state == 0) {
                    // 이번 이동은 빠르게
                    nextDist = dist + next.weight / 2;
                    nextState = 1;
                } else {
                    // 이번 이동은 느리게
                    nextDist = dist + next.weight * 2;
                    nextState = 0;
                }

                if (wolf[next.to][nextState] > nextDist) {
                    wolf[next.to][nextState] = nextDist;
                    pq.add(new Node(next.to, nextDist, nextState));
                }
            }
        }
    }
}
