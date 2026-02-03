package Implement;

import java.io.*;
import java.util.*;

public class B_12764_싸지방 {

    static int comCount = 0;
    static PriorityQueue<User> users;   // 사용자 큐
    static PriorityQueue<int[]> usingComs;  // 사용중인 자리
    static PriorityQueue<Integer> emptyComs;    // 비어 있는 자리

    static class User {
        int start;
        int end;

        public User(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        users = new PriorityQueue<>(Comparator.comparing(user -> user.start)); // 사용자 시작 시각 빠른 순
        usingComs = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1]; // 종료 시각 빠른 순
            }
        });
        emptyComs = new PriorityQueue<>(); // 컴퓨터 번호 작은 순

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            users.add(new User(start, end));
        }

        int[] useCnt = new int[N+1];
        while(!users.isEmpty()) {
            User user = users.poll();
            // 지금 사용할 수 있는 컴퓨터 찾기
            while(!usingComs.isEmpty()) {
                int[] com = usingComs.peek();
                int comNum = com[0];
                int endTime = com[1];
                if(endTime <= user.start) {
                    emptyComs.add(comNum);
                    usingComs.poll();
                } else {
                    break;
                }
            }

            // 비어있는 컴퓨터가 없다면 컴퓨터 증설
            if(emptyComs.isEmpty()) {
                usingComs.add(new int[]{++comCount, user.end});
                useCnt[comCount]++;

            } else { // 빈 컴퓨터가 있으면 재사용
                int comNum = emptyComs.poll();
                useCnt[comNum]++;
                usingComs.add(new int[]{comNum, user.end});
            }
        }

        System.out.println(comCount);
        for(int i=1; i<=comCount; i++) {
            System.out.print(useCnt[i] + " ");
        }
    }
}
