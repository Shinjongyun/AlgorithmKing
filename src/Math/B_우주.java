package Math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_우주 {

    static int T;
    static int[][] arr;
    static int answer = 0;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        arr = new int[T][2];
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[i][0] = a;
            arr[i][1] = b;
        }

        for (int i = 0; i < T; i++) {
            answer = 0;
            find(arr[i][0], arr[i][1]);
            sb.append(answer).append("\n");
        }

        System.out.print(sb);
    }

    public static void find(int from, int to) {
        int distance = 1;
        from += distance;
        answer++;
        while(from!=to-1){
            int fdistance = distance +1;
            int bdistance = distance -1;
            if(from+ fdistance <=to-1){
                from += fdistance;
                distance++;
                answer++;
            } else if(from+distance <=to-1){
                from += distance;
                answer++;
            }
            else{
                from += bdistance;
                distance--;
                answer++;
            }
        }
        answer++;
    }
}
