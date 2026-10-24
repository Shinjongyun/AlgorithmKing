import java.util.*;

class Solution {

    static Map<Long, Long> parent = new HashMap<>();

    public long[] solution(long k, long[] room_number) {

        parent.clear();

        long[] answer = new long[room_number.length];

        for (int i = 0; i < room_number.length; i++) {
            long room = find(room_number[i]);
            answer[i] = room;
            parent.put(room, room + 1);
        }

        return answer;
    }

    static long find(long room) {

        if (!parent.containsKey(room)) {
            return room;
        }

        long next = find(parent.get(room));

        parent.put(room, next);

        return next;
    }
}