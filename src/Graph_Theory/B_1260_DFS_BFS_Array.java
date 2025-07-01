package Graph_Theory;
import java.io.*;
import java.util.*;

public class B_1260_DFS_BFS_Array {

    static int N, M;
    static int[][] graph;
    static boolean[] visited1;
    static boolean[] visited2;
    static StringBuilder dfsBuilder =  new StringBuilder();
    static StringBuilder bfsBuilder =  new StringBuilder();

    public static void dfs(int index) {
        visited1[index] = true;
        dfsBuilder.append(index).append(" ");
        for(int i = 1; i <= N; i++){
            if(!visited1[i] && graph[index][i] == 1){
                dfs(i);
            }
        }
    }

    public static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        visited2[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int index = queue.poll();
            bfsBuilder.append(index).append(" ");

            for (int i = 1; i <= N; i++) {
                if (graph[index][i] == 1 && !visited2[i]) {
                    queue.add(i);
                    visited2[i] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());   // 시작하는 놈

        visited1 = new boolean[N+1];  // 만들 떄 1 더해주는 거 잊지 않기
        visited2 = new boolean[N+1];
        graph = new int[N+1][N+1];

        for(int i = 0; i < M; i++){ // 간선 입력 받기
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        dfs(start);
        bfs(start);

        System.out.println(dfsBuilder);
        System.out.println(bfsBuilder);
    }
}
