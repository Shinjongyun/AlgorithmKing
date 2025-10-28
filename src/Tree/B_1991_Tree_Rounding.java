package Tree;

import java.io.*;
import java.util.*;

public class B_1991_Tree_Rounding {

    static int N;
    static ArrayList<String>[] nodes;
    static boolean[] visited1;
    static boolean[] visited2;
    static boolean[] visited3;

    static StringBuilder sb1 = new StringBuilder();
    static StringBuilder sb2 = new StringBuilder();
    static StringBuilder sb3 = new StringBuilder();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        nodes = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }
        visited1 = new boolean[N+1];
        visited2 = new boolean[N+1];
        visited3 = new boolean[N+1];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            nodes[0].add(st.nextToken());
            nodes[i].add(st.nextToken());
            nodes[i].add(st.nextToken());
        }

        first(1);
        second(1);
        third(1);

        System.out.println(sb1.toString());
        System.out.println(sb2.toString());
        System.out.println(sb3.toString());

    }

    static void first(int i) {
        visited1[i] = true;
        sb1.append(nodes[0].get(i-1));
        if(!nodes[i].get(0).equals(".")) {
            int j = find(i, 0);
            first(j);
        }
        if(!nodes[i].get(1).equals(".")) {
            int j = find(i, 1);
            first(j);
        }
    }

    static void second(int i) {
        visited2[i] = true;
        if(!nodes[i].get(0).equals(".")) {
            int j = find(i, 0);
            second(j);
        }
        sb2.append(nodes[0].get(i-1));
        if(!nodes[i].get(1).equals(".")) {
            int j = find(i, 1);
            second(j);
        }
    }
    static void third(int i) {
        visited3[i] = true;
        if(!nodes[i].get(0).equals(".")) {
            int j = find(i, 0);
            third(j);
        }
        if(!nodes[i].get(1).equals(".")) {
            int j = find(i, 1);
            third(j);
        }
        sb3.append(nodes[0].get(i-1));
    }
    static int find(int i, int j) {
        for(int m=0; m<N; m++){
            if(nodes[0].get(m).equals(nodes[i].get(j))){
                return m+1;
            }
        }
        return -1;
    }
}
