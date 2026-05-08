import java.util.*;

class Solution {

    static int answer = 0;
    static int n;
    static List<Integer>[] tree;
    static int[] info;

    public int solution(int[] info, int[][] edges) {
        this.info = info;
        n = info.length;

        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            tree[e[0]].add(e[1]);
        }

        List<Integer> can = new ArrayList<>();

        // 0번 노드의 자식들을 후보에 넣고 시작
        for (int child : tree[0]) {
            can.add(child);
        }

        dfs(1, 0, can);

        return answer;
    }

    public static void dfs(int sheep, int wolf, List<Integer> can) {
        answer = Math.max(answer, sheep);

        for (int i = 0; i < can.size(); i++) {
            int next = can.get(i);

            int nextSheep = sheep;
            int nextWolf = wolf;

            if (info[next] == 1) {
                nextWolf++;
            } else {
                nextSheep++;
            }

            if (nextWolf >= nextSheep) {
                continue;
            }

            // 현재 후보 리스트 복사
            List<Integer> nextCan = new ArrayList<>(can);

            // 방문할 노드는 후보에서 제거
            nextCan.remove(i);

            // 방문한 노드의 자식들을 후보에 추가
            for (int child : tree[next]) {
                nextCan.add(child);
            }

            dfs(nextSheep, nextWolf, nextCan);
        }
    }
}