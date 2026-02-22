

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int q;
    static List<gorilla> gorillas;
    static long answer = 0;

    public static class gorilla{
        String name;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        public gorilla(String name){
            this.name = name;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        gorillas = new ArrayList<>();
        q = Integer.parseInt(st.nextToken());

        for(int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            if(order==1){
                String name = st.nextToken();
                int numInfo = Integer.parseInt(st.nextToken());
                int index = 0;
                boolean isNew = true;
                boolean already = false;
                for(int j = 0; j < gorillas.size(); j++){
                    if(name.equals(gorillas.get(j).name)){
                        isNew = false;
                        index = j;
                        break;
                    }
                }
                for(int j = 0; j < numInfo; j++){
                    if(isNew){
                        if(!already){
                            gorillas.add(new gorilla(name));
                            already = true;
                            int a = Integer.parseInt(st.nextToken());
                            gorillas.get(gorillas.size()-1).pq.add(a);
                        } else{
                            int a = Integer.parseInt(st.nextToken());
                            gorillas.get(gorillas.size()-1).pq.add(a);
                        }
                    }
                    else {
                        int a = Integer.parseInt(st.nextToken());
                        gorillas.get(index).pq.add(a);
                    }
                }
                
            } else{
                String name = st.nextToken();
                int num = Integer.parseInt(st.nextToken());
                q2(name, num);
            }
        }
        System.out.print(answer);
    }

    public static void q2(String name, int num){
        for(int j = 0; j < gorillas.size(); j++){
            if(name.equals(gorillas.get(j).name)){
                for(int k = 0; k < num; k++){
                    if(!gorillas.get(j).pq.isEmpty()){
                        answer += gorillas.get(j).pq.poll();
                    } else{
                        break;
                    }
                }
                break;
            }
        }
    }
}
