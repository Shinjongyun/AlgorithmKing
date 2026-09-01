import java.util.*;
import java.io.*;

public class Solution {

	static int n;
	static int[][] arr;

	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        
        arr = new int[n][2];
        for(int i=0; i<n; i++) {
        	st = new StringTokenizer(br.readLine());
        	arr[i][0] = Integer.parseInt(st.nextToken());
        	arr[i][1] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> empty = new PriorityQueue<>((a, b) -> (a - b));
        // 0은 끝나는 시간, 1은 방 번호
        PriorityQueue<int []> com = new PriorityQueue<>((a, b) -> {
        	if(a[0] - b[0] == 0) {
        		return a[1] - b[1];
        	}
        	return a[0] - b[0];
        });
        
        int time = 0;
        int idx = 0;
        int num = 0;
        int[] answer = new int [n];
        
        while(idx < n) {
        	
        	int[] cur = arr[idx];
    
        	while(!com.isEmpty() && com.peek()[0] <= cur[0]) {
        		int[] out = com.poll();
        		empty.add(out[1]);
        	}
        	
        	if(empty.isEmpty()) {
        		num++;
    			com.add(new int[] {cur[1], num});
    			answer[num-1]++;
        	}
        	else {
        		int index = empty.poll();
    			com.add(new int[] {cur[1], index});
    			answer[index - 1]++;

        	}
        		
        	idx++;
    
        	if(idx < n) time = arr[idx][0];
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(num).append("\n");
        for(int i : answer) {
        	if(i == 0) break;
        	sb.append(i).append(" ");
        }
        System.out.print(sb.toString());
	}
}
