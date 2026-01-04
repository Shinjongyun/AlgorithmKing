package Data_Structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class B_2504_괄호 {

    static Stack<Character> st = new Stack<>();
    static int answer;

    static class a {
        int index;
        char a;
        boolean flag;
        public a(int index, char a) {
            this.index = index;
            this.a = a;
            flag = false;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str = new StringTokenizer(br.readLine());

        String input = str.nextToken();
        char[] arr = input.toCharArray();

        int i = 0;
        int temp = 1;
        while (i < arr.length) {
            char c = arr[i];
            if (c == '(') {
                st.push(c);
                temp *= 2;
            } else if (c == '[') {
                st.push(c);
                temp *= 3;
            } else if (c == ')') {
                if (st.isEmpty() || st.peek() != '(') {
                    answer = 0;
                    break;
                }
                if (arr[i-1] == '(') answer += temp; // 바로 닫힌 경우
                st.pop();
                temp /= 2;
            } else if (c == ']') {
                if (st.isEmpty() || st.peek() != '[') {
                    answer = 0;
                    break;
                }
                if (arr[i-1] == '[') answer += temp; // 바로 닫힌 경우
                st.pop();
                temp /= 3;
            }
            i++;
        }
        System.out.print(st.isEmpty() ? answer : 0);
    }
}
