

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static int[][] tomato;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int answer = 1;
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 가로(열)
        N = Integer.parseInt(st.nextToken()); // 세로(행)

        tomato = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                tomato[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(tomato[i][j] == 1){
                    q.add(new int[]{i, j});
                }
            }
        }

        bfs();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tomato[i][j] == 0) {
                    System.out.print(-1);
                    return;
                }
                answer = Math.max(answer, tomato[i][j]);
            }
        }
        System.out.print(answer -1);
    }

    public static void bfs() {
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int[] dir : dir) {
                int x = cur[0] + dir[0];
                int y = cur[1] + dir[1];
                if(x>=0 && y>=0 && x<N && y<M && tomato[x][y]==0) {
                    tomato[x][y] = tomato[cur[0]][cur[1]] + 1;
                    q.offer(new int[]{x, y});
                }
            }
        }
    }

}
