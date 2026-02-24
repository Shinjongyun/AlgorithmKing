
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int M;
    static List<Integer>[] graph;
    static boolean solve;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new List[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        for (int i = 0; i < N; i++) {
            dfs(i, 1);
            if(solve){
                System.out.print(1);
                return;
            }
        }
        System.out.print(0);
    }

    public static void dfs(int to, int depth) {
        if(depth == 5){
            solve = true;
            return;
        }
        visited[to] = true;
        for (int v : graph[to]) {
            if (!visited[v]) {
                dfs(v, depth + 1);
                if(solve) return;
            }
        }
        visited[to] = false;
    }
}
