class Solution {
    public String solution(String number, int k) {
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < number.length(); i++){
            
            char cur = number.charAt(i);
            
            while(k > 0 &&
                  sb.length() > 0 &&
                  sb.charAt(sb.length() - 1) < cur){
                
                sb.deleteCharAt(sb.length() - 1);
                k--;
            }
            
            sb.append(cur);
        }
        
        // 내림차순이라 삭제하지 못한 경우
        if(k > 0){
            sb.setLength(sb.length() - k);
        }
        
        return sb.toString();
    }
}