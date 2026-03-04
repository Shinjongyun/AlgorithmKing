package Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_체스판다시칠하기 {
    static int N;
    static int M;
    static int answer = Integer.MAX_VALUE;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        for(int i = 0; i <= N-8; i++) {
            for(int j = 0; j <= M-8; j++) {
                bfs(i, j);
            }
        }
        System.out.print(answer);
    }

    public static void bfs(int x, int y) {
        int count = 0;
        String[] st = {"BWBWBWBW", "WBWBWBWB"};
        for(int i = 0; i < 8; i++) {
            int row = x + i;
            for(int j = 0; j < 8; j++) {
                int col = y + j;
                if(map[row][col] != st[row % 2].charAt(j)) {
                    count++;
                }
            }
        }
        int total = Math.min(count, 64 - count);
        answer = Math.min(answer, total);
    }
}
