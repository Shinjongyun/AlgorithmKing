package Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_2638_치즈 {

    static int N;
    static int M;
    static boolean[][] cheese;
    static int answer = 0;
    static int remain = 0;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] outside;
    static Queue<int[]> cq = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cheese = new boolean[N][M];
        outside = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                String ch = st.nextToken();
                if(Integer.parseInt(ch)==1) {
                    cheese[i][j] = true;
                    remain++;
                }
                else cheese[i][j] = false;
            }
        }

        while (remain > 0) {
            // outside 매 턴 초기화
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    outside[i][j] = false;
                }
            }

            // 외부 공기 다시 계산
            outsiding();

            // 치즈 좌표 큐에 넣기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (cheese[i][j]) cq.offer(new int[]{i, j});
                }
            }
            melting();
        }

        System.out.print(answer);
    }

    public static void outsiding() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        outside[0][0] = true;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for(int i = 0; i < 4; i++) {
                int nx = cur[0] + dir[i][0];
                int ny = cur[1] + dir[i][1];
                if(nx>=0 && nx<N && ny>=0 && ny<M && !cheese[nx][ny] && !outside[nx][ny]) {
                    q.offer(new int[]{nx, ny});
                    outside[nx][ny] = true;
                }
            }
        }
    }

    public static void melting() {
        ArrayList<int[]> melt = new ArrayList<>();

        while (!cq.isEmpty()) {
            int[] cur = cq.poll();
            int count = 0;

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dir[i][0];
                int ny = cur[1] + dir[i][1];

                // 외부공기와 맞닿은 면만 카운트
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && outside[nx][ny]) {
                    count++;
                }
            }

            if (count >= 2) melt.add(cur);
        }

        // 동시에 녹이기
        for (int[] cur : melt) {
            cheese[cur[0]][cur[1]] = false;
            remain--;
        }

        answer++;
    }
}