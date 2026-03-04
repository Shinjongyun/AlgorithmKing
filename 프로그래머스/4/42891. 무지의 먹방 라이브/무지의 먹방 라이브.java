import java.util.*;

class Solution {
    
    static int[] food_times;
    static long k;
    static int answer = 0;
    static PriorityQueue<int []> pq;
    
    public int solution(int[] food_times, long k) {

        this.food_times = food_times;
        this.k = k;
        long sum = 0;
        
        pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        for(int i =0; i<food_times.length; i++){
            pq.add(new int[] {i+1, food_times[i]});
            sum += food_times[i];
        }
        
        if (sum <= k) return -1;
        
        long prev = 0;                 // 지금 까지 각 음식별로 깎아 낸 시간
        long remain = food_times.length; // 남은 음식 개수 (size)
        
        while(!pq.isEmpty()){
            long curTime = pq.peek()[1];     // 현재 남아있는 음식 중에서 가장 짧은 시간
            long diff = curTime - prev;      // 그 음식 시간이 0이 되기 까지의 차이
            long cost = diff * remain;       // 0으로 만들기 위해 필요한 시간

            if (diff == 0) { // 다 먹은 음식은 제거
                pq.poll();
                remain--;
                continue;
            }

            if (k >= cost) {    // 최소 음식이 끝나는 순간까지 통째로 처리
                k -= cost;
                prev = curTime;

                // time == curTime 인 음식들 전부 제거
                while (!pq.isEmpty() && pq.peek()[1] == curTime) {
                    pq.poll();
                    remain--;
                }
            } else {
                // 남은 애들 idx 순으로 정렬 후 k%remain 번째
                List<int[]> left = new ArrayList<>();
                while (!pq.isEmpty()) left.add(pq.poll());

                left.sort(Comparator.comparingInt(a -> a[0])); // idx 오름차순
                int pos = (int) (k % remain);
                return left.get(pos)[0];
            }
        }

        return -1;
    }
}