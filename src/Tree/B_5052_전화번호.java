package Tree;

import java.io.*;

public class B_5052_전화번호 {

    static Node root;

    static class Node {
        Node[] next = new Node[10]; // 0~9로 갈 수 있는 길
        boolean isEnd;              // 여기서 전화번호 끝났는지
    }

    static boolean insert(String phone) {
       // 원문을 root부터 내려갑니다.
        Node cur = root;

        // 자 phone을 하나씩 넣습니다.
        for (int i = 0; i < phone.length(); i++) {
            int d = phone.charAt(i) - '0';

            // 길이 없으면 새로 만듬
            if (cur.next[d] == null) {
                cur.next[d] = new Node();
            }

            cur = cur.next[d];

            // 기존 번호는 끝낫는데
            if (cur.isEnd) {
                return false; // phone이 1234이고, 원래 123이 있었음
            }
        }

        // 내가 끝났는데 이미 아래 번호가 있음
        for (int i = 0; i < 10; i++) {
            if (cur.next[i] != null) {
                return false; // phone이 123이고, 원래 1234가 있었음
            }
        }

        cur.isEnd = true; // 여기서 번호 끝
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            root = new Node();

            boolean ok = true;
            for (int i = 0; i < N; i++) {
                String phone = br.readLine();
                if (ok && !insert(phone)) ok = false;
            }

            sb.append(ok ? "YES" : "NO").append('\n');
        }

        System.out.print(sb);
    }
}



