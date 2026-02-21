

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static PriorityQueue<int []> lesson;
    static PriorityQueue<int []> studying;
    static PriorityQueue<int []> room;
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());

        lesson = new PriorityQueue<>((a, b) -> {
            int result = Integer.compare(a[0], b[0]);
            if (result == 0) {
                return Integer.compare(a[1], b[1]);
            }
            return result;
        });
        for(int i =0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lesson.offer(new int[]{a,b});
        }

        studying = new PriorityQueue<>((a, b) -> Integer.compare(a[1],b[1]));

        while(!lesson.isEmpty()){
            int[] cur = lesson.poll();

            while(!studying.isEmpty()){
                if(studying.peek()[1] <= cur[0]){
                    studying.poll();
                } else {
                    break;
                }
            }

            // 강의실이 없다면
            if(answer < studying.size() + 1){
                studying.offer(cur);
                answer++;
            }
            // 남으면
            else {
                studying.offer(cur);
            }
        }

        System.out.print(answer);
    }
}
