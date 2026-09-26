class Solution {
    public int solution(int[] queue1, int[] queue2) {

        int n = queue1.length;

        long first = 0;
        long second = 0;

        for (int num : queue1) first += num;
        for (int num : queue2) second += num;

        long total = first + second;

        // 전체 합이 홀수면 절대 동일하게 못 나눔
        if (total % 2 != 0) return -1;

        long target = total / 2;

        int[] arr = new int[n * 2];

        for (int i = 0; i < n; i++) {
            arr[i] = queue1[i];
            arr[i + n] = queue2[i];
        }

        int left = 0;
        int right = n;

        int count = 0;

        while (count <= n * 3) {

            if (first == target) {
                return count;
            }

            if (first > target) {
                first -= arr[left % (2 * n)];
                left++;
            } else {
                first += arr[right % (2 * n)];
                right++;
            }

            count++;
        }

        return -1;
    }
}