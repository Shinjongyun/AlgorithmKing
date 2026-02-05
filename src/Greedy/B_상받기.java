package Greedy;

import java.io.*;
import java.util.*;

public class B_상받기 {

    static int[] a;
    static Integer[] b;
    static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        a = new int[N];
        b = new Integer[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(a);
        Arrays.sort(b, Collections.reverseOrder());

        int total = 0;
        for (int i = 0; i < N; i++) {
            total += a[i]*b[i];
        }
        System.out.print(total);
    }
}
