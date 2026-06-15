import java.util.*;

class Solution {

    static int dist_limit;
    static int split_limit; 
    static long answer = Integer.MIN_VALUE;
    
    
    public long solution(int dist_limit, int split_limit) {
        
        this.dist_limit = dist_limit;
        this.split_limit = split_limit;
        
        dfs(1, 0, 1, 1);
        return answer; 
    }     
    
    // 이번 뎁스의 분배 노드 후보의 수 : hubo
    // 현재까지의 분배 노드의 수 : dist
    // 현재까지의 분배도 : split
    // 현재까지의 리프노드의 수 : leaf
    public void dfs(long hubo, long dist, long split, long leaf){
        
        if(dist > dist_limit) return;
        if(split > split_limit) return;
        
        answer = Math.max(answer, leaf);
        
        for(int i = 2; i<=3; i++){
            
            long thisTime = Math.min(hubo, dist_limit - dist);
            
            long nextHubo = thisTime * i;
            long nextDist = dist + thisTime; 
            long nextSplit = split * i;
            long nextLeaf = leaf - thisTime + thisTime * i;
            
            // 까기
            dfs(nextHubo, nextDist, nextSplit, nextLeaf);
        }
    }
}