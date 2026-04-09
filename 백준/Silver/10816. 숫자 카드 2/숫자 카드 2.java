

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] card;
    static int[] need;
    static int[] answer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        card = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            card[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(card);

        M = Integer.parseInt(br.readLine());
        answer = new int[M];
        need = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            need[i] = Integer.parseInt(st.nextToken());
            answer[i] = upperBound(need[i]) - lowerBound(need[i]);
        }

        for (int i = 0; i < M; i++) {
            sb.append(answer[i]).append(" ");
        }
        System.out.print(sb);
    }

    public static int lowerBound(int key) {
        int low = 0;
        int high = N;

        while (low < high) {
            int mid = (low + high) / 2;

            if (card[mid] >= key) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static int upperBound(int key) {
        int low = 0;
        int high = N;

        while (low < high) {
            int mid = (low + high) / 2;

            if (card[mid] > key) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}