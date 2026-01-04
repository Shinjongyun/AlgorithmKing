package Data_Structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_10845_Queue {

    static Queue<Integer> q = new LinkedList<Integer>();
    static int N;
    static StringBuilder sb = new StringBuilder();
    static int last;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            magic(st);
        }
        System.out.print(sb);
    }

    public static void magic(StringTokenizer st){
        String order = st.nextToken();
        if(order.equals("push")){
            String input = st.nextToken();
            q.offer(Integer.parseInt(input));
            last=Integer.parseInt(input);
        }
        if(order.equals("pop")){
            if(q.isEmpty()) sb.append(-1).append("\n");
            else sb.append(q.poll()).append("\n");
        }
        if(order.equals("size")){
            sb.append(q.size()).append("\n");
        }
        if(order.equals("empty")){
            if(q.isEmpty()) sb.append(1).append("\n");
            else sb.append(0).append("\n");
        }
        if(order.equals("front")){
            if(q.isEmpty()) sb.append(-1).append("\n");
            else sb.append(q.peek()).append("\n");
        }
        if(order.equals("back")){
            if(q.isEmpty()) sb.append(-1).append("\n");
            else sb.append(last).append("\n");
        }
    }
}
