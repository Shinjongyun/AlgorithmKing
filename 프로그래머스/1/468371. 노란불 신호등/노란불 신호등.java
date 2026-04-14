class Solution {
    
    static int answer = -1;
    
    public int solution(int[][] signals) {
        int max = 1;
        
        for(int[] signal : signals){
            int total = signal[0] + signal[1] +signal[2];
            max *= total;
        }

        for(int sec=1; sec<=max; sec++){
            boolean [] correct = new boolean[signals.length];
            for(int i = 0; i<signals.length; i++){
                if(isYellow(signals[i], sec)) correct[i] = true;
            }   
            
            boolean flag = true;
            for(int i =0; i<signals.length; i++){
                if(!correct[i]) flag = false;
            }
            
            if(flag) {
                answer = sec;
                break;
            }
        }
        return answer; 
    }
        
    public static boolean isYellow(int [] signal, int sec){
        int g = signal[0];
        int y = signal[1];
        int r = signal[2];
        int now = sec % (g + y + r);

        if(now > g && now <= g + y) return true;
        return false;
    }
}