package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_1541_잃어버린괄호 {

    static int total = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] subList = br.readLine().split("-");

        for(int i =0; i<subList.length;i++) {
            String[] s = subList[i].split("[+]");
            for(int j =0; j<s.length;j++) {
                if(i==0) total += Integer.parseInt(s[j]);
                else total -= Integer.parseInt(s[j]);
            }
        }
        System.out.print(total);
    }
}
