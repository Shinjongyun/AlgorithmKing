import java.util.*;

class Solution {
    
    static int[] answer;
    
    public int[] solution(long[] numbers) {
        answer = new int[numbers.length];
        
        for(int i = 0; i < numbers.length; i++){
            boolean flag = cal(numbers[i]);

            if(flag) answer[i] = 1;
            else answer[i] = 0;
        }

        return answer;
    }
    
    public static boolean cal(long num){
        String m = Long.toBinaryString(num);

        int size = 1;

        while (size < m.length()) {
            size = size * 2 + 1;
        }

        StringBuilder mask = new StringBuilder();

        while (mask.length() + m.length() < size) {
            mask.append("0");
        }

        mask.append(m);

        return check(mask.toString(), 0, mask.length() - 1);
    }

    public static boolean check(String tree, int low, int high) {
        if (low > high) return true;

        int mid = (low + high) / 2;

        if (tree.charAt(mid) == '0') {
            if (hasOne(tree, low, mid - 1)) return false;
            if (hasOne(tree, mid + 1, high)) return false;
        }

        return check(tree, low, mid - 1) && check(tree, mid + 1, high);
    }

    public static boolean hasOne(String tree, int low, int high) {
        for (int i = low; i <= high; i++) {
            if (tree.charAt(i) == '1') {
                return true;
            }
        }
        return false;
    }
}