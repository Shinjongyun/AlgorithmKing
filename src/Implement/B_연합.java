package Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_연합 {

    static Queue<int []> q;
    static int L;
    static int R;
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int answer = 0;
    static int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];
        q = new LinkedList<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            visited = new boolean[N][N];
            boolean moved = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        if (bfs(i, j)) moved = true;
                    }
                }
            }

            if (!moved) break;
            answer++;
        }

        System.out.print(answer);
    }


    public static boolean bfs(int x, int y) {
        List<int []> union = new LinkedList<>();
        int total = map[x][y];
        visited[x][y] = true;
        q.add(new int [] {x, y});
        union.add(new int[]{x, y});
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for(int i = 0; i < 4; i++) {
                int nx = cur[0] + dir[i][0];
                int ny = cur[1] + dir[i][1];
                if(nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    if(!visited[nx][ny] && canPlace(cur[0], cur[1], nx, ny)) {
                        visited[nx][ny] = true;
                        q.add(new int[] {nx, ny});
                        union.add(new int[] {nx, ny});
                        total += map[nx][ny];
                    }
                }
            }
        }

        if (union.size() == 1) return false;
        int avg = total / union.size();
        for (int[] p : union) {
            map[p[0]][p[1]] = avg;
        }
        return true;
    }

    public static boolean canPlace(int x, int y, int nx, int ny) {
        if(Math.abs(map[x][y] - map[nx][ny]) >= L && Math.abs(map[x][y] - map[nx][ny]) <= R) {
            return true;
        }
        return false;
    }

}
