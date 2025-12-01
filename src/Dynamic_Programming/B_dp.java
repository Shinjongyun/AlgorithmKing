package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_dp {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());

        int answer = dp(N);
        StringBuilder sb = new StringBuilder();
        sb.append(answer);
        System.out.print(sb);
    }

    static int dp(int n) {
        int answer=0;

        if((n-1)==1){
            return ++answer;
        }
        if(n==2){
            return ++answer;
        }
        if((n-1)%3==0){
            n--;
            answer++;
            while(n>=3){
                n/=3;
                answer++;
            }
            return answer;
        }
        if(n%3==0){
            while(n>=3){
                n/=3;
                answer++;
            }
            return answer;
        }
        if(n>10){
            while(n%3==0){
                n--;
                answer++;
            }
            while(n>=3){
                n/=3;
                answer++;
            }
            return answer;
        }
        return answer;
    }
}
