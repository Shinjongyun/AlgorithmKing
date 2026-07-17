import java.util.*;

class Solution {

    static int INF = Integer.MAX_VALUE;
    static int answer = Integer.MAX_VALUE;

    // 방향까지 상태에 포함
    static int[][][] dist;

    static int n;
    static int[][] dir = {
        {0, 1}, {0, -1}, {-1, 0}, {1, 0}
    };

    static int[][] board;

    public int solution(int[][] board) {

        this.board = board;
        n = board.length;

        dist = new int[n][n][2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dist[i][j], INF);
            }
        }

        // 가로 상태
        // (0, 0), (0, 1)을 차지하므로 오른쪽 칸인 (0, 1)을 저장
        dfs(0, 1, 0, 0);

        return answer;
    }

    // type 0: 가로, type 1: 세로
    public static void dfs(int x, int y, int time, int type) {

        // 현재 로봇 위치가 범위를 벗어나거나 벽과 겹치는 경우
        if (check(x, y, type)) {
            return;
        }

        // 이미 구한 정답보다 오래 걸리면 탐색할 필요 없음
        if (time >= answer) {
            return;
        }

        // 같은 상태를 더 짧거나 같은 시간에 방문했다면 중단
        if (dist[x][y][type] <= time) {
            return;
        }

        dist[x][y][type] = time;

        if (isEnd(x, y)) {
            answer = Math.min(answer, time);
            return;
        }

        // 상하좌우 이동
        for (int i = 0; i < 4; i++) {

            int nx = x + dir[i][0];
            int ny = y + dir[i][1];

            dfs(nx, ny, time + 1, type);
        }

        // 가로 상태
        if (type == 0) {

            /*
             * 현재 상태:
             *
             * (x, y-1) (x, y)
             */

            // 위쪽으로 회전
            if (x - 1 >= 0
                    && board[x - 1][y - 1] == 0
                    && board[x - 1][y] == 0) {

                // 왼쪽 칸을 축으로 회전
                dfs(x, y - 1, time + 1, 1);

                // 오른쪽 칸을 축으로 회전
                dfs(x, y, time + 1, 1);
            }

            // 아래쪽으로 회전
            if (x + 1 < n
                    && board[x + 1][y - 1] == 0
                    && board[x + 1][y] == 0) {

                // 왼쪽 칸을 축으로 회전
                dfs(x + 1, y - 1, time + 1, 1);

                // 오른쪽 칸을 축으로 회전
                dfs(x + 1, y, time + 1, 1);
            }
        }

        // 세로 상태
        else {

            /*
             * 현재 상태:
             *
             * (x-1, y)
             * (x,   y)
             */

            // 왼쪽으로 회전
            if (y - 1 >= 0
                    && board[x - 1][y - 1] == 0
                    && board[x][y - 1] == 0) {

                // 위쪽 칸을 축으로 회전
                dfs(x - 1, y, time + 1, 0);

                // 아래쪽 칸을 축으로 회전
                dfs(x, y, time + 1, 0);
            }

            // 오른쪽으로 회전
            if (y + 1 < n
                    && board[x - 1][y + 1] == 0
                    && board[x][y + 1] == 0) {

                // 위쪽 칸을 축으로 회전
                dfs(x - 1, y + 1, time + 1, 0);

                // 아래쪽 칸을 축으로 회전
                dfs(x, y + 1, time + 1, 0);
            }
        }
    }

    // true면 이동 불가능
    public static boolean check(int x, int y, int type) {

        // 가로: (x, y-1), (x, y)
        if (type == 0) {

            if (x < 0 || x >= n || y < 0 || y >= n) {
                return true;
            }

            if (y - 1 < 0) {
                return true;
            }

            return board[x][y - 1] == 1
                    || board[x][y] == 1;
        }

        // 세로: (x-1, y), (x, y)
        else {

            if (x < 0 || x >= n || y < 0 || y >= n) {
                return true;
            }

            if (x - 1 < 0) {
                return true;
            }

            return board[x - 1][y] == 1
                    || board[x][y] == 1;
        }
    }

    public static boolean isEnd(int x, int y) {
        return x == n - 1 && y == n - 1;
    }
}