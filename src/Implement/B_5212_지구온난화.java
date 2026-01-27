package Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_5212_지구온난화 {

    static int R;
    static int C;
    static char[][] map;
    static char[][] answer;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        answer = new char[R][C];
        for(int i = 0; i < R; i++) {
            String line = br.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                answer[i][j] = line.charAt(j);
            }
        }

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] == 'X') {
                    change(i, j);
                }
            }
        }

        // 오줌 찌이이익
        int minR = R, maxR = -1, minC = C, maxC = -1;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (answer[i][j] == 'X') {
                    minR = Math.min(minR, i);
                    maxR = Math.max(maxR, i);
                    minC = Math.min(minC, j);
                    maxC = Math.max(maxC, j);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = minR; i <= maxR; i++) {
            for (int j = minC; j <= maxC; j++) {
                sb.append(answer[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    public static void change(int i, int j) {
        int sur = 0;
        for(int k = 0; k < 4; k++) {
            int nx = i + dir[k][0];
            int ny = j + dir[k][1];
            if(nx >=0 && nx < R && ny >= 0 && ny < C && map[nx][ny] == '.') {
                sur++;
            } else if(nx<0 || nx>= R || ny < 0 || ny >= C) {
                sur++;
            }
        }
        if(sur>=3) answer[i][j] = '.';
    }

    public static void print(int i, int j) {
    }
}
