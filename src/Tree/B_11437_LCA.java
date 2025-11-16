package Tree;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_11437_LCA {
    static int N;                    // 노드 개수
    static int M;                    // 조상 개수
    static List<Integer>[] adj;      // 인접 리스트 (트리)
    static int[] parent;             // 부모 노드
    static int[] depth;              // 깊이
    static boolean[] visited;
    static int [] answer;

    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       N = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            addEdge(a,b);
        }

        parent = new int[N + 1];
        depth = new int[N + 1];
        visited = new boolean[N + 1];


        // 1번을 루트로 잡고 DFS 시작
        dfs(1, 0);

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        answer = new int[M];

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            answer[i] = lca(a, b);
        }
        for(int i=0; i<M; i++){
            System.out.println(answer[i]);
        }
    }

    static void addEdge(int a, int b) {
        adj[a].add(b);
        adj[b].add(a); // 트리이지만 양방향으로 함
    }

    // DFS로 parent, depth 채우기
    static void dfs(int cur, int d) {
        visited[cur] = true;
        depth[cur] = d;

        for (int next : adj[cur]) {
            if (!visited[next]) {
                parent[next] = cur;
                dfs(next, d + 1);
            }
        }
    }

    // 두 노드 a, b의 LCA 찾기
    static int lca(int a, int b) {
        int ah = depth[a];
        int bh = depth[b];

        while(ah > bh) {
            a = parent[a];
            ah--;
        }

        while(bh > ah) {
            b = parent[b];
            bh--;
        }

        while(a!=b) {
            a = parent[a];
            b = parent[b];
        }
        return a;
    }
}