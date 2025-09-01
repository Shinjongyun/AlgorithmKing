package Graph_Theory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_2178_BFS_MIRO {

    static int N;
    static int M;
    static int[][] graph;
    static int result = 0;
    static int maxdays =0 ;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];

        for(int i = 0; i < N; i++){
            String line = br.readLine();
            for(int j = 0; j < M; j++){
                graph[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
            }
        }

        q.add(new int [] {0,0});
        result = bfs();
        System.out.println(result);

    }

    public static int bfs(){
        while(!q.isEmpty()){
            int[] p = q.poll();
            int x = p[0];
            int y = p[1];

            for(int k = 0; k < 4; k++){
                int nx = x + dx[k];
                int ny = y + dy[k];

                if(nx>=0 && ny>=0 && nx<N && ny<M && graph[nx][ny]==1){
                    q.add(new int[]{nx, ny});
                    graph[nx][ny] = graph[x][y] + 1;
                    if(nx==N-1 && ny==M-1){
                        return graph[nx][ny];
                    }
                }
            }

        }
        return 0 ;
    }
}
