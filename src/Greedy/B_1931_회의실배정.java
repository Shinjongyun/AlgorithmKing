package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_1931_회의실배정 {

    static int N;
    static PriorityQueue<int []> meeting;
    static int[] using;
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        meeting = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });
        for(int i=0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            meeting.offer(new int[]{a, b});
        }

        using = new int[2];
        while(!meeting.isEmpty()) {

            int[] cur = meeting.poll();
            int start = cur[0];
            int end = cur[1];

            if(using[1] <= start){
                  using[0] = start;
                  using[1] = end;
                  answer++;
            }
        }
        System.out.print(answer);
    }
}
