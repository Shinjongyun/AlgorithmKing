package Set;

import java.util.*;
import java.io.*;

public class B_듣보잡 {

    static Set<String> set = new HashSet<>();
    static int N;
    static int M;
    static StringBuilder sb = new StringBuilder();
    static int count;
    static List<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            String name = br.readLine();
            set.add(name);
        }

        for (int i = 0; i < M; i++) {
            String name = br.readLine();
            if (set.contains(name)) {
                list.add(name);
                count++;
            }
        }

        Collections.sort(list);

        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append("\n");
        }

        System.out.println(count);
        System.out.print(sb);
    }
}
