

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int [] arr = new int[26];
    static int answer = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                char c = str.charAt(j);
                arr[c-'A'] += (int)Math.pow(10, str.length() - 1 - j); // 가중치 더하기
            }
        }

        Arrays.sort(arr); 
        
        int num = 9;
        int turn = 25;
        while(arr[turn] != 0) {
            answer += arr[turn]*num;
            turn--;
            num--;
        }

        System.out.print(answer);
    }
}
