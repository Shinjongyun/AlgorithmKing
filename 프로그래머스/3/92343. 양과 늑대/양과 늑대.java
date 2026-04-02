import java.util.*;

class Solution {
    
    static List<Integer>[] graph;
    static int N;
    static boolean[] visited;
    static int answer = 0;
    static int[] info;
    
    public int solution(int[] info, int[][] edges) {
        
        N = info.length;
        this.info = info;
        visited = new boolean[N];
        
        graph = new List[N];
        for(int i=0; i<N; i++){
            graph[i] = new ArrayList<>();
        }
    
        int j = 0;
        for(int i=0; i<edges.length; i++){
            graph[edges[i][0]].add(edges[i][1]);
        }
                  
        
        List<Integer> nextNodes = new ArrayList<>();
        nextNodes.add(0);
        dfs(0, 0, nextNodes);
        return answer;
    }
    
    public static void dfs(int sCount, int wCount, List<Integer> next){
        
        for (int i = 0; i < next.size(); i++) {
            int cur = next.get(i);

            int nextSheep = sCount;
            int nextWolf = wCount;

            if (info[cur] == 0) {
                nextSheep++;
            } else {
                nextWolf++;
            }

            // 늑대 수가 양 수 이상이면 더 진행 불가
            if (nextWolf >= nextSheep) {
                continue;
            }

            answer = Math.max(answer, nextSheep);

            // 후보 리스트 복사
            List<Integer> newNextNodes = new ArrayList<>(next);

            // 현재 선택한 노드 제거
            newNextNodes.remove(i);

            // 현재 노드의 자식들을 후보에 추가
            for (int child : graph[cur]) {
                newNextNodes.add(child);
            }

            dfs(nextSheep, nextWolf, newNextNodes);
        }
    }
}