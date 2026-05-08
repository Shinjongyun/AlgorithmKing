import java.util.*;

class Solution {
    
    static long answer = 1;
    static int dist_limit, split_limit;

    public long solution(int dist_limit, int split_limit) {
        this.dist_limit = dist_limit;
        this.split_limit = split_limit;
        dfs(1, 1, 1, 0);
        return answer;
    }
    
    // cur 현재 level의 분배노드 수
    // split 현재까지의 분배도
    // 리프노드 수
    // 현재까지 사용한 분배 노드의 수
    public static void dfs(int cur, long split, int leaf, int used){
        
        if(split > split_limit) return;
        
        int remain = dist_limit - used;
        
        if(dist_limit < used) return;

        answer = Math.max(answer, leaf);
        
        for(int node=2; node<=3; node++){
            
            // 이번 턴에 분배 할 노드 수
            int thisTime = Math.min(cur, remain);
            
            int nextUsed = used + thisTime;
            
            long nextSplit = split * node;
            
            int nextLeaf = leaf - thisTime + thisTime * node;
            
            dfs(thisTime * node, nextSplit, nextLeaf, nextUsed);
        }
    }
}