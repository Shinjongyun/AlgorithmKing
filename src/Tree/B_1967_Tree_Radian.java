package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B_1967_Tree_Radian {

    static int N;
    static ArrayList<Node>[] graph;
    static int answer;
    static boolean[] visited;

    static class Node{
        int distance;
        int num;
        public Node (int distance, int num){
            this.distance = distance;
            this.num = num;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());

        graph=new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            graph[i]=new ArrayList<>();
        }

        for(int i=0; i<N-1; i++){
            st=new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            Node node1 = new Node(distance, child);
            graph[parent].add(node1);
            Node node2 = new Node(distance, parent);
            graph[child].add(node2);
        }
        for(int i=1; i<=N; i++){
            int dis=0;
            visited=new boolean[N+1];
            find(i, dis);
        }
        System.out.println(answer);
    }

    static void find(int i, int dis) {
        visited[i] = true;
        for (int j = 0; j < graph[i].size(); j++) {
            Node node = graph[i].get(j);
            if (!visited[node.num]) {
                int next = dis + node.distance;
                answer = Math.max(answer, next);
                find(node.num, next);
            }
        }
    }
}
