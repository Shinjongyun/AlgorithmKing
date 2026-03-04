
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static List<Integer> minus = new ArrayList<>();
    static List<Integer> plus = new ArrayList<>();
    static int zeroCount = 0;
    static int total = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(br.readLine());
            if (a == 0) {
                zeroCount++;
            } else if (a <0){
                minus.add(a);
            } else{
                plus.add(a);
            }
        }

        Collections.sort(minus);                      // 오름차순: -5, -3, -1
        plus.sort(Collections.reverseOrder());        // 내림차순: 5, 4, 3, 2, 1...

        int total = 0;

        // 음수 처리: 앞에서 2개씩 곱
        int i =0;
        for (; i + 1 < minus.size(); i += 2) {
            total += minus.get(i) * minus.get(i + 1);
        }
        // 음수 1개 남으면 0 있으면 버리고, 없으면 더함
        if (i < minus.size()) {
            if (zeroCount == 0) total += minus.get(i);
        }

        // 양수 처리: 큰 수부터 2개씩
        int j = 0;
        for (; j + 1 < plus.size(); j += 2) {
            int a = plus.get(j);
            int b = plus.get(j + 1);
            if (b == 1) {          
                total += a + b;
            } else {
                total += a * b;
            }
        }
        // 양수 1개 남으면 더함
        if (j < plus.size()) total += plus.get(j);

        System.out.print(total);
    }
}
