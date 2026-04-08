

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static List<Edge>[] edges;
    static int[] fox;
    static int[][] wolf;
    static int INF = Integer.MAX_VALUE;
    static int answer = 0;

    static class Edge{
        int to;
        int weight;
        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class Node{
        int to;
        int dist;
        int stats;
        public Node(int to, int dist, int stats) {
            this.to = to;
            this.dist = dist;
            this.stats = stats;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new List[N+1];
        fox = new int[N+1];
        wolf = new int[N+1][2];

        for(int i=1; i<=N; i++){
            edges[i] = new ArrayList<>();
            fox[i] = INF;
            wolf[i][0] = INF;
            wolf[i][1] = INF;
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken()) * 2;
            edges[a].add(new Edge(b, c));
            edges[b].add(new Edge(a, c));
        }

        dij1(1);
        dij2(1);

        for(int i=1; i<=N; i++){
            int bestWolf = Math.min(wolf[i][0], wolf[i][1]);
            if(bestWolf > fox[i]){
                answer++;
            }
        }
        System.out.print(answer);
    }

    public static void dij1(int start){
        PriorityQueue<Node> q = new PriorityQueue<>((a, b) -> a.dist - b.dist);

        q.offer(new Node(start, 0, 0));
        fox[start] = 0;
        while(!q.isEmpty()){
            Node node = q.poll();

            int cur = node.to;
            int distance = node.dist;

            if(fox[cur] < distance) continue;

            for(Edge e : edges[cur]){
                if(fox[e.to] > distance + e.weight){
                    fox[e.to] = distance + e.weight;
                    q.offer(new Node(e.to, fox[e.to], 0));
                }
            }
        }
    }

    public static void dij2(int start){
        PriorityQueue<Node> q = new PriorityQueue<>((a, b) -> a.dist - b.dist);

        q.offer(new Node(start, 0, 0));
        wolf[start][0] = 0;
        while(!q.isEmpty()){
            Node node = q.poll();
            int cur = node.to;
            int distance = node.dist;
            int status = node.stats;

            if(wolf[cur][status] < distance) continue;

            for(Edge e : edges[cur]){
                int nStatus, nextDist;
                if(status == 0){ // 전에 애가 너 빠르게 이동하래
                    nextDist = distance + e.weight / 2;
                    nStatus = 1;
                } else { // 전에 애가 너 느리게 이동하래
                    nextDist = distance + e.weight * 2;
                    nStatus = 0;
                }
                if(wolf[e.to][nStatus] > nextDist){
                    wolf[e.to][nStatus] = nextDist;
                    q.offer(new Node(e.to, nextDist, nStatus));
                }
            }
        }
    }

}
