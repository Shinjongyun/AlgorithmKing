

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<int []> virus = new ArrayList<int []>();
    static int N;
    static int M;
    static int answer = Integer.MAX_VALUE;
    static int[][] dir = {{1,0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) virus.add(new int [] {i, j});
            }
        }

        back(0, 0, map);
        if(answer == Integer.MAX_VALUE) answer = -1;
        System.out.print(answer);
    }

    public static void back(int depth, int index, int[][] map) {

        if(depth == M){
            int count = bfs(map);
            if(count != -1){
                answer = Math.min(answer, count);
            }
            return;
        }

        for(int i = index; i < virus.size(); i++){
            int[] cur = virus.get(i);
            int x = cur[0];
            int y = cur[1];
            map[x][y] = 3;
            back(depth + 1, i+1, map);
            map[x][y] = 2;
        }
    }

    public static int bfs(int[][] pMap) {
        Queue<int []> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        int[][] map = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(pMap[i][j] == 3){
                    q.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            for(int i = 0; i < 4; i++){
                int newX = x + dir[i][0];
                int newY = y + dir[i][1];
                if(newX >= 0 && newX < N && newY >= 0 && newY < N && pMap[newX][newY] != 1){
                    if(!visited[newX][newY]){
                        visited[newX][newY] = true;
                        map[newX][newY] = map[x][y] + 1;
                        q.add(new int[]{newX, newY});
                    }
                }
            }
        }

        int count = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(pMap[i][j] == 0){
                    if(!visited[i][j]) return -1;
                    count = Math.max(count, map[i][j]);
                }
            }
        }
        return count;
    }
}
