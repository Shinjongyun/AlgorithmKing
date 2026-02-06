

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int A;
    static int B;
    static int answer = 1;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        int b = B;
        while(b!=A){
            if(b < A){
                answer = -1;
                break;
            }
            if(b%2==0){
                b = b/2;
                answer++;
            } else if ((b-1)%10==0){
                b = b - 1;
                b = b /10;
                answer++;
            } else {
                answer = -1;
                break;
            }
        }
        System.out.print(answer);
    }
}
