package Graph_Theory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_2194_Unit_Moving {
    static int N;
    static int M;
    static int[][] graph;
    static boolean[][] visited;
    static int A;
    static int B;
    static int K;
    static int SX;
    static int SY;
    static int EX;
    static int EY;
    static int result;
    static Queue<int[]> q = new LinkedList<int[]>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x][y] = 1;
        }

        st = new StringTokenizer(br.readLine());
        SX = Integer.parseInt(st.nextToken());
        SY = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        EX = Integer.parseInt(st.nextToken());
        EY = Integer.parseInt(st.nextToken());

        q.add(new int[]{SX, SY});
        visited[SX][SY] = true;
        result = bfs();
        System.out.println(result);
    }

    public static int bfs() {
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if (nextX < 1 || nextX > N || nextY < 1 || nextY > M) continue;
                if (visited[nextX][nextY]) continue;
                if (!check(nextX, nextY)) continue;

                visited[nextX][nextY] = true;
                q.add(new int[]{nextX, nextY});
                graph[nextX][nextY] = graph[x][y] + 1;

                if (nextX == EX && nextY == EY) {
                    return graph[nextX][nextY];
                }
            }
        }
        return -1;
    }

    public static boolean check(int x, int y) {

        if (x + A - 1 > N || y + B - 1 > M) return false;

        // 정확한 범위 (x..x+A-1, y..y+B-1) 순회
        for (int i = x; i <= x + A - 1; i++) {
            for (int j = y; j <= y + B - 1; j++) {
                if (graph[i][j] == 1) return false;
            }
        }
        return true;
    }
}