package Data_Structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class B_2493_Top {
    static int N;
    static Top[] top;
    static Stack<Top> stack;

    static class Top{
        int num;
        int index;
        public Top(int num, int index){
            this.num = num;
            this.index = index;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        top = new Top[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            top[i] = new Top(Integer.parseInt(st.nextToken()), i);
        }

        stack = new Stack<>();
        StringBuilder answer = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            while (!stack.empty() && stack.peek().num < top[i].num) {
                stack.pop();
            }

            if (stack.empty()) {
                answer.append(0).append(" ");
            } else {
                answer.append(stack.peek().index).append(" ");
            }

            stack.push(top[i]);
        }
        answer.setLength(answer.length() - 1);
        System.out.println(answer);
    }
}
