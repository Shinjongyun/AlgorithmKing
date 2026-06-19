class Solution {
    
    static int n; 
    static int[] info;
    static int maxDiff = 0;
    static int[] answer;

    public int[] solution(int n, int[] info) {
        
        this.n = n;
        this.info = info;
        
        answer = null;
        
        int[] ryan = new int[info.length];
        back(0, ryan, n);
        
        if (answer == null) {
            return new int[]{-1};
        }
        return answer;
    }

    public static void back(int depth, int[] ryan, int remain) {

        if (depth == 10) {
            ryan[10] = remain;

            int result = cal(ryan);

            if (result > 0) {
                if (result > maxDiff) {
                    maxDiff = result;
                    answer = ryan.clone();
                } else if (result == maxDiff && isBetter(ryan, answer)) {
                    answer = ryan.clone();
                }
            }

            return;
        }
        
        for(int i=0; i<=remain; i++){
            
            ryan[depth] = i;
            back(depth+1, ryan, remain - i);
            
        }   
    }
    
    public static int cal(int[] ryan){
        
        int ryanTotal = 0;
        int apeachTotal = 0;
        
        for(int i=0; i<=10; i++){
            if (ryan[i] > info[i]) {
                ryanTotal += 10 - i;
            } else if (info[i] > 0) {
                apeachTotal += 10 - i;
            }
        }
        
        return ryanTotal - apeachTotal; 
    }
    
     public static boolean isBetter(int[] ryan, int[] answer) {
        if (answer == null) return true;

        for (int i = 10; i >= 0; i--) {
            if (ryan[i] > answer[i]) {
                return true;
            } else if (ryan[i] < answer[i]) {
                return false;
            }
        }

        return false;
    }
}