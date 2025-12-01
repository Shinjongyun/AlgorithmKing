package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B_1068_Tree {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int delete;
    static int[] parent;
    static int ans;
    static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        parent = new int[N + 1];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();
        int root = -1;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if (n == -1) {
                root = i;
            } else {
                graph[i].add(n);
                graph[n].add(i);
            }
        }
        delete = Integer.parseInt(br.readLine());
        if (delete == root) {
            ans=0;
        } else dfs(root);
        System.out.println(ans);
    }

    static void dfs(int v) {
        visited[v] = true;
        int nodes = 0;
        for (int cur : graph[v]) {
            if (cur != delete && !visited[cur]) {
                nodes++;
                dfs(cur);
            }
        }
        if (nodes == 0) {  // 루트만 남은 경우
            ans++;
        }
    }
}
