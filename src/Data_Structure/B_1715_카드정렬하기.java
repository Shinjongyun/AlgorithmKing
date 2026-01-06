package Data_Structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class B_1715_카드정렬하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        if (N == 1) {
            System.out.print(0);
            return;
        }

        long answer = 0;
        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            int s = a + b;
            answer += s;
            pq.add(s);
        }

        System.out.print(answer);
    }
}