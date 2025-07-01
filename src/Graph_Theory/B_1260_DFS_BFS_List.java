/*package Graph_Theory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_1260_DFS_BFS_List {

    static int N, M;
    static ArrayList<Integer>[][] list;
    static int[][] graph;
    static boolean[] visited;
    static Queue<Integer> queue;

    public static void dfs(int index){


    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        list = new ArrayList[N][M];
        visited = new boolean[N];
        graph = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }



        graph_dfs();
        graph_bfs();

        System.out.println(start);

    }





}*/
