class Solution {

    static int[] answer = new int[11];
    static int n;
    static int[] info;
    static int INF = Integer.MAX_VALUE;
    static boolean found = false;
    static int maxDiff = 0;
    
    public int[] solution(int n, int[] info) {
        
        this.n = n;
        this.info = info;
        
        for(int i=0; i<11; i++){
            answer[i] = INF;
        }
        
        int remain = n;
        int[] arr = new int [11];
        back(0, remain, arr);
        
        boolean flag = false;
        for(int i=0; i<11; i++){
            if(answer[i] == INF) flag = true;
        }
        
        if(flag) return new int[] {-1};
        else return answer;
    }
    
    public static void back(int depth, int remain, int[] arr){
        
        if (depth == 11) {

            // 화살을 전부 사용한 경우만 검사
            if (remain == 0) {

                int diff = cal(arr);

                // 점수 차가 더 크거나
                // 점수 차가 같고 낮은 점수를 더 많이 맞힌 경우
                if (diff > maxDiff ||
                    (diff == maxDiff && diff > 0 && check(arr))) {

                    maxDiff = diff;
                    answer = arr.clone();
                    found = true;
                }
            }

            return;
        }
        
        for(int i=0; i<=remain; i++){
            
            if(remain - i < 0) continue;
            arr[depth] = i;
            back(depth + 1, remain - i, arr);
        }
    }
    
    public static int cal (int[] arr){
        int brown = 0;
        int apeach  = 0;
        
        for(int i=0; i<11; i++){
            
            // 둘 다 0
            if(arr[i] == 0 && info[i] == 0) continue;
            // 브라운이 이긴 경우
            else if(arr[i] > info[i]){
                brown += 10 - i;
            }
            else apeach += 10 - i;
            
        }
        
        return brown - apeach;
    }
    
    public static boolean check (int[] arr){
        
        if(!found) return true;
        
        for(int i=10; i>=0; i--){
            
            if(answer[i] != arr[i]){
                if(answer[i] < arr[i]) return true;
                else return false;
            }
        }
        return false; 
    }
}