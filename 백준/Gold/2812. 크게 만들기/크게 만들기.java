

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int K;
    static String target;
    static Stack<Character> answer = new Stack<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int originalK = Integer.parseInt(st.nextToken());
        K = originalK;   // 지울 수 있는 숫자의 횟수

        target = br.readLine();

        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);

            // peek한 것보다 숫자가 더 큼 (예: peek은 2, 숫자는 9)
            // 더 큰 숫자가 오면 앞의 작은 숫자를 K가 허용하는 만큼 제거
            while (!answer.isEmpty() && answer.peek() < c && K > 0) {
                answer.pop();
                K--;
            }

            // peek한것보다 숫자가 더 작으면 push (예: peek은 4, 숫자는 2)
            answer.push(c);
        }

        // K가 남아있으면 뒤에서 더 제거 (내림차순 같은 케이스)
        while (K > 0) {
            answer.pop();
            K--;
        }

        // 결과는 정확히 N - originalK 자리만 출력
        int resultLen = N - originalK;
        StringBuilder sb = new StringBuilder(resultLen);
        for (int i = 0; i < resultLen; i++) {
            sb.append(answer.get(i));
        }

        System.out.print(sb);
    }
}
