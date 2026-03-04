package Bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_9663_NQueen {

    static int N;
    static int[] queen;
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        queen = new int[N];

        dfs(0);
        System.out.print(answer);
    }

    public static void dfs(int row){
        // N개의 퀸 배치 완료
        if(row == N){
            answer++;
            return;
        }

        for (int j = 0; j < N; j++) {
            if (canPlace(row, j)) {
                queen[row] = j;
                dfs(row + 1);
            }
        }
    }

    public static boolean canPlace(int row, int col){
        // 지금 놓혀져 있는 모든 퀸의 갯수 만큼 루프돌리기
        for(int i = 0; i < row; i++){
            // 같은 열인 경우
            if(queen[i] == col) return false;
            // 대각선에 배치되는 경우
            if (Math.abs(row - i) == Math.abs(col - queen[i])) return false;
        }
        return true;
    }
}
