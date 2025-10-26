package Dynamic_Programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_1912_Continuous_Sum {

    static int N;
    static int result;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for(int i =0; i < N; i++){
            arr[i] = Integer.parseInt(st1.nextToken());
        }

        for(int i=0; i < N; i++){
            if(i+1 < N) result = Math.max(arr[i], arr[i+1]);
        }

        result=kadane();

        System.out.println(result);
    }

    static int kadane(){
        // 배열의 첫 번째 요소로 초기화
        int maxSoFar = arr[0];
        int maxEndingHere = arr[0];

        // 배열의 두 번째 요소부터 탐색
        for (int i = 1; i < arr.length; i++) {
            // 현재 요소를 포함하여 최대 합을 구함
            maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i]);
            // 현재까지의 최대 합을 갱신
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }
}
