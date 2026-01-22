package Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_2108_통계학 {

    static int N;
    static List<Integer> arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new ArrayList<>();
        for(int i=0; i<N; i++) {
            int a = Integer.parseInt(br.readLine());
            arr.add(a);
        }
        average();
        midrange();
        max();
        difference();

        System.out.print(sb);
    }

    public static void average() {
        long sum = 0;
        for (int x : arr) sum += x;

        int answer = (int) Math.round((double) sum / arr.size());
        sb.append(answer).append("\n");
    }

    public static void midrange(){
        int answer = 0;
        int size = arr.size();
        Collections.sort(arr);
        answer = arr.get(size/2);
        sb.append(answer).append("\n");
    }

    public static void max() {
        int best = 0;
        List<Integer> modes = new ArrayList<>();
        int cur = 1;
        for (int i = 1; i <= N; i++) {
            if (i == N || !arr.get(i).equals(arr.get(i - 1))) {

                int value = arr.get(i - 1);

                if (cur > best) {
                    best = cur;
                    modes.clear();
                    modes.add(value);
                } else if (cur == best) {
                    modes.add(value);
                }

                cur = 1;
            } else {
                cur++;
            }
        }
        int answer = (modes.size() == 1) ? modes.get(0) : modes.get(1);
        sb.append(answer).append("\n");
    }


    public static void difference(){
        int size = arr.size();
        int answer = arr.get(size-1) - arr.get(0);
        sb.append(answer).append("\n");
    }
}
