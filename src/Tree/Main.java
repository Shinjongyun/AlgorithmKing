package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int V;
    static ArrayList<Node>[] graph;
    static int answer;
    static boolean[] visited;
    static int I=0;

    static class Node{
        int number;
        int distance;
        public Node(int number, int distance){
            this.number = number;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V= Integer.parseInt(st.nextToken());
        graph = new ArrayList[V+1];
        for(int i=1; i<=V; i++){
            graph[i] = new ArrayList<Node>();
        }

        for(int i=1; i<=V; i++){
            st = new StringTokenizer(br.readLine());
            int no  = Integer.parseInt(st.nextToken());
            while(true){
                int a = Integer.parseInt(st.nextToken());
                if(a==-1) break;
                int b = Integer.parseInt(st.nextToken());
                graph[no].add(new Node(a,b));
            }
        }

        visited = new boolean[V+1];
        answer = 0;
        I = 0;
        int index = find(1, 0); // 1. 임의의 정점에서 가장 먼 정점의 번호를 찾음

        visited = new boolean[V+1];
        answer = 0;
        I = 0;
        find(index, 0);  // 2. 그 다음 그 정점에서 가장 먼 거리를 찾음

        System.out.print(answer);
    }

    static int find(int i, int dis){  // 모든 정점에서 거리를 재니까 시간 초과 됨 따라서 오직 2번 사용
        visited[i] = true;
        answer=Math.max(dis, answer);
        if(answer==dis){
            I=i;
        }
        for(Node n : graph[i]){
            if(!visited[n.number]){
                find(n.number, dis+n.distance);
            }
        }
        return I;
    }
}
