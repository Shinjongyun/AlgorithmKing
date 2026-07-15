import java.util.*;

class Solution {

    static int[][] board;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    static Set<Integer> set = new HashSet<>();

    public int solution(int[][] board, int r, int c) {

        this.board = board;
        
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                if(board[i][j] != 0){
                    if(!set.contains(board[i][j])) set.add(board[i][j]);
                }
            }
        }
        
        int answer = back(r, c);
        return answer;
    }
    
    public static int back(int r, int c) {

        // 모든 카드를 제거한 경우
        if (set.isEmpty()) {
            return 0;
        }

        int min = Integer.MAX_VALUE;

        // 반복 중 set을 삭제하므로 복사본으로 반복
        List<Integer> cardList = new ArrayList<>(set);

        for (int s : cardList) {

            int[][] pos = findPos(s);

            int r1 = pos[0][0];
            int c1 = pos[0][1];

            int r2 = pos[1][0];
            int c2 = pos[1][1];

            int firstMove =
                    bfs(r, c, r1, c1)
                    + bfs(r1, c1, r2, c2)
                    + 2;

            int secondMove =
                    bfs(r, c, r2, c2)
                    + bfs(r2, c2, r1, c1)
                    + 2;

            // 현재 카드 한 쌍 제거
            board[r1][c1] = 0;
            board[r2][c2] = 0;
            set.remove(s);

            int first =
                    firstMove
                    + back(r2, c2);

            int second =
                    secondMove
                    + back(r1, c1);

            min = Math.min(
                    min,
                    Math.min(first, second)
            );

            // 제거했던 카드 복구
            board[r1][c1] = s;
            board[r2][c2] = s;
            set.add(s);
        }

        return min;
    }
    
    public static int bfs(int r, int c, int x, int y){
        
        boolean[][] visited = new boolean [board.length][board.length];
        Queue<int []> q = new LinkedList<>(); 
        q.add(new int[] {r, c, 0});
        visited[r][c] = true; 
        
        while(!q.isEmpty()){
            
            int[] cur = q.poll(); 
            
            for(int i=0; i<4; i++){
                
                int nx = cur[0] + dir[i][0];
                int ny = cur[1] + dir[i][1]; 
                int count = cur[2];
                
                if (cur[0] == x && cur[1] == y) {
                    return count;
                }
                
                if(nx>=0 && ny>=0 && nx<4 && ny<4 && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    q.add(new int [] {nx, ny, count + 1});
                }
                
                int[] ctrl = ctrlMove(cur[0], cur[1], i);

                int nr = ctrl[0];
                int nc = ctrl[1];

                if (!visited[nr][nc]) {
                    visited[nr][nc] = true;

                    q.offer(new int[]{ nr, nc, count + 1});
                }
            }
        }  
        return -1; 
    }
    
    static int[] ctrlMove(int r, int c, int direction) {

        while (true) {

            int nr = r + dir[direction][0];
            int nc = c + dir[direction][1];

            // 보드 밖이면 현재 칸에서 멈춤
            if (nr<0 || nc <0 || nr>=4 || nc >=4) {
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
    
    public static int[][] findPos(int num){
        
        int[][] pos = new int [2][2];
        int idx = 0;
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                if(board[i][j] == num){
                    pos[idx][0] = i;
                    pos[idx][1] = j;
                    idx++; 
                }
            }
        }
        return pos;
    }
}