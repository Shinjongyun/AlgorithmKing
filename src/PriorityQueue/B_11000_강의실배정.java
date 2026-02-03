package PriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_11000_강의실배정 {

    static int N;
    static PriorityQueue<int []> classTime;
    static PriorityQueue<Integer> waiting;
    static PriorityQueue<int []> studying;
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 여기에는 시작 시간과 끝 시간을 저장
        classTime = new PriorityQueue<>((int[] a, int[] b) -> Integer.compare(a[0], b[0]));
        for(int i =0; i< N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            classTime.add(new int [] {a,b});
        }

        // 이거는 강의실의 인덱스를 저장
        waiting = new PriorityQueue<>();
        // 이 큐는 강의실의 인덱스와 끝 시간을 저장
        studying = new PriorityQueue<>((int [] a, int[] b) -> Integer.compare(a[1], b[1]));

        // 자 수업 한번 넣어볼까?
        while(!classTime.isEmpty()) {

            // 시작시간이 가장 빠른 수업 poll
            int[] classTime = B_11000_강의실배정.classTime.poll();
            int start = classTime[0];
            int end = classTime[1];

            // 이미 끝난 수업들을 강의실에서 제거하는 루프
            while (!studying.isEmpty()) {
                int curIndex = studying.peek()[0];
                int curEnd = studying.peek()[1];
                if (curEnd <= start) {     // 기존 수업의 끝나는 시간이 현재 수업 시작 시간 보다 작으면 그 강의실은 이제 비었으니까 다시 쓸 수 있도록 회수
                    waiting.add(curIndex);
                    studying.poll();
                } else { // 아직 안 끝난 수업이 peek 당하면 뒤에 애들은 더 늦게 끝나니까 더 볼 필요 없음
                    break;
                }
            }

            // 만약에 대기 강의실이 부족한 경우
            if (waiting.isEmpty()) {
                studying.add(new int[]{++answer, end});    // 이거는 강의실의 수를 증가 시킨겨
            } else {  // 강의실이 남아 있어? 그러면 그냥 공부 시작!
                studying.add(new int[]{waiting.poll(), end});
            }
        }
        System.out.print(answer);
    }
}
