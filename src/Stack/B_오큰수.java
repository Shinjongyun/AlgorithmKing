package Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class B_오큰수 {

    static int N;
    static int[] answer;
    static Stack<nge> stack = new Stack<>();

    static class nge{
        int num;
        int index;
        public nge(int num, int index){
            this.num = num;
            this.index = index;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        answer = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {

            int number = Integer.parseInt(st.nextToken());

            // 더 큰 경우
            while(!stack.isEmpty() && stack.peek().num < number){
                nge cur = stack.pop();
                answer[cur.index] = number;
            }

            // 스택에 아무것도 없는 경우 or 나보다 큰 것만 남아있는 경우
            stack.push(new nge(number, i));

            // 마지막 순서인 경우
            if(i==N-1){
                while(!stack.isEmpty()){
                    nge cur = stack.pop();
                    answer[cur.index] = -1;
                }
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            sb.append(answer[i]).append(" ");
        }

        System.out.print(sb);
    }
}
