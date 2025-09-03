package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_2839_Sugar {

    static int N;
    static int result;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        while(N>0){
            if(N%5==0){
                result += N/5;
                break;
            }
            if(N<3){
                result = -1;
                break;
            }
            N -= 3;
            result++;
        }

        System.out.println(result);
    }
}
