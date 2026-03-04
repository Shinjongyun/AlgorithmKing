package Graph_Theory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_2667_아파트단지 {

    static List<Integer> danjee;
    static int[][] map;
    static int N;
    static int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        danjee = new ArrayList<>();
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(!visited[i][j] && map[i][j] == 1) {
                    bfs(i, j);
                }
            }
        }

        danjee.sort(Comparator.naturalOrder());
        System.out.println(danjee.size());
        for(int i = 0; i < danjee.size(); i++) {
            System.out.println(danjee.get(i));
        }
    }

    public static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        int count = 1;
        visited[x][y] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for(int i = 0; i <4; i++){
                int nx = cur[0] + dir[i][0];
                int ny = cur[1] + dir[i][1];
                if(nx >=0 && ny>=0 && nx < N && ny < N){
                    if(map[nx][ny] == 1 && !visited[nx][ny]){
                        count++;
                        q.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        danjee.add(count);
    }





}
