package Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_14500_테트릭스 {
    static int N;
    static int M;
    static int[][] map;
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                blue1(i, j);
                blue2(i, j);
                yellow(i, j);
                orange1(i, j);
                orange2(i, j);
                orange3(i, j);
                orange4(i, j);
                orange5(i, j);
                orange6(i, j);
                orange7(i, j);
                orange8(i, j);
                green1(i, j);
                green2(i, j);
                green3(i, j);
                green4(i, j);
                red1(i, j);
                red2(i, j);
                red3(i, j);
                red4(i, j);
            }
        }

        System.out.print(answer);
    }

    public static void blue1(int x, int y) {
        int result = 0;
        for(int i = 0; i < 4; i++) {
            if(i+y<M) result += map[x][y+i];
            else return;
        }
        answer = Math.max(answer, result);
    }

    public static void blue2(int x, int y) {
        int result = 0;
        for(int i = 0; i < 4; i++) {
            if(i+x<N) result += map[x+i][y];
            else return;
        }
        answer = Math.max(answer, result);
    }

    public static void yellow(int x, int y) {
        int result = 0;
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                if(j+y<M && i+x<N) result += map[x+i][y+j];
                else return;
            }
        }
        answer = Math.max(answer, result);
    }
    public static void orange1(int x, int y) {
        int result = 0;
        for(int i = 0; i < 3; i++) {
            if (i + x < N) result += map[i + x][y];
            else return;
            if(i==2) {
                if(y+1<M){
                    result += map[i + x][y + 1];
                }
                else return;
            }
        }
        answer = Math.max(answer, result);
    }

    public static void orange2(int x, int y) {
        int result = 0;
        for(int i = 0; i < 3; i++) {
            if (i + y < M) result += map[x][y+i];
            else return;
            if(i==0) {
                if(x+1<N){
                    result += map[x+1][y+i];
                }
                else return;
            }
        }
        answer = Math.max(answer, result);
    }

    public static void orange3(int x, int y) {
        int result = 0;
        for(int i = 0; i < 3; i++) {
            if (i + x < N) result += map[i + x][y];
            else return;
            if(i==0) {
                if(y+1<M){
                    y++;
                    result += map[i + x][y];
                }
                else return;
            }
        }
        answer = Math.max(answer, result);
    }

    public static void orange4(int x, int y) {
        int result = 0;
        for(int i = 0; i < 3; i++) {
            if (i + y < M) result += map[x][y+i];
            else return;
            if(i==2) {
                if(x-1>=0){
                    result += map[x-1][y+i];
                }
                else return;
            }
        }
        answer = Math.max(answer, result);
    }

    // ┘ 모양
    public static void orange5(int x, int y) {
        if (x + 2 >= N || y - 1 < 0) return;
        int result =
                map[x][y] +
                        map[x+1][y] +
                        map[x+2][y] +
                        map[x+2][y-1];
        answer = Math.max(answer, result);
    }

    // └ 모양
    public static void orange6(int x, int y) {
        if (x + 1 >= N || y + 2 >= M) return;
        int result =
                map[x][y] +
                        map[x][y+1] +
                        map[x][y+2] +
                        map[x+1][y+2];
        answer = Math.max(answer, result);
    }

    // ┌ 모양
    public static void orange7(int x, int y) {
        if (x + 2 >= N || y + 1 >= M) return;
        int result =
                map[x][y] +
                        map[x+1][y] +
                        map[x+2][y] +
                        map[x][y+1];
        answer = Math.max(answer, result);
    }

    // ┐ 모양
    public static void orange8(int x, int y) {
        if (x - 1 < 0 || y + 2 >= M) return;
        int result =
                map[x][y] +
                        map[x][y+1] +
                        map[x][y+2] +
                        map[x-1][y];
        answer = Math.max(answer, result);
    }

    public static void green1(int x, int y) {
        int result = 0;
        for(int i = 0; i < 3; i++) {
            if(i+x<N) result += map[i+x][y];
            else return;
            if(i==1) {
                if(y + 1 < M){
                    y++;
                    result += map[x+i][y];
                }
                else return;
            }
        }
        answer = Math.max(answer, result);
    }

    public static void green2(int x, int y) {
        int result = 0;
        for(int i = 0; i < 3; i++) {
            if(i+y<M) result += map[x][y+i];
            else return;
            if(i==1) {
                if(x - 1 >= 0){
                    x--;
                    result += map[x][y+i];
                }
                else return;
            }
        }
        answer = Math.max(answer, result);
    }

    // Z 가로
    public static void green3(int x, int y) {
        if (x + 1 >= N || y + 2 >= M) return;
        int result =
                map[x][y] +
                        map[x][y+1] +
                        map[x+1][y+1] +
                        map[x+1][y+2];
        answer = Math.max(answer, result);
    }

    // Z 세로
    public static void green4(int x, int y) {
        if (x + 2 >= N || y - 1 < 0) return;
        int result =
                map[x][y] +
                        map[x+1][y] +
                        map[x+1][y-1] +
                        map[x+2][y-1];
        answer = Math.max(answer, result);
    }

    public static void red1(int x, int y) {
        int result = 0;
        for(int i = 0; i < 3; i++) {
            if(i+y<M) result += map[x][y + i];
            else return;
            if(i==1) {
                if(x+1<N) result += map[x + 1][y+i];
                else return;
            }
        }
        answer = Math.max(answer, result);
    }

    public static void red2(int x, int y) {
        int result = 0;
        for(int i = 0; i < 3; i++) {
            if(i+x<N) result += map[x+i][y];
            else return;
            if(i==1) {
                if(y-1>=0) {
                    result += map[x+i][y-1];
                }
                else return;
            }
        }
        answer = Math.max(answer, result);
    }

    public static void red3(int x, int y) {
        int result = 0;
        for(int i = 0; i < 3; i++) {
            if(i+y<M) result += map[x][y + i];
            else return;
            if(i==1) {
                if(x-1>=0) result += map[x - 1][y+i];
                else return;
            }
        }
        answer = Math.max(answer, result);
    }

    public static void red4(int x, int y) {
        int result = 0;
        for(int i = 0; i < 3; i++) {
            if(i+x<N) result += map[x+i][y];
            else return;
            if(i==1) {
                if(y+1<M) result += map[x+i][y+1];
                else return;
            }
        }
        answer = Math.max(answer, result);
    }
}
