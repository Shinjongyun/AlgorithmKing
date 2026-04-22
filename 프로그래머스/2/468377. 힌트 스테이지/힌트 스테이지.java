import java.util.*;

class Solution {
    
    static int answer = Integer.MAX_VALUE;
    static int n;
    static int[] cnt;
    
    public int solution(int[][] cost, int[][] hint) {
        
        n = cost.length;
        int totalCount = 1 << (n - 1);
        cnt = new int[n]; 
        
        // 2^(n-1)의 case 완전 탐색
        for (int mask = 0; mask < totalCount; mask++) {
            
            int[] cnt = new int[n];   // 각 case의 힌트권의 개수
            int sum = 0;    // 각 case의 비용의 총합

            // 
            for (int j = 0; j < n; j++) {
                // 현재 스테이지에서 가진 힌트권 최대 사용
                int use = Math.min(cnt[j], n - 1);
                sum += cost[j][use];

                // j번째 스테이지에서 번들을 사는 경우
                if ((mask & (1 << j)) != 0) {
                    sum += hint[j][0]; // 현재 스테이지 번들 가격

                    // 구매한 번들의 힌트권 수 동기화 
                    for (int k = 1; k < hint[j].length; k++) {
                        int ticketStage = hint[j][k] - 1;
                        cnt[ticketStage]++;
                    }
                }
            }

            answer = Math.min(answer, sum);
        }
        
        return answer;
    }
}