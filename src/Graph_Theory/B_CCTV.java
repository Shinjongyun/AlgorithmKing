package Graph_Theory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B_CCTV {
    static int N;
    static int M;
    static int[] dir = {1,2,3,4};
    static int answer = Integer.MAX_VALUE;
    static int cctvCount = 0;
    static List<int []> cctvList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0 && map[i][j] != 6) {
                    cctvCount++;
                    cctvList.add(new int [] {i, j, map[i][j]});
                }
            }
        }

        dfs(0, map);
        System.out.print(answer);
    }

    public static void dfs(int depth, int[][] map){
        if(depth == cctvList.size()){
            answer = Math.min(answer, countZero(map));
            return;
        }

        int x = cctvList.get(depth)[0];
        int y = cctvList.get(depth)[1];
        int type = cctvList.get(depth)[2];

        for(int d : dir){
            int[][] copied = copyMap(map);

            if(type == 1) one(copied, x, y, d);
            else if(type == 2) two(copied, x, y, d);
            else if(type == 3) three(copied, x, y, d);
            else if(type == 4) four(copied, x, y, d);
            else if(type == 5) five(copied, x, y);

            dfs(depth + 1, copied);
        }
    }

    public static void one(int[][] map, int x, int y, int d) {
        if(d==1){
            for(int i=y; i<M; i++) {
                if(map[x][i]== 0) map[x][i] = 7;
                else if (map[x][i] == 6) break;
            }
        }
        if(d==2){
            for(int i=x; i<N; i++) {
                if(map[i][y]== 0) map[i][y] = 7;
                else if (map[i][y] == 6) break;
            }
        }
        if(d==3){
            for(int i=y; i>=0; i--) {
                if(map[x][i]== 0) map[x][i] = 7;
                else if (map[x][i] == 6) break;
            }
        }
        if(d==4){
            for(int i=x; i>=0; i--) {
                if(map[i][y]== 0) map[i][y] = 7;
                else if (map[i][y] == 6) break;
            }
        }
    }

    public static void two(int[][] map, int x, int y, int d) {
        if(d==1 || d==3){
            for(int i=y; i<M; i++) {
                if(map[x][i]== 0) map[x][i] = 7;
                else if (map[x][i] == 6) break;
            }
            for(int i=y; i>=0; i--) {
                if(map[x][i]== 0) map[x][i] = 7;
                else if (map[x][i] == 6) break;
            }
        }
        if(d==2 || d==4) {
            for(int i=x; i<N; i++) {
                if(map[i][y]== 0) map[i][y] = 7;
                else if (map[i][y] == 6) break;
            }
            for(int i=x; i>=0; i--) {
                if(map[i][y]== 0) map[i][y] = 7;
                else if (map[i][y] == 6) break;
            }
        }
    }

    public static void three(int[][] map, int x, int y, int d) {
        if(d==1){
            for(int i=y; i<M; i++) {
                if(map[x][i]== 0) map[x][i] = 7;
                else if (map[x][i] == 6) break;
            }
            for(int i=x; i>=0; i--) {
                if(map[i][y]== 0) map[i][y] = 7;
                else if (map[i][y] == 6) break;
            }
        }
        if(d==2){
            for(int i=y; i<M; i++) {
                if(map[x][i]== 0) map[x][i] = 7;
                else if (map[x][i] == 6) break;
            }
            for(int i=x; i<N; i++) {
                if(map[i][y]== 0) map[i][y] = 7;
                else if (map[i][y] == 6) break;
            }
        }
        if(d==3){
            for(int i=x; i<N; i++) {
                if(map[i][y]== 0) map[i][y] = 7;
                else if (map[i][y] == 6) break;
            }
            for(int i=y; i>=0; i--) {
                if(map[x][i]== 0) map[x][i] = 7;
                else if (map[x][i] == 6) break;
            }
        }
        if(d==4){
            for(int i=y; i>=0; i--) {
                if(map[x][i]== 0) map[x][i] = 7;
                else if (map[x][i] == 6) break;
            }
            for(int i=x; i>=0; i--) {
                if(map[i][y]== 0) map[i][y] = 7;
                else if (map[i][y] == 6) break;
            }
        }
    }

    public static void four(int[][] map, int x, int y, int d) {
        if(d==1){
            for(int i=y; i<M; i++) {
                if(map[x][i]== 0) map[x][i] = 7;
                else if (map[x][i] == 6) break;
            }
            for(int i=x; i>=0; i--) {
                if(map[i][y]== 0) map[i][y] = 7;
                else if (map[i][y] == 6) break;
            }
            for(int i=x; i<N; i++) {
                if(map[i][y]== 0) map[i][y] = 7;
                else if (map[i][y] == 6) break;
            }
        }
        if(d==2){
            for(int i=y; i<M; i++) {
                if(map[x][i]== 0) map[x][i] = 7;
                else if (map[x][i] == 6) break;
            }
            for(int i=x; i<N; i++) {
                if(map[i][y]== 0) map[i][y] = 7;
                else if (map[i][y] == 6) break;
            }
            for(int i=y; i>=0; i--) {
                if(map[x][i]== 0) map[x][i] = 7;
                else if (map[x][i] == 6) break;
            }
        }
        if(d==3){
            for(int i=y; i>=0; i--) {
                if(map[x][i]== 0) map[x][i] = 7;
                else if (map[x][i] == 6) break;
            }
            for(int i=x; i>=0; i--) {
                if(map[i][y]== 0) map[i][y] = 7;
                else if (map[i][y] == 6) break;
            }
            for(int i=x; i<N; i++) {
                if(map[i][y]== 0) map[i][y] = 7;
                else if (map[i][y] == 6) break;
            }
        }
        if(d==4){
            for(int i=x; i>=0; i--) {
                if(map[i][y]== 0) map[i][y] = 7;
                else if (map[i][y] == 6) break;
            }
            for(int i=y; i>=0; i--) {
                if(map[x][i]== 0) map[x][i] = 7;
                else if (map[x][i] == 6) break;
            }
            for(int i=y; i<M; i++) {
                if(map[x][i]== 0) map[x][i] = 7;
                else if (map[x][i] == 6) break;
            }
        }
    }

    public static void five(int[][] map, int x, int y) {
        for(int i=y; i<M; i++) {
            if(map[x][i]== 0) map[x][i] = 7;
            else if(map[x][i] == 6) break;
        }
        for(int i=y; i>=0; i--) {
            if(map[x][i]== 0) map[x][i] = 7;
            else if(map[x][i] == 6) break;
        }
        for(int i=x; i<N; i++) {
            if(map[i][y]== 0) map[i][y] = 7;
            else if(map[i][y] == 6) break;
        }
        for(int i=x; i>=0; i--) {
            if(map[i][y]== 0) map[i][y] = 7;
            else if(map[i][y] == 6) break;
        }
    }

    public static int countZero(int[][] map){
        int cnt = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 0) cnt++;
            }
        }
        return cnt;
    }

    public static int[][] copyMap(int[][] map){
        int[][] copied = new int[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                copied[i][j] = map[i][j];
            }
        }
        return copied;
    }
}
