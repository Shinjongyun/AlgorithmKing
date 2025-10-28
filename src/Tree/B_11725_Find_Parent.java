package Tree;

import java.util.*;
import java.io.*;

public class B_11725_Find_Parent {

    static int N;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        // 이중 배열화
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        visited = new boolean[N+1];
        parent = new int[N+1];

        for(int i = 1; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph[x].add(y);
            graph[y].add(x);
        }

        dfs(1);

        for(int i = 2; i <= N; i++){
            System.out.println(parent[i]);
        }
    }
    static void dfs(int i){
        visited[i] = true;
        for(int j : graph[i]){
            if(!visited[j]){
                dfs(j);
                parent[j] = i;
            }
        }
    }
}