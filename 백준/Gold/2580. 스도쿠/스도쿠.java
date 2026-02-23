

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static boolean solved = false;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        map = new int[9][9];
        for(int i = 0; i<9; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<9; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();

        dfs(0, 0);

        for(int i = 0; i<9; i++){
            for(int j = 0; j<9; j++){
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int x, int y){
        if (solved) return;

        if (x == 9) { 
            solved = true;
            return;
        }

        if (y == 9) { 
            dfs(x + 1, 0);
            return;
        }

        if (map[x][y] == 0) {
            for (int v = 1; v <= 9; v++) {
                if (canPlace(x, y, v)) {
                    map[x][y] = v;
                    dfs(x, y + 1);
                    if (solved) return;
                    map[x][y] = 0; 
                }
            }
        } else {
            dfs(x, y + 1); 
        }
    }

    public static boolean canPlace(int x, int y, int num){
        for(int i = 0; i<9; i++){
            if(map[x][i] == num){
                return false;
            }
        }
        for(int i = 0; i<9; i++){
            if(map[i][y] == num){
                return false;
            }
        }
        int set_row = (x / 3) * 3;
        int set_col = (y / 3) * 3;

        for (int i = set_row; i < set_row + 3; i++) {
            for (int j = set_col; j < set_col + 3; j++) {
                if (map[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }
}


