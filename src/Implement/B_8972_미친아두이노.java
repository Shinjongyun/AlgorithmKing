package Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B_8972_미친아두이노 {
    static int R;
    static int C;
    static int[] order;
    static char[][] map;
    static StringBuilder sb = new StringBuilder();
    static int K;
    static int[] me;
    static ArrayList<int[]> crazy;
    static int[][] dir = {{1, -1}, {1, 0}, {1, 1}, {0, -1}, {0, 0}, {0, 1}, {-1, -1}, {-1, 0}, {-1, 1}};
    static boolean flag = false;
    static int[][] visited;
    static int deadTurn = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());

        me = new int[2];
        crazy = new ArrayList<>();
        map = new char[R][C];
        visited = new int[R][C];
        for(int i=0; i<R; i++){
            String line = br.readLine();
            for(int j=0; j<C; j++){
                map[i][j] = line.charAt(j);
                if(map[i][j] == 'I'){
                    me[0]=i;
                    me[1]=j;
                }
                if(map[i][j] == 'R'){
                    crazy.add(new int[]{i,j});
                    visited[i][j] = crazy.size();
                }
            }
        }

        String line = br.readLine();
        K=line.length();
        order = new int[K];
        for(int i=0; i<K; i++){
            order[i] = line.charAt(i) - '0';
        }

        for(int i=0; i<K; i++){
            peyz(order[i]-1, i+1);
            if(flag){
                break;
            }
        }

        if(flag){
            System.out.print("kraj " + deadTurn);
            return;
        }
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    public static void peyz(int o, int turn){
        command(o);
        for (int i = 0; i < crazy.size(); i++) {                           // 내가 아두이노에 갖다 박는 경우
            if (crazy.get(i)[0] == me[0] && crazy.get(i)[1] == me[1]) {
                flag = true;
                deadTurn = turn;
                return;
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                visited[i][j] = 0;
            }
        }

        for(int i=0; i<crazy.size(); i++) {
            int x = crazy.get(i)[0];
            int y = crazy.get(i)[1];
            int total = Integer.MAX_VALUE;
            int nx = crazy.get(i)[0];
            int ny = crazy.get(i)[1];
            int jebal = 0;
            for (int j = 0; j < dir.length; j++) {                           // 아두이노 움직이기
                nx += dir[j][0];
                ny += dir[j][1];
                if (total > Math.abs(me[0] - nx) + Math.abs(me[1] - ny)) {
                    total = Math.abs(me[0] - nx) + Math.abs(me[1] - ny);
                    jebal = j;
                }
                nx -= dir[j][0];
                ny -= dir[j][1];
            }
            nx += dir[jebal][0];
            ny += dir[jebal][1];
            crazy.get(i)[0] = nx;
            crazy.get(i)[1] = ny;

            visited[nx][ny]++;

            if (nx == me[0] && ny == me[1]) {    // 아두이노가 종수를 잡는 경우
                if (turn < K) flag = true;
                deadTurn = turn;
                return;
            }
        }

        // map 싹 초기화 후 I + 살아남은 R만 다시 그림
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = '.';
            }
        }
        map[me[0]][me[1]] = 'I';

        ArrayList<int[]> newCrazy = new ArrayList<>();
        for (int i = 0; i < crazy.size(); i++) {
            int nx = crazy.get(i)[0];
            int ny = crazy.get(i)[1];

            // 2대 이상 모이면 폭발 → 제외
            if (visited[nx][ny] == 1) {
                newCrazy.add(new int[]{nx, ny});
                map[nx][ny] = 'R';
            }
        }
        crazy = newCrazy;
    }

    public static void command(int command){
        map[me[0]][me[1]] = '.';
        int nx = me[0] + dir[command][0];
        int ny = me[1] + dir[command][1];
        me[0] = nx;
        me[1] = ny;
        map[nx][ny] = 'I';
    }
}
