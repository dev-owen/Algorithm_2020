import java.util.HashMap;
import java.util.HashSet;

public class KAKAO20_ITN_3 {
	public static void main(String[] args) {
		String[] arr = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
		System.out.println(solution(arr)[0]);
		System.out.println(solution(arr)[1]);
	}

	static int[] solution(String[] gems) {
		int[] answer = new int[2];
		int len = Integer.MAX_VALUE;
		HashMap<String, Integer> hm = new HashMap<>();
		HashSet<String> hs = new HashSet<>();
		for (int i = 0; i < gems.length; i++) {
			hm.put(gems[i], 0);
			hs.add(gems[i]);
		}
		for (int i = 0; i < gems.length; i++) {
			if (hs.contains(gems[i])) {
				hm.put(gems[i], i + 1);
				hs.remove(gems[i]);
			}
			hm.put(gems[i], i + 1);
			if (hs.isEmpty()) {
				// Map을 돌면서 최소 최대 찾아서 구간 길이가 최소면 정답 업데이트
				int min = Integer.MAX_VALUE;
				int max = 0;
				for (String key : hm.keySet()) {
					min = Math.min(min, hm.get(key));
					max = Math.max(max, hm.get(key));
				}
				if (max - min < len) {
					len = max - min;
					answer[0] = min;
					answer[1] = max;
				}
			}
		}
		return answer;
	}
}
