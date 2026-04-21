import java.util.*;

class Solution {
    public int solution(String message, int[][] spoiler_ranges) {
        int answer = 0;

        HashSet<String> nonSpo = new HashSet<>();   // 스포가 아닌 구간에 등장한 단어
        HashSet<String> opened = new HashSet<>();   // 스포 단어

        StringBuilder sb = new StringBuilder(message);
        boolean[] covered = new boolean[message.length()];

        for (int[] cur : spoiler_ranges) {
            for (int i = cur[0]; i <= cur[1]; i++) {
                covered[i] = true;
            }
        }

        String[] words = message.split(" ");
        int idx = 0;

        // boolean을 통해 스포 아닌 애들부터 처리
        for (String word : words) {
            int start = idx;
            int end = idx + word.length() - 1;

            boolean isSpoWord = false;
            for (int j = start; j <= end; j++) {
                if (covered[j]) {
                    isSpoWord = true;
                    break;
                }
            }

            if (!isSpoWord) {
                nonSpo.add(word);
            }

            idx += word.length() + 1;
        }

        // 돌면서 스포 처리
        for (int[] cur : spoiler_ranges) {
            int start = cur[0];
            int end = cur[1];

            int sIdx = start;
            while (sIdx > 0 && sb.charAt(sIdx - 1) != ' ') {
                sIdx--;
            }

            int eIdx = end;
            while (eIdx < message.length() - 1 && sb.charAt(eIdx + 1) != ' ') {
                eIdx++;
            }

            String[] spoiler = message.substring(sIdx, eIdx + 1).split(" ");

            for (String word : spoiler) {
                if (!nonSpo.contains(word) && !opened.contains(word)) {
                    answer++;
                }
                opened.add(word);
            }
        }

        return answer;
    }
}