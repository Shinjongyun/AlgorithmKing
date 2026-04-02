

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<int []> wall = new ArrayList<int []>();
    static int N;
    static int M;
    static int answer;
    static int[][] dir = {{1,0}, {-1, 0}, {0, 1}, {0, -1}};

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
                if(map[i][j] == 0) wall.add(new int [] {i, j});
            }
        }

        back(0, 0, map);
        System.out.print(answer);
    }

    public static void back(int depth, int index, int[][] map) {

        if(depth == 3){
            int count = bfs(map);
            answer = Math.max(answer, count);
            return;
        }

        for(int i = index; i < wall.size(); i++){
            int[] cur = wall.get(i);
            int x = cur[0];
            int y = cur[1];
            if(map[x][y] == 0){
                map[x][y] = 1;
                back(depth + 1, index+1, map);
                map[x][y] = 0;
            }
        }
    }

    public static int bfs(int[][] pMap) {
        Queue<int []> q = new LinkedList<>();
        int[][] map = new int[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                map[i][j] = pMap[i][j];
                if(map[i][j] == 2){
                    q.add(new int[]{i, j});
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
                if(newX >= 0 && newX < N && newY >= 0 && newY < M && map[newX][newY] != 1){
                    if(map[newX][newY] == 0){
                        map[newX][newY] = 2;
                        q.add(new int[]{newX, newY});
                    }
                }
            }
        }

        int count = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 0){
                    count++;
                }
            }
        }
        return count;
    }
}
