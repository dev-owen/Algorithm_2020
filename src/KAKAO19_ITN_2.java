import java.util.*;

public class KAKAO19_ITN_2 {
	public static void main(String[] args) {
		System.out.println(solution("{{2},{2,1},{2,1,3},{2,1,3,4}}"));
		System.out.println(solution("{{4,2,3},{3},{2,3,4,1},{2,3}}"));
	}

	static public int[] solution(String s) {
		PriorityQueue<String> pq = new PriorityQueue<String>(500, new StringLengthComparator());
		String[] strArr = s.substring(2, s.length()-2).split("\\},\\{");
		for(String s_ : strArr) {
			pq.add(s_);
		}
		HashSet<Integer> hs = new HashSet<>();
		int N = pq.size();
		int[] answer = new int[N];
		for(int i = 0; i < N; i++) {
			String str = pq.poll();
			String[] tmpArr = str.split(",");
			for(String _s : tmpArr) {
				int num = Integer.parseInt(_s);
				if(hs.contains(num)) continue;
				else {
					hs.add(num);
					answer[i] = num;
				}
			}
		}
		return answer;
	}
}

class StringLengthComparator implements Comparator<String> {
	@Override
	public int compare(String x, String y) {
		if (x.length() < y.length()) return -1;
		if (x.length() > y.length()) return 1;
		return 0;
	}
}