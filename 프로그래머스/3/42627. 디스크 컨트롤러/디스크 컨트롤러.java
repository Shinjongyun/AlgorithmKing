import java.util.*; 

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        PriorityQueue<int []> q = new PriorityQueue<>((a, b) -> {
            if(a[1] - b[1] == 0){
                if(a[0] - b[0] == 0) return a[2] - b[2];
                return a[0] - b[0]; 
            }
            return a[1] - b[1];
        }); 
        
        Arrays.sort(jobs, ((a, b) -> a[0] - b[0]));
        int time = 0;
        int idx = 0;
        int count = 0; 
        while(count != jobs.length){
            for(int i=idx; i<jobs.length; i++){
                if(jobs[i][0] <= time) q.add(new int[] {jobs[i][0], jobs[i][1], idx++});
                else break; 
            }
            
            if(!q.isEmpty()){
                int[] cur = q.poll(); 
                time += cur[1];
                count++;
                answer += time - cur[0];
            } else time++; 
        }
        
        return answer / jobs.length;
    }
}