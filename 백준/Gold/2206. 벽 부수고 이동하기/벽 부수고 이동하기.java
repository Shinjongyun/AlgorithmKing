
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static int[][] map;
    static boolean[][][] visited;
    static int[][][] dist;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dist = new int[N][M][2];
        visited = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
            }
        }

        System.out.print(bfs(0,0));
    }

    public static int bfs(int x, int y) {
        Queue<int []> q = new LinkedList<>();

        // 상태: x, y, broke(0/1)
        q.offer(new int[]{x, y, 0});
        visited[x][y][0] = true;
        dist[x][y][0] = 1;

        while (!q.isEmpty()) {

            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];
            int broke = cur[2];

            if (cx == N - 1 && cy == M - 1) {
                return dist[cx][cy][broke];
            }

            for (int i = 0; i < 4; i++) {
                int nx = cx + dir[i][0];
                int ny = cy + dir[i][1];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {

                    // 1) 다음이 빈칸이면 그냥 이동
                    if (!visited[nx][ny][broke] && map[nx][ny] == 0) {
                        q.offer(new int[]{nx, ny, broke});
                        visited[nx][ny][broke] = true;
                        dist[nx][ny][broke] = dist[cx][cy][broke] + 1;
                    }

                    // 2) 다음이 벽인데, 아직 안 부쉈으면 부수고 이동
                    if (broke == 0 && !visited[nx][ny][1] && map[nx][ny] == 1) {
                        q.offer(new int[]{nx, ny, 1});
                        visited[nx][ny][1] = true;
                        dist[nx][ny][1] = dist[cx][cy][broke] + 1;
                    }
                }
            }
        }

        return -1;
    }
}
