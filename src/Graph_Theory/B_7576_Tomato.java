package Graph_Theory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_7576_Tomato {

    static int N;
    static int M;
    static int[][] tomato;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        tomato = new int[N][M];

        // 1. 익은 토마토(1)만 큐에 추가
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                tomato[i][j] = Integer.parseInt(st.nextToken());
                if (tomato[i][j] == 1) {
                    q.add(new int[]{i, j});
                }
            }
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 2. 경계값 확인 및 익지 않은 토마토(0)인 경우만 탐색
                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (tomato[nx][ny] == 0) {
                        q.add(new int[]{nx, ny});
                        // 3. 날짜를 세는 로직
                        tomato[nx][ny] = tomato[x][y] + 1;
                    }
                }
            }
        }

        int maxDays = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 4. 익지 않은 토마토(0)가 남아있으면 -1 반환
                if (tomato[i][j] == 0) {
                    return -1;
                }
                // 5. 최댓값 계산
                maxDays = Math.max(maxDays, tomato[i][j]);
            }
        }

        // 6. 결과 반환: 시작 날짜가 1이므로, 총 날짜를 구하려면 1을 빼야 함
        // 예외: 이미 모든 토마토가 익어있다면 maxDays가 1이므로, 0을 반환
        return maxDays - 1;
    }
}