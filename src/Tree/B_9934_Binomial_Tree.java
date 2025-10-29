package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B_9934_Binomial_Tree {

    static int K;
    static List<Integer>[] rank;
    static int[] array;
    static int N;
    static boolean[] visited;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K=Integer.parseInt(st.nextToken());
        N= (int) Math.pow(2,K)-1;
        rank = new ArrayList[K+1];
        for(int i=1;i<=K;i++){
            rank[i] = new ArrayList<>();
        }

        array = new int[N+1];
        visited = new boolean[N+1];
        st=new StringTokenizer(br.readLine());

        for(int i=1;i<=N;i++){
            array[i]= Integer.parseInt(st.nextToken());
        }

        for(int i=1;i<=K;i++){
            find(i);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= K; i++) {
            for (int num : rank[i]) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static void find(int step){
        int interval= (int) Math.pow(2,step);
        int start = 1;
        for(int s=1;s<=N;s++){
            if(!visited[s]){
                break;
            }
            start++;
        }
        for(int i=start;i<=N;i+=interval){
            visited[i]= true;
            rank[K-step+1].add(array[i]);
        }
    }
}
