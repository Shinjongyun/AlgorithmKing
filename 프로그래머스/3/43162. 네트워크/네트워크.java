import java.util.*;

class Solution {
    
    static int[] parent; 
    static List<Edge>[] graph; 
    
    class Edge{
        int to;
        Edge(int to){
            this.to = to; 
        }
    }
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        parent = new int[n+1];
        
        for(int i=1; i<=n; i++){
            parent[i] = i; 
        }
        
        graph = new List[n+1]; for(int i=1; i<=n; i++) graph[i] = new ArrayList<>(); 
        
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(i != j && computers[i][j] == 1){
                    graph[i+1].add(new Edge(j+1)); 
                }
            }
        }
        
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(i != j && computers[i][j] == 1){
                    union(i+1, j+1); 
                }
            }
        }
        
        Set<Integer> set = new HashSet<>();
        for(int i=1; i<=n; i++){
            int f = find(i); 
            if(!set.contains(f)){
                answer++;
                set.add(find(f));
            }  
        }
        
        return answer;
    }
    
    public static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]); 
    }
    
    public static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b); 
        
        if(rootA == rootB) return; 
        
        parent[rootA] = rootB;
    }
}