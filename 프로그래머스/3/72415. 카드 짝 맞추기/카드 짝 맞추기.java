import java.util.*;

class Solution {

    static int[][] board;
    static int[][] position = new int[7][4];
    static boolean[] exists = new boolean[7];

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public int solution(int[][] board, int r, int c) {

        Solution.board = board;

        int[] count = new int[7];

        // 카드 위치 저장
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                int card = board[i][j];

                if (card == 0) {
                    continue;
                }

                exists[card] = true;

                if (count[card] == 0) {
                    position[card][0] = i;
                    position[card][1] = j;
                } else {
                    position[card][2] = i;
                    position[card][3] = j;
                }

                count[card]++;
            }
        }

        return dfs(r, c);
    }

    static int dfs(int r, int c) {

        int answer = Integer.MAX_VALUE;
        boolean cardRemain = false;

        // 다음에 제거할 카드 선택
        for (int card = 1; card <= 6; card++) {

            if (!exists[card]) {
                continue;
            }

            cardRemain = true;

            int r1 = position[card][0];
            int c1 = position[card][1];

            int r2 = position[card][2];
            int c2 = position[card][3];

            /*
             * 현재 위치 → 첫 번째 카드 → 두 번째 카드
             */
            int first =
                    bfs(r, c, r1, c1)
                    + bfs(r1, c1, r2, c2)
                    + 2;

            /*
             * 현재 위치 → 두 번째 카드 → 첫 번째 카드
             */
            int second =
                    bfs(r, c, r2, c2)
                    + bfs(r2, c2, r1, c1)
                    + 2;

            // 카드 제거
            board[r1][c1] = 0;
            board[r2][c2] = 0;
            exists[card] = false;

            // 두 번째 카드 위치에서 다음 카드 제거
            int firstResult = first + dfs(r2, c2);

            // 첫 번째 카드 위치에서 다음 카드 제거
            int secondResult = second + dfs(r1, c1);

            answer = Math.min(
                    answer,
                    Math.min(firstResult, secondResult)
            );

            // 원상 복구
            board[r1][c1] = card;
            board[r2][c2] = card;
            exists[card] = true;
        }

        // 남은 카드가 없음
        if (!cardRemain) {
            return 0;
        }

        return answer;
    }

    static int bfs(
            int startR,
            int startC,
            int targetR,
            int targetC
    ) {

        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[4][4];

        queue.offer(new int[]{
                startR,
                startC,
                0
        });

        visited[startR][startC] = true;

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int r = current[0];
            int c = current[1];
            int count = current[2];

            if (r == targetR && c == targetC) {
                return count;
            }

            for (int direction = 0; direction < 4; direction++) {

                // 일반 방향키 이동
                int nr = r + dr[direction];
                int nc = c + dc[direction];

                if (isInRange(nr, nc) && !visited[nr][nc]) {
                    visited[nr][nc] = true;

                    queue.offer(new int[]{
                            nr,
                            nc,
                            count + 1
                    });
                }

                // Ctrl + 방향키 이동
                int[] ctrl = ctrlMove(r, c, direction);

                nr = ctrl[0];
                nc = ctrl[1];

                if (!visited[nr][nc]) {
                    visited[nr][nc] = true;

                    queue.offer(new int[]{
                            nr,
                            nc,
                            count + 1
                    });
                }
            }
        }

        return 0;
    }

    static int[] ctrlMove(int r, int c, int direction) {

        while (true) {

            int nr = r + dr[direction];
            int nc = c + dc[direction];

            // 보드 밖이면 현재 칸에서 멈춤
            if (!isInRange(nr, nc)) {
                return new int[]{r, c};
            }

            r = nr;
            c = nc;

            // 카드를 만나면 멈춤
            if (board[r][c] != 0) {
                return new int[]{r, c};
            }
        }
    }

    static boolean isInRange(int r, int c) {
        return r >= 0 && r < 4
                && c >= 0 && c < 4;
    }
}