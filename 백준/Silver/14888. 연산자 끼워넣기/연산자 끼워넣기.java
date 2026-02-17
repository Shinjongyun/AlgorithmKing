
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    static int[] num;
    static int[] op; // [+, -, *, /]

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine().trim());

        num = new int[N];
        op = new int[4];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) num[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) op[i] = Integer.parseInt(st.nextToken());

        dfs(1, num[0]);

        System.out.println(max);
        System.out.println(min);
    }

    static void dfs(int idx, int current) {
        if (idx == N) {
            max = Math.max(max, current);
            min = Math.min(min, current);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (op[i] == 0) continue;

            op[i]--;
            int next = calc(current, num[idx], i);
            dfs(idx + 1, next);
            op[i]++;
        }
    }

    static int calc(int a, int b, int opIndex) {
        switch (opIndex) {
            case 0: return a + b;
            case 1: return a - b;
            case 2: return a * b;
            case 3: return a / b;
        }
        return 0;
    }
}
