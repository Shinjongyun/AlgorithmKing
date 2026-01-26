package Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_15686_치킨배달 {

    static int N, M;
    static int[][] map;
    static int answer = Integer.MAX_VALUE;
    static ArrayList<int[]> chickenList = new ArrayList<>();
    static ArrayList<int[]> houseList = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == 2) chickenList.add(new int[]{i, j});
                else if(map[i][j] == 1) houseList.add(new int[]{i, j});
            }
        }
        visited = new boolean[chickenList.size()];

        dfs(0, 0);
        System.out.print(answer);
    }

    private static void dfs(int count, int index) {
        if(count == M) {
            answer = Math.min(answer, distance());
        }
        for(int i = index; i < chickenList.size(); i++) {
            if(!visited[i]) {
                visited[i] = true;
                dfs(count + 1, i + 1);
                visited[i] = false;
            }
        }
    }

    private static int distance() {
        int total = 0;
        for(int[] cur : houseList){
            int best = Integer.MAX_VALUE;
            for(int i = 0; i < chickenList.size(); i++){
                if(visited[i]) {
                    int[] end = chickenList.get(i);
                    int d = Math.abs(end[0]-cur[0]) + Math.abs(end[1]-cur[1]);
                    best = Math.min(best, d);
                }
            }
            total += best;
        }
        return total;
    }
}

