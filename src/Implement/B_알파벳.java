package Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_알파벳 {

    static int R;
    static int C;
    static char[][] map;
    static int answer = 0;
    static boolean[] visited;
    static Queue<int[]> q;
    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[26];
        q = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        dfs(0, 0, 1);
        System.out.print(answer);
    }

    public static void dfs(int x, int y, int depth) {

        answer = Math.max(answer, depth);
        int cur = map[x][y]  - 'A';
        visited[cur] = true;
        for(int i = 0; i < dir.length; i++) {
            int newX = x + dir[i][0];
            int newY = y + dir[i][1];
            if(newX >= 0 && newX < R && newY >= 0 && newY < C) {
                if(!visited[map[newX][newY]  - 'A']) {
                    dfs(newX, newY, depth + 1);
                }

            }
        }
        visited[cur] = false;
    }
}
