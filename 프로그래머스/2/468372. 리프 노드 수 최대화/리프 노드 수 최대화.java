import java.util.*;

class Solution {
    
    static int answer = 1;
    static int d, s;
    
    public int solution(int dist_limit, int split_limit) {
        answer = 1;
        d = dist_limit;
        s = split_limit;
        
        dfs(1, 1, 1, 0);
        return answer;
    }
    
    // cur   : 현재 깊이에서 분배 가능한 노드 수
    // used  : 지금까지 사용한 분배 노드 수
    // split : 현재 깊이까지의 분배도 곱
    // leaf  : 지금까지 확정된 리프 수
    public static void dfs(long cur, long used, long split, long leaf) {
        
        if (used > d) return;
        
        // 지금 멈추면 남은 cur는 전부 리프
        answer = (int)Math.max(answer, leaf + cur);
        
        for (int child = 2; child <= 3; child++) {
            
            long nextSplit = split * child;
            if (nextSplit > s) continue;
            
            // 이번 depth에서 새로 생기는 노드 수
            long nextNodes = cur * child;
            
            // 앞으로 더 분배에 쓸 수 있는 노드 수
            long remain = d - used;
            
            // 다음 depth로 넘길 수 있는 분배 노드 수
            long nextCur = Math.min(nextNodes, remain);
            
            // 넘기지 못한 노드는 리프로 확정
            long nextLeaf = leaf + (nextNodes - nextCur);
            
            dfs(nextCur, used + nextCur, nextSplit, nextLeaf);
        }
    }
}