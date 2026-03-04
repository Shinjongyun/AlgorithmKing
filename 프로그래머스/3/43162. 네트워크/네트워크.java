import java.util.*;
import java.io.*;

class Solution {
    
    static boolean[] visited;
    static int[][] graph;
    static int N;
    static boolean solved = false;
    static int count = -1;
    
    public int solution(int n, int[][] computers) {
        
        N = n;
        visited = new boolean[n];
        graph = computers;

        while(!solved){
            int index = done();
            dfs(index);
            count++;
        }
        
        int answer = count;
        return answer;
    }
    public static void dfs(int index){
        visited[index] = true;
        for(int i =0; i<graph[index].length; i++){
            if(graph[index][i]==1 && !visited[i]) dfs(i);  
        }
    }
    public static int done(){
        for(int i=0; i <N; i++){
            if(!visited[i]) return i;
        }
        solved = true;
        return 0;
    }
}