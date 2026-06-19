import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        
        // 현재 주차장에 들어와 있는 차량의 입차 시간을 저장하는 Map
        // key   : 차량 번호
        // value : 입차 시간(분 단위)
        Map<String, Integer> inTime = new HashMap<>();
        
        // 차량별 누적 주차 시간을 저장하는 Map
        // TreeMap을 쓰는 이유:
        // 차량 번호가 작은 순서대로 결과를 반환해야 하기 때문
        Map<String, Integer> totalTime = new TreeMap<>();
        
        // 모든 입출차 기록을 하나씩 확인
        for (String record : records) {
            
            // record 예시: "05:34 5961 IN"
            // 공백 기준으로 나누면
            // parts[0] = "05:34"
            // parts[1] = "5961"
            // parts[2] = "IN"
            String[] parts = record.split(" ");
            
            // 시간을 분 단위로 변환
            int time = toMinute(parts[0]);
            
            // 차량 번호
            String carNumber = parts[1];
            
            // 입차인지 출차인지
            String type = parts[2];
            
            // 입차인 경우
            if (type.equals("IN")) {
                
                // 해당 차량의 입차 시간을 저장
                inTime.put(carNumber, time);
                
            } else {
                // 출차인 경우
                
                // 해당 차량이 언제 들어왔는지 꺼냄
                int start = inTime.get(carNumber);
                
                // 출차 시간 - 입차 시간 = 이번 주차 시간
                int parkingTime = time - start;
                
                // 기존 누적 시간에 이번 주차 시간을 더함
                // 처음 나온 차량이면 기본값 0 사용
                totalTime.put(
                    carNumber,
                    totalTime.getOrDefault(carNumber, 0) + parkingTime
                );
                
                // 출차했으므로 현재 주차 중인 차량 목록에서 제거
                inTime.remove(carNumber);
            }
        }
        
        // 하루의 마지막 시간인 23:59를 분 단위로 변환
        int endTime = toMinute("23:59");
        
        // 아직 출차하지 않은 차량 처리
        // 문제 조건상 출차 기록이 없으면 23:59에 출차한 것으로 간주
        for (String carNumber : inTime.keySet()) {
            
            // 입차 시간
            int start = inTime.get(carNumber);
            
            // 23:59 - 입차 시간 = 마지막 주차 시간
            int parkingTime = endTime - start;
            
            // 누적 주차 시간에 더함
            totalTime.put(
                carNumber,
                totalTime.getOrDefault(carNumber, 0) + parkingTime
            );
        }
        
        // 차량 수만큼 정답 배열 생성
        int[] answer = new int[totalTime.size()];
        
        // 정답 배열에 값을 넣기 위한 인덱스
        int index = 0;
        
        // TreeMap이므로 차량 번호가 작은 순서대로 반복됨
        for (String carNumber : totalTime.keySet()) {
            
            // 해당 차량의 총 주차 시간
            int time = totalTime.get(carNumber);
            
            // 총 주차 시간을 기준으로 요금 계산 후 정답 배열에 저장
            answer[index++] = calculateFee(time, fees);
        }
        
        return answer;
    }
    
    // "HH:MM" 형식의 시간을 분 단위로 바꾸는 함수
    private int toMinute(String time) {
        
        // 예: "05:34" -> ["05", "34"]
        String[] arr = time.split(":");
        
        // 시간 부분
        int hour = Integer.parseInt(arr[0]);
        
        // 분 부분
        int minute = Integer.parseInt(arr[1]);
        
        // 전체 분으로 변환
        // 예: 05:34 -> 5 * 60 + 34 = 334
        return hour * 60 + minute;
    }
    
    // 누적 주차 시간을 이용해서 주차 요금을 계산하는 함수
    private int calculateFee(int time, int[] fees) {
        
        // 기본 시간
        int basicTime = fees[0];
        
        // 기본 요금
        int basicFee = fees[1];
        
        // 단위 시간
        int unitTime = fees[2];
        
        // 단위 요금
        int unitFee = fees[3];
        
        // 누적 주차 시간이 기본 시간 이하라면 기본 요금만 부과
        if (time <= basicTime) {
            return basicFee;
        }
        
        // 기본 시간을 초과한 시간
        int extraTime = time - basicTime;
        
        // 초과 시간을 단위 시간으로 나눈 횟수
        // 나누어떨어지지 않으면 올림해야 함
        int unitCount = (int) Math.ceil((double) extraTime / unitTime);
        
        // 최종 요금 = 기본 요금 + 단위 횟수 * 단위 요금
        return basicFee + unitCount * unitFee;
    }
}