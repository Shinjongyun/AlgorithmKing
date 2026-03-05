

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[][] lecture;
    static PriorityQueue<int []> studying;
    static long answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        lecture = new int[N][2];
        studying = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            lecture[i][0] = Integer.parseInt(st.nextToken());
            lecture[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(lecture, (a, b) -> Integer.compare(a[0], b[0]));

        int count = 0;
        for (int i = 0; i < N; i++) {

            int[] cur = lecture[i];
            int start = cur[0];
            int end = cur[1];

            while(!studying.isEmpty()) {
                if(start >= studying.peek()[1]) {
                    studying.poll();
                } else{
                    break;
                }
            }

            // 강의실이 다 꽉차있는 경우
            if(count < studying.size() + 1){
                studying.offer(cur);
                count++;
            }
            // 강의실이 남아 있는 경우
            else{
                studying.offer(cur);
            }
        }

        System.out.print(count);
    }
}
