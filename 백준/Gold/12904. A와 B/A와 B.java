

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static char[] to;
    static char[] from;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        from = line.toCharArray();
        line = br.readLine();
        to = line.toCharArray();
        int start = to.length -1;
        int end = from.length -1;

        for (int i = start; i > end; i--) {
            if(to[i] == 'A') addA();
            else if(to[i] == 'B') shuffle();
        }
        if(Arrays.equals(to, from)) System.out.print(1);
        else System.out.print(0);
    }

    public static void shuffle(){
        char[] tArray;
        tArray = to.clone();
        to = new char[tArray.length - 1];
        for(int i = 0; i < tArray.length -1; i++){
            to[i] = tArray[i];
        }
        for(int i = 0; i < to.length/2; i++){
            char temp = to[i];
            to[i] = to[to.length - i - 1];
            to[to.length - i - 1] = temp;
        }
    }

    public static void addA(){
        char[] tArray;
        tArray = to.clone();
        to = new char[tArray.length - 1];
        for(int i = 0; i < tArray.length -1; i++){
            to[i] = tArray[i];
        }
    }
}
