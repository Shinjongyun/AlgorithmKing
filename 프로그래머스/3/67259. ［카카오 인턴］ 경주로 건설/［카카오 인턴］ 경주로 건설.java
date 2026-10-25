import java.util.*;

class Solution {

    static int answer;
    static int[][] board;
    static int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    static int n;
    static int[][][] dist;
    static final int INF = Integer.MAX_VALUE;

    public int solution(int[][] board) {

        Solution.board = board;
        n = board.length;
        answer = INF;

        dist = new int[n][n][2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dist[i][j], INF);
            }
        }

        back(0, 0, 0, 0);

        return answer;
    }

    public static void back(int type, int x, int y, int count) {

        dist[x][y][type] = count;

        if (x == n - 1 && y == n - 1) {
            answer = Math.min(answer, count);
            return;
        }

        for (int i = 0; i < 4; i++) {

            int nx = x + dir[i][0];
            int ny = y + dir[i][1];

            if (nx < 0 || ny < 0 || nx >= n || ny >= n ||
                board[nx][ny] == 1) continue;

            int nextType = (i < 2) ? 0 : 1;
            int newCount = count + 100;

            // 시작점이 아니고 방향이 바뀌면 코너 비용 추가
            if ((x != 0 || y != 0) && type != nextType) {
                newCount += 500;
            }

            if (newCount >= answer ||
                dist[nx][ny][nextType] <= newCount) continue;

            back(nextType, nx, ny, newCount);
        }
    }
}