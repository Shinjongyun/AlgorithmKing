package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B_15681_트리와쿼리 {

    static int N;
    static int R;
    static int Q;
    static List<Integer>[] tree;
    static boolean[] visited;
    static int answer;
    static StringBuilder sb = new StringBuilder();
    static int[] size;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        tree = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }
        visited = new boolean[N + 1];
        size = new int[N + 1];
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        dfs(R);

        for (int i = 0; i < Q; i++) {
            int u = Integer.parseInt(br.readLine());
            answer=size[u];
            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }

    public static int dfs(int i) {
        visited[i] = true;
        int count = 1;
        for (int j : tree[i]) {
            if (!visited[j]) {
                count += dfs(j);
            }
        }
        size[i] = count;
        return count;
    }
}
