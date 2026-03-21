

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int M;
    static int answer = Integer.MAX_VALUE;
    static List<int[]> list = new ArrayList<int[]>();
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    list.add(new int[]{i, j});
                    map[i][j] = 0;
                }
            }
        }

        dfs(0, 0, map);
        if(answer == Integer.MAX_VALUE){
            System.out.print(-1);
            return;
        }
        System.out.print(answer);
    }

    public static void dfs(int depth, int start, int[][] map) {
        if (depth == M) {
            int count = bfs(map);
            if (count != -1) {
                answer = Math.min(answer, count);
            }
            return;
        }

        for (int i = start; i < list.size(); i++) {
            int[] cur = list.get(i);
            int x = cur[0];
            int y = cur[1];

            map[x][y] = 2;              // 선택한 바이러스 활성화
            dfs(depth + 1, i + 1, map);
            map[x][y] = 0;              // 원복
        }

    }

    public static int bfs(int[][] map) {
        // 단지 시간 측정용
        int[][] dist = new int[N][N];
        Queue<int []> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dist[i][j] = -1;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 2) {
                    q.add(new int[]{i, j});
                    dist[i][j] = 0;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            for (int k = 0; k < 4; k++) {
                int nx = cur[0] + dir[k][0];
                int ny = cur[1] + dir[k][1];
                if(nx>=0 && ny>=0 && nx<N && ny<N && map[nx][ny] != 1 && dist[nx][ny] == -1) {
                    q.offer(new int[]{nx, ny});
                    dist[nx][ny] = dist[x][y] + 1;
                }
            }
        }
        int count = 0;
        count = count(dist, map);
        return count;
    }

    public static int count(int[][] dist, int[][] map) {
        int max = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) continue; // 벽 제외
                if (dist[i][j] == -1) {
                    return -1;
                }
                max = Math.max(max, dist[i][j]);
            }
        }
        return max;
    }
}
