

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static PriorityQueue<int[]> game;   // [start, end]  start 오름차순
    static PriorityQueue<int[]> using;  // [end, seat]   end 오름차순
    static PriorityQueue<Integer> empty; // 비어있는 seat 번호 (작은 번호 우선)
    static int[] usedCount;             // seat별 사용 횟수

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        // 시작 시간 기준
        game = new PriorityQueue<>((a, b) -> {
            int r = Integer.compare(a[0], b[0]);
            if (r == 0) r = Integer.compare(a[1], b[1]);
            return r;
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            game.add(new int[]{a, b});
        }

        // 끝나는 시간 기준
        using = new PriorityQueue<>((a, b) -> {
            int r = Integer.compare(a[0], b[0]); // end
            if (r == 0) r = Integer.compare(a[1], b[1]); // seat
            return r;
        });

        empty = new PriorityQueue<>();
        usedCount = new int[N]; // 최대 N자리까지 가능

        int seatCount = 0;

        while (!game.isEmpty()) {
            int[] cur = game.poll();
            int start = cur[0];
            int end = cur[1];

            // 끝난 자리들 반환
            while (!using.isEmpty() && using.peek()[0] <= start) {
                int[] finished = using.poll();   // [end, seat]
                empty.add(finished[1]);
            }

            int seat;
            // 자리가 남아있는 경우
            if (!empty.isEmpty()) {
                seat = empty.poll(); // 가장 작은 자리 번호
            }
            // 자리가 다 꽉찬 경우
            else {
                seat = seatCount++;  // 새 자리 배정
            }

            usedCount[seat]++;

            // 자리 사용중 등록
            using.add(new int[]{end, seat});
        }

        StringBuilder sb = new StringBuilder();
        sb.append(seatCount).append("\n");
        for (int i = 0; i < seatCount; i++) {
            sb.append(usedCount[i]).append(" ");
        }
        System.out.print(sb);
    }
}