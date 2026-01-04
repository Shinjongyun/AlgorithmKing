package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B_1068_Tree {

    static int N;
    static List<Integer>[] tree;
    static int T;
    static boolean[] visited;
    static int answer =0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        tree = new List[N];
        visited = new boolean[N];
        for(int i = 0; i < N; i++){
            tree[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        int root = -1;
        for(int i=0; i<N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if(n==-1) root = i;
            else tree[n].add(i);
        }

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        delete(T);
        dfs(root);
        StringBuilder sb = new StringBuilder();
        if(T==root) sb.append(0);
        else sb.append(answer);
        System.out.print(sb);
    }

    static void dfs(int n) {
        visited[n] = true;
        boolean hasChild = false;
        for (int child : tree[n]) {
            if (!visited[child]) {
                hasChild = true;
                dfs(child);
            }
        }
        if (!hasChild) {  // 살아있는 자식이 하나도 없다 → 리프
            answer++;
        }
    }

    static void delete(int n) {
        visited[n] = true;
        for(int i: tree[n]) {
            if(!visited[i]) delete(i);
        }
    }
}
