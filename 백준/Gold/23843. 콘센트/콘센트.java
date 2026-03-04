

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static List<Integer> time;
    static PriorityQueue<Integer> using; // 누적 시간 큐
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        time = new ArrayList<>();
        for (int i = 0; i < N; i++) time.add(Integer.parseInt(st.nextToken()));

        time.sort(Collections.reverseOrder());
        using = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int cur = time.get(i);

            if(using.size() >= M){
                int min = using.poll();
                using.offer(cur+min);

            } else if (using.size() < M){
                    using.offer(cur);
            }
        }

        while (!using.isEmpty()){
            int cur = using.poll();
            answer = Math.max(cur, answer);
        }
        System.out.print(answer);
    }
}
