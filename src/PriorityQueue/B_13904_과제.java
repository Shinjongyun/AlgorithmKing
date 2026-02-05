package PriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_13904_과제 {

    static int N;
    static PriorityQueue<int[]> pq;
    static int[] task;
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        task = new int[1001];
        pq = new PriorityQueue<>((int [] a, int [] b) -> Integer.compare(b[1], a[1]));

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.add(new int[]{d, w});
        }

        while(!pq.isEmpty()) {
            int[] t = pq.poll();
            // 넣어주기
            for(int i = t[0]; i >= 1; i--) {
                if(task[i] < t[1]) {
                    task[i] = t[1];
                    break;
                }
            }
        }

        for(int i = 1; i < 1001; i++) {
            answer += task[i];
        }
        System.out.print(answer);
    }
}
