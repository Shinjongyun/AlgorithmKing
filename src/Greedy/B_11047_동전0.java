package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_11047_동전0 {


    static int[] coin;
    static int N;
    static int K;
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int remain = K;
        coin = new int[N];
        for(int i =0; i<N; i++){
            coin[i] = Integer.parseInt(br.readLine());
        }

        while(remain != 0){
            for(int i = N-1; i>=0; i--){
                if(remain / coin[i] != 0){
                    answer += remain/coin[i];
                    remain -= coin[i]*(remain / coin[i]);
                }
            }
        }
        System.out.print(answer);
    }
}
