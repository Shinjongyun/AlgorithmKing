package Set;

import java.util.*;
import java.io.*;

public class B_회사에있는사람 {

    static int N;
    static Set<String> set = new HashSet<>();
    static List<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String type = st.nextToken();

            if(type.equals("leave") && set.contains(name)){
                set.remove(name);
            }
            else if(type.equals("enter")){
                set.add(name);
            }
        }

        // set -> list
        for (String name : set) {
            list.add(name);
        }

        Collections.sort(list, Collections.reverseOrder());

        StringBuilder sb = new StringBuilder();
        for (String name : list) {
            sb.append(name).append("\n");
        }

        System.out.print(sb);
    }
}
