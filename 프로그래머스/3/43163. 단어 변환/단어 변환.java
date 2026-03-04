import java.io.*;
import java.util.*;

class Solution {
    
    static int answer = Integer.MAX_VALUE;
    static boolean[] visited;
    static int N;
    static String[] word;
    static String answerWord;
    
    public int solution(String begin, String target, String[] words) {
        
        N = words.length;
        word = words;
        answerWord = target;
        visited = new boolean[N];
        
        dfsFromBegin(begin, 0);
        return (answer == Integer.MAX_VALUE) ? 0 : answer;
    }
    
    static void dfsFromBegin(String cur, int depth) {
        if (cur.equals(answerWord)) {
            answer = Math.min(answer, depth);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i] && canPlace(cur, word[i])) {
                visited[i] = true;
                dfsFromBegin(word[i], depth + 1);
                visited[i] = false;
            }
        }
    }

    
    static boolean canPlace(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) diff++;
            if (diff > 1) return false;
        }
        return diff == 1;
    }
}