package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_9372_Sanggeun {

    static int Test;
    static int Node;
    static int Edge;
    static int[] result;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Test=Integer.parseInt(st.nextToken());
        result = new int[Test];

        for(int j=0; j<Test; j++) {
            st = new StringTokenizer(br.readLine());
            Node = Integer.parseInt(st.nextToken());
            Edge = Integer.parseInt(st.nextToken());

            for (int i = 0; i < Edge; i++) {
                st = new StringTokenizer(br.readLine());
            }

            result[j]=Node-1;
        }
        
        for(int j=0; j<Test; j++) {
            System.out.println(result[j]);
        }

    }
}
