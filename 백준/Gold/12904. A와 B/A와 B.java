

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static String from;
    static String to;
    static int answer;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        from  = br.readLine();
        to = br.readLine();

        StringBuilder sb = new StringBuilder();
        sb.append(to);

        for(int i=to.length()-1; i>=0; i--) {

            if(sb.length() == from.length()) break;

            if(sb.charAt(sb.length()-1) == 'A') {
                sb.delete(sb.length()-1, sb.length());
            } else if (sb.charAt(sb.length()-1) == 'B') {
                sb.delete(sb.length()-1, sb.length());
                sb.reverse();
            }
        }
        String finish = sb.toString();
        if(finish.equals(from)){
            answer = 1;
        } else {
            answer = 0;
        }
        System.out.print(answer);
    }
}
