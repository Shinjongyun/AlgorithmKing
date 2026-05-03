import java.util.*;

class Solution {

    static class Node{
        int to;
        int cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static List<Node>[] graph;
    static final int INF = 2000000000;

    public int solution(int n, int s, int a, int b, int[][] fares) {

        graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 무방향 그래프
        for (int[] fare : fares) {
            int from = fare[0];
            int to = fare[1];
            int cost = fare[2];

            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        int[] distS = dijkstra(s, n);
        int[] distA = dijkstra(a, n);
        int[] distB = dijkstra(b, n);

        int answer = INF;

        for (int k = 1; k <= n; k++) {
            int total = distS[k] + distA[k] + distB[k];
            answer = Math.min(answer, total);
        }

        return answer;
    }

    public static int[] dijkstra(int start, int n) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);

        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            int now = cur.to;
            int cost = cur.cost;

            // 이미 더 짧은 거리로 방문한 적 있으면 패스
            if (dist[now] < cost) {
                continue;
            }

            for (Node next : graph[now]) {
                int nextNode = next.to;
                int nextCost = cost + next.cost;

                if (nextCost < dist[nextNode]) {
                    dist[nextNode] = nextCost;
                    pq.offer(new Node(nextNode, nextCost));
                }
            }
        }

        return dist;
    }
}