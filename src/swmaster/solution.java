package swmaster;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class Solution {
    static HashSet<Integer> made = new HashSet<>();
    static boolean[] used;
    static List<Integer>[] list;

    public int solution(String numbers) {
        int n =3;
        used = new boolean[numbers.length()];
        list = new ArrayList[n+1];
        for(int i =1; i<=n; i++){
            list[i] = new ArrayList<>();
        }


        // 길이 1 ~ N까지 모두 만들기
        for (int len = 1; len <= numbers.length(); len++) {
            dfs(numbers, "", len);
        }

        int count = 0;
        for (int x : made) {
            if (isPrime(x)) count++;
        }
        return count;
    }

    // targetLen 길이만큼 순열 만들기
    static void dfs(String numbers, String cur, int targetLen) {
        if (cur.length() == targetLen) {
            made.add(Integer.parseInt(cur)); // "011" -> 11
            return;
        }

        for (int i = 0; i < numbers.length(); i++) {
            if (used[i]) continue;
            used[i] = true;
            dfs(numbers, cur + numbers.charAt(i), targetLen);
            used[i] = false;
        }
    }

    static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
}