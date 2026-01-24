package Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_14502_연구소 {
    static int N;
    static int M;
    static int[][] map;
    static Queue<int[]> q;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static boolean[][] visited;
    static int answer = 0;
    static int[][] virusMap;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        q = new LinkedList<>();
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);
        System.out.print(answer);
    }

    // 백트래킹으로 벽세우기
    public static void dfs(int wall) {
        if (wall == 3) {
            bfs();
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(wall + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    // 바이러스 퍼뜨리기
    public static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        virusMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                virusMap[i][j] = map[i][j];
                if (virusMap[i][j] == 2) {
                    q.add(new int[]{i, j});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && virusMap[nx][ny] == 0) {
                    virusMap[nx][ny] = 2;
                    q.add(new int[]{nx, ny});
                }
            }
        }
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (virusMap[i][j] == 0) {
                    count++;
                }
            }
        }
        answer = Math.max(answer, count);
    }
}
