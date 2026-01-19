package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B_15900_나무탈출 {

    static int N;
    static List<Integer>[] tree;
    static boolean[] visited;
    static int sum;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());


        tree = new List[N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        dfs(1, 0);

        if(sum%2==1){
            System.out.print("Yes");
            return;
        }
        System.out.print("No");
    }

    public static void dfs(int node, int depth) {
        visited[node] = true;
        boolean isLeaf = true;
        for(int i : tree[node]) {
            if(!visited[i]) {
                isLeaf = false;
                dfs(i, depth + 1);
            }
        }
        if(isLeaf && node != 1) sum +=depth;
    }
}


