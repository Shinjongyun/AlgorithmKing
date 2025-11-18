package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B_11438_LCA2 {

    static int N;
    static int M;
    static List<Integer>[] tree;
    static int[] answer;
    static int[][] parent;
    static boolean[] visited;
    static int[] depth;
    static int height = 17;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N= Integer.parseInt(st.nextToken());
        tree= new List[N+1];
        for(int i=1;i<=N;i++){
            tree[i]= new ArrayList<>();
        }
        visited =new boolean[N+1];
        parent = new int [N+1][height];
        depth= new int [N+1];

        for(int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }
        setTree(1,0);
        parentInit();

        M= Integer.parseInt(br.readLine());
        answer = new int [M];
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            answer[i]= lca(a, b);
        }
        for(int i=0;i<M;i++){
            System.out.println(answer[i]);
        }
    }

    static void setTree(int cur, int d){
        visited[cur]=true;
        depth[cur]=d;
        for(int next : tree[cur]){
            if(!visited[next]){
                parent[next][0]=cur;
                setTree(next,d+1);
            }
        }
    }

    static void parentInit(){
        for(int n=1;n<height;n++){
            for(int i=1;i<=N;i++){
                parent[i][n]=parent[parent[i][n-1]][n-1];
            }
        }
    }

    static int lca(int a, int b){
        if(depth[a]<depth[b]){
            int temp = a;
            a = b;
            b = temp;
        }

        for(int i = height-1; i>=0; i--){
            if(Math.pow(2, i) <= depth[a] - depth[b]){
                a=parent[a][i];
            }
        }

        if (a == b) return a;

        for (int i = height - 1; i >= 0; i--) {
            if (parent[a][i] != parent[b][i]) {
                a = parent[a][i];
                b = parent[b][i];
            }
        }
        return parent[a][0];
    }
}
