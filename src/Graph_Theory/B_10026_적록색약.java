package Graph_Theory;

import java.io.*;
import java.util.*;

public class B_10026_적록색약 {
    static int[][] d = {{0,1}, {0,-1}, {-1,0}, {1,0}};
    static String[][] arr;
    static boolean[][] visited;
    static int N;
    static int answer1;
    static int answer2;
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        arr = new String[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = String.valueOf(line.charAt(j));
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!visited[i][j]){
                    original(i, j);
                    answer1++;
                }
            }
        }

        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(arr[i][j].equals("G")){
                    arr[i][j] = "R";
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!visited[i][j]){
                    original(i, j);
                    answer2++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(answer1).append(" ").append(answer2);
        System.out.print(sb);
    }

    public static void original(int x, int y) {
        visited[x][y] = true;
        q.offer(new int[]{x, y});
        while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int i=0;i<4;i++){
                int nx = cur[0]+d[i][0];
                int ny = cur[1]+d[i][1];
                if(nx>=0 && ny>=0 && nx<N && ny<N && !visited[nx][ny] && arr[x][y].equals(arr[nx][ny])) {
                    q.offer(new int[]{nx,ny});
                    visited[nx][ny] = true;
                }
            }
        }
    }
}
