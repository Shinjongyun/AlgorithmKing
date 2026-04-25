import java.util.*;

class Solution {
    static long answer = 0;
    static List<Long> numbers = new ArrayList<>();
    static List<Character> operators = new ArrayList<>();
    static List<Character> opKinds = new ArrayList<>();
    static boolean[] visited;
    static char[] order;

    public long solution(String expression) {
        parse(expression);

        visited = new boolean[opKinds.size()];
        order = new char[opKinds.size()];

        dfs(0);

        return answer;
    }

    // 1. 수식 파싱
    static void parse(String expression) {
        numbers.clear();
        operators.clear();
        opKinds.clear();

        StringBuilder sb = new StringBuilder();
        Set<Character> set = new HashSet<>();

        for (char ch : expression.toCharArray()) {
            if (ch == '+' || ch == '-' || ch == '*') {
                numbers.add(Long.parseLong(sb.toString()));
                sb.setLength(0);

                operators.add(ch);
                set.add(ch);
            } else {
                sb.append(ch);
            }
        }
        numbers.add(Long.parseLong(sb.toString()));

        for (char op : set) {
            opKinds.add(op);
        }
    }

    // 2. 연산자 우선순위 순열 생성
    static void dfs(int depth) {
        if (depth == opKinds.size()) {
            long result = calculate();
            answer = Math.max(answer, Math.abs(result));
            return;
        }

        for (int i = 0; i < opKinds.size(); i++) {
            if (visited[i]) continue;
            visited[i] = true;
            order[depth] = opKinds.get(i);
            dfs(depth + 1);
            visited[i] = false;
        }
    }

    // 3. 현재 우선순위대로 계산
    static long calculate() {
        List<Long> nums = new ArrayList<>(numbers);
        List<Character> ops = new ArrayList<>(operators);

        for (char op : order) {
            for (int i = 0; i < ops.size(); ) {
                if (ops.get(i) == op) {
                    long a = nums.get(i);
                    long b = nums.get(i + 1);
                    long result = operate(a, b, op);

                    nums.remove(i + 1);
                    nums.remove(i);
                    nums.add(i, result);

                    ops.remove(i);
                } else {
                    i++;
                }
            }
        }

        return nums.get(0);
    }

    static long operate(long a, long b, char op) {
        if (op == '+') return a + b;
        if (op == '-') return a - b;
        return a * b;
    }
}