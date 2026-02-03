package Implement;

import java.io.*;
import java.util.*;

public class B_12764_싸지방 {

    static int N;
    static PriorityQueue<int []> users;
    static PriorityQueue<int []> using;
    static PriorityQueue<Integer> waiting;
    static int computerCount = 0;
    static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        users = new PriorityQueue<>((int[] a, int[] b) -> Integer.compare(a[0], b[0]));

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            users.add(new int [] {a, b});
        }

        using = new PriorityQueue<>((int[] a, int[] b) -> Integer.compare(a[1], b[1]));
        waiting = new PriorityQueue<>();

        count = new int[N+1];
        while(!users.isEmpty()) {

            int[] user = users.poll();
            int start = user[0];
            int end = user[1];

            while(!using.isEmpty()) {
                int curIndex = using.peek()[0];
                int curEnd = using.peek()[1];
                if(curEnd <= start){
                    waiting.add(curIndex);   // 반납하기
                    using.poll();
                } else break;
            }

            if(waiting.isEmpty()) {
                using.add(new int [] {++computerCount, end});
                count[computerCount]++;
            }
            else{
                int use = waiting.poll();
                using.add(new int [] {use, end});
                count[use]++;
            }

        }
        System.out.println(computerCount);
        for (int i = 1; i <= computerCount; i++) {
            System.out.print(count[i] + " ");
        }
    }
}
