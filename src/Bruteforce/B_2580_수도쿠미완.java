package Bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_2580_수도쿠미완 {

    static int[][] map;
    static StringBuilder sb = new StringBuilder();
    static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        map = new int[10][10];

        for(int i = 1; i <= 9; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= 9; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) N++;
            }
        }

        dfs(1, 0);
        System.out.print(sb);
    }

    public static void dfs(int x, int turn){

        if(turn==N){
            for(int i = 1; i <= 9; i++){
                for(int j = 1; j <= 9; j++){
                    sb.append(map[i][j]+ " ");
                }
                sb.append("\n");
            }
            return;
        }

        for(int i = 1; i <= 9; i++){
            if(map[x][i]==0){
                for(int j = 1; j <= 9; j++){
                    if(find(x, i, j)){
                        map[x][i] = j;
                        dfs(x, turn+1);
                        map[x][i] = 0;
                    }
                }
            } else{
                if(x+1<=9) dfs(x+1, turn);
            }
        }
    }
    public static boolean find(int x, int y, int num){
        for(int i = 1; i <= 9; i++){
            // 가로
            if(num == map[x][i]){
                return false;
            }

            //세로
            if(num == map[i][y]){
                return false;
            }
        }

        int nx, ny;
        if(x <= 3){
            nx=1;
        } else if (x <= 6){
            nx=4;
        } else{
            nx=7;
        }

        if(y <= 3){
            ny=1;
        } else if (y <= 6){
            ny=4;
        } else{
            ny=7;
        }

        // 네모
        for(int i = nx; i <= nx+2; i++){
            for(int j = ny; j <= ny+2; j++){
                if(num == map[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}
