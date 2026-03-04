

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<int []> list = new ArrayList<int []>();
    static boolean solve = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] map = new int[9][9];
        for(int i=0; i<9; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==0){
                    list.add(new int[] {i, j});
                }
            }
        }

        dfs(0, map);

    }

    public static void dfs(int depth, int[][] map){
        if(depth == list.size() && !solve){
            solve = true;
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<9; i++){
                for(int j=0; j<9; j++){
                    sb.append(map[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.print(sb);
            return;
        }

        int[] cur = list.get(depth);
        int x = cur[0];
        int y = cur[1];
        for(int i=1; i<=9; i++){
            if(canPlace(x, y, i, map)){
                map[x][y] = i;
                dfs(depth+1, map);
                map[x][y] = 0; // 백트래킹
                if(solve) return;
            }
        }
    }

    public static boolean canPlace(int x, int y, int num, int[][] map){
        // 가로 확인
        for(int i=0; i<9; i++){
            if(map[x][i] == num) return false;
        }

        // 세로 확인
        for(int i=0; i<9; i++){
            if(map[i][y] == num) return false;
        }

        // 네모 확인
        int nx = x/3 * 3;
        int ny = y/3 * 3;
        for(int i=nx; i<nx+3; i++){
            for(int j=ny; j<ny+3; j++){
                if(map[i][j] == num) return false;
            }
        }
        return true;
    }
}
