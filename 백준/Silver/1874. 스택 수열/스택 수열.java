

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static Stack<Integer> stack = new Stack<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int numIndex = 0;
        int num = 1;
        while(true){
            if (!stack.isEmpty() && stack.peek()==arr[numIndex]) {
                stack.pop();
                sb.append("-"+"\n");
                numIndex++;
            }
            else if(arr[numIndex]!=num) {
                if(num>N){
                    System.out.print("NO");
                    return;
                }
                stack.push(num);
                sb.append("+"+"\n");
                num++;
            } else if (arr[numIndex]==num) {
                sb.append("+"+"\n");
                sb.append("-"+"\n");
                numIndex++;
                num++;
            } else {
                System.out.print("NO");
                return;
            }
            if(numIndex==N){
                break;
            }
        }
        System.out.print(sb);
    }
}
