

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static char[] arr;
    static Stack<Character> stack = new Stack<>();
    static int count = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        arr = new char[line.length()];
        for(int i = 0; i < line.length(); i++){
            arr[i] = line.charAt(i);
        }

        int index = 0;
        int size = 0;
        while(true){
            if(index == line.length()){
                break;
            }
            if(index+1 < line.length() && arr[index]=='(' && arr[index+1]==')'){
                count += size;
                index++;
                index++;
            }
            else if(arr[index]=='('){
                stack.push(arr[index]);
                count++;
                size++;
                index++;
            }
            else if(arr[index]==')'){
                stack.pop();
                size--;
                index++;
            }
        }

        System.out.print(count);
    }
}
