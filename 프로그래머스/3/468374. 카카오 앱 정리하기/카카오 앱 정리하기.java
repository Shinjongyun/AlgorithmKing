import java.util.*;

class Solution {

    static int[][] board;
    static int n;
    static int m;

    // 1: 오른쪽, 2: 아래, 3: 왼쪽, 4: 위
    static int[][] dir = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };

    public int[][] solution(int[][] board, int[][] commands) {
        Solution.board = board;
        n = board.length;
        m = board[0].length;

        for (int i = 0; i < commands.length; i++) {
            process(commands[i][0], commands[i][1]);
        }

        return Solution.board;
    }

    static void process(int startId, int arrow) {
        Set<Integer> seed = new HashSet<>();
        seed.add(startId);

        while (true) {
            // seed 앱들이 움직일 때 같이 밀릴 전체 앱 찾기
            Set<Integer> group = findMoveApps(seed, arrow);

            // group 전체를 동시에 한 칸 이동
            moveAll(group, arrow);

            // 이동 후 잘린 앱 찾기
            Set<Integer> nextSeed = findCutApps(group, arrow);

            if (nextSeed.isEmpty()) {
                break;
            }

            seed = nextSeed;
        }
    }

    static Set<Integer> findMoveApps(Set<Integer> seed, int arrow) {
        Set<Integer> set = new HashSet<>();
        Queue<Integer> q = new ArrayDeque<>();

        for (int id : seed) {
            set.add(id);
            q.offer(id);
        }

        int dr = dir[arrow - 1][0];
        int dc = dir[arrow - 1][1];

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int r = 0; r < n; r++) {
                for (int c = 0; c < m; c++) {
                    if (board[r][c] != cur) continue;

                    int nr = (r + dr + n) % n;
                    int nc = (c + dc + m) % m;

                    int next = board[nr][nc];

                    if (next != 0 && !set.contains(next)) {
                        set.add(next);
                        q.offer(next);
                    }
                }
            }
        }

        return set;
    }

    static void moveAll(Set<Integer> group, int arrow) {
        int[][] next = new int[n][m];

        int dr = dir[arrow - 1][0];
        int dc = dir[arrow - 1][1];

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {

                int id = board[r][c];

                if (id == 0) continue;

                // 움직이는 앱이면 한 칸 이동해서 넣기
                if (group.contains(id)) {
                    int nr = (r + dr + n) % n;
                    int nc = (c + dc + m) % m;

                    next[nr][nc] = id;
                }

                // 안 움직이는 앱이면 그대로 넣기
                else {
                    next[r][c] = id;
                }
            }
        }

        board = next;
    }

    static Set<Integer> findCutApps(Set<Integer> movedGroup, int arrow) {
        Set<Integer> cut = new HashSet<>();

        // 오른쪽/왼쪽 이동이면 좌우로 잘렸는지만 확인
        if (arrow == 1 || arrow == 3) {
            for (int r = 0; r < n; r++) {
                int leftId = board[r][0];
                int rightId = board[r][m - 1];

                if (leftId == 0) continue;
                if (leftId != rightId) continue;
                if (!movedGroup.contains(leftId)) continue;

                boolean allSame = true;

                for (int c = 0; c < m; c++) {
                    if (board[r][c] != leftId) {
                        allSame = false;
                        break;
                    }
                }

                if (!allSame) {
                    cut.add(leftId);
                }
            }
        }

        // 위/아래 이동이면 상하로 잘렸는지만 확인
        else {
            for (int c = 0; c < m; c++) {
                int topId = board[0][c];
                int bottomId = board[n - 1][c];

                if (topId == 0) continue;
                if (topId != bottomId) continue;
                if (!movedGroup.contains(topId)) continue;

                boolean allSame = true;

                for (int r = 0; r < n; r++) {
                    if (board[r][c] != topId) {
                        allSame = false;
                        break;
                    }
                }

                if (!allSame) {
                    cut.add(topId);
                }
            }
        }

        return cut;
    }
}