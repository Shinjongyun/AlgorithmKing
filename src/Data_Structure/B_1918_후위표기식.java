package Data_Structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class B_1918_후위표기식 {

    static char[] c;
    static Stack<Character> s = new Stack<>();
    static StringBuilder sb = new StringBuilder();

    static int pri(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String input = st.nextToken();
        c = input.toCharArray();

        int i = 0;

        while (i < c.length) {
            char ch = c[i];

            if (ch != '+' && ch != '-' && ch != '*' && ch != '/' && ch != '(' && ch != ')') {
                // 피연산자(알파벳)
                sb.append(ch);

            } else {
                // 연산자/괄호
                if (ch == '(') {
                    s.push(ch);

                } else if (ch == ')') {
                    // '(' 나올 때까지 pop
                    while (!s.isEmpty() && s.peek() != '(') {
                        sb.append(s.pop());
                    }
                    if (!s.isEmpty()) s.pop(); // '(' 제거

                } else {
                    // + - * / : 우선순위 높은/같은 것 먼저 pop, 즉 +가 들어 왔을 때 이미 있던 *를 먼저 pop 시키기
                    while (!s.isEmpty() && s.peek() != '(' && pri(s.peek()) >= pri(ch)) {
                        sb.append(s.pop());
                    }
                    s.push(ch);
                }
            }

            i++;
        }

        // 남은 연산자 처리
        while (!s.isEmpty()) {
            sb.append(s.pop());
        }

        System.out.print(sb);
    }
}
