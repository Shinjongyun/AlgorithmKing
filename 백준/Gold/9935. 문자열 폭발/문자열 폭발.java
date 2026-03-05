

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String bomb = br.readLine();
        int L = bomb.length();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            sb.append(input.charAt(i));

            if (sb.length() >= L) {
                boolean ok = true;
                for (int j = 0; j < L; j++) {
                    if (sb.charAt(sb.length() - L + j) != bomb.charAt(j)) {
                        ok = false;
                        break;
                    }
                }
                if (ok) sb.delete(sb.length() - L, sb.length());
            }
        }

        if (sb.length() == 0) System.out.print("FRULA");
        else System.out.print(sb);
    }
}
