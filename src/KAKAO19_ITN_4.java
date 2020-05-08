import java.util.ArrayList;
import java.util.HashMap;

public class KAKAO19_ITN_4 {
	public static void main(String[] args) {
		long[] arr = {1, 3, 4, 1, 3, 1};
		System.out.println(solution(10, arr));
	}

	static long[] solution(long k, long[] room_number) {
		long[] answer = new long[room_number.length];
		HashMap<Long, Long> hm = new HashMap<>();
		for (int i = 0; i < room_number.length; i++) {
			long num = room_number[i];
			if(!hm.containsKey(num)) {
				answer[i] = num;
				hm.put(num, num+1);
			} else {
				ArrayList<Long> list = new ArrayList<>();
				while(hm.containsKey(num)) {
					list.add(num);
					num = hm.get(num);
				}
				answer[i] = num;
				list.add(num);
				for(long l : list) {
					hm.put(l, num+1);
				}
			}
		}
		return answer;
	}
}

