import java.util.*;

class Solution {
    
    static long answer = 1;
    static int dist_limit, split_limit;

    public long solution(int dist_limit, int split_limit) {
        this.dist_limit = dist_limit ;
        this.split_limit = split_limit;
        dfs(1, 1, 1, 0);
        return answer;
    }
    
    // 현재의 분배 노드의 후보
    // 현재까지의 분배도
    // 현재까지의 리프 노드의 수
    // 현재까지 사용된 분배 노드의 수
    public static void dfs(int cur, long split, int leaf, int used){
        
        if (split > split_limit) return;
        
        answer = Math.max(answer, leaf);
        
        for (int child = 2; child <=3; child++){
            
            int remain = dist_limit - used;
            
            int nextNodes = Math.min(cur, remain);

            // gpt: 시간 초과
            if (nextNodes == 0) continue; 
            
            dfs(nextNodes * child, split*child, (leaf - nextNodes) + nextNodes * child, used + cur);
        }
    }
}