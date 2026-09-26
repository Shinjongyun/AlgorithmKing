import java.util.*;

class Solution {

    public int[] solution(String[] gems) {
        int[] answer = new int[2];

        Set<String> set = new HashSet<>();
        for(int i = 0; i < gems.length; i++){
            set.add(gems[i]);
        }

        int min = Integer.MAX_VALUE;

        int right = 1;
        int left = 0;

        Map<String, Integer> bag = new HashMap<>();
        bag.put(gems[0], 1);

        while(true){

            // 모든 종류가 들어있으면
            if(bag.size() == set.size()){

                int dist = right - left;

                if(min > dist){
                    min = dist;
                    answer[0] = left + 1;
                    answer[1] = right;
                }

                // 왼쪽 줄이기
                String past = gems[left++];

                if(bag.get(past) == 1)
                    bag.remove(past);
                else
                    bag.put(past, bag.get(past) - 1);
            }

            // 종류가 부족하면 오른쪽 늘리기
            else {

                // ★ 여기서 종료
                if(right == gems.length)
                    break;

                String next = gems[right++];

                if(!bag.containsKey(next))
                    bag.put(next, 1);
                else
                    bag.put(next, bag.get(next) + 1);
            }
        }

        return answer;
    }
}