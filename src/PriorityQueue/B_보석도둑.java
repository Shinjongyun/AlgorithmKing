package PriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_보석도둑 {

    static int N;
    static int K;
    static PriorityQueue<int []> jewel;
    static PriorityQueue<Integer> bag;
    static long answer = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        jewel = new PriorityQueue<>((a, b) -> {
            int result = Integer.compare(a[0] ,b[0]);
            if (result == 0) result = Integer.compare(a[1] ,b[1]);
            return result;
        });
        for(int i =0; i< N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            jewel.add(new int[]{a,b});
        }

        bag = new PriorityQueue<>();
        for (int i = 0; i < K; i++) {
            int c = Integer.parseInt(br.readLine());
            bag.add(c);
        }

        // 넣을 수 있는 보석들 집합
        PriorityQueue<Integer> canPick = new PriorityQueue<>(Collections.reverseOrder());

        // 가방을 작은 것부터 처리
        while (!bag.isEmpty()) {
            int cap = bag.poll();

            // 현재 가방에 들어갈 수 있는 보석들을 전부 후보(canPick)에 추가
            while (!jewel.isEmpty() && jewel.peek()[0] <= cap) {
                canPick.add(jewel.poll()[1]); // 가치만 넣어 버렷
            }

            // 후보 중 가장 비싼 거 1개 선택
            if (!canPick.isEmpty()) {
                answer += canPick.poll();
            }
        }
        System.out.print(answer);
    }

}
