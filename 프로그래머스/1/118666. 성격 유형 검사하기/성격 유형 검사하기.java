class Solution {
    
    static int[][] grid = new int [4][2];
    static String answer;
    static int[][] promise;
    
    public String solution(String[] survey, int[] choices) {
        
        promise = new int [survey.length][2];
        
        int idx1 = 0;
        for(String s : survey){
            StringBuilder sb = new StringBuilder(s);
            int gI = 0;
            int seq = 1;
            if(sb.charAt(0) == 'C' || sb.charAt(0) == 'F') gI = 1;
            if(sb.charAt(0) == 'J' || sb.charAt(0) == 'M') gI = 2;
            if(sb.charAt(0) == 'A' || sb.charAt(0) == 'N') gI = 3;
            
            if(sb.charAt(0) == 'N' || sb.charAt(0) == 'M' || sb.charAt(0) == 'F' || sb.charAt(0) == 'T'){
                seq = 0;
            }
            promise[idx1][0] = gI;
            promise[idx1][1] = seq;
            idx1++;
        }
        
        int idx = 0;
        for(int cur : choices){
            
            // 정배
            if(promise[idx][1] == 1){
                if(cur>=1 && cur <= 3) grid[promise[idx][0]][0] += (cur - 4) * (-1);
                if(cur>=5 && cur <= 7) grid[promise[idx][0]][1] += (cur - 4);
            } 
            // 역배
            else{
                if(cur>=1 && cur <= 3) grid[promise[idx][0]][1] += (cur - 4) * (-1);
                if(cur>=5 && cur <= 7) grid[promise[idx][0]][0] += (cur - 4);
            }
            idx++;
        }
        
        StringBuilder a = new StringBuilder();
        // 평가
        if(grid[0][0] >= grid[0][1]) a.append("R"); 
        else a.append("T");
        
        if(grid[1][0] >= grid[1][1]) a.append("C"); 
        else a.append("F");
        
        if(grid[2][0] >= grid[2][1]) a.append("J"); 
        else a.append("M");
        
        if(grid[3][0] >= grid[3][1]) a.append("A"); 
        else a.append("N");
        
        answer = a.toString();
        
        return answer;
    }
}