public class KAKAO20_1 {
	public static void main(String[] args) {
		System.out.println(solution("aabbaccc"));
	}

	static public int solution(String s) {
		int size = s.length();
		int answer = size;
		for (int i = 1; i <= size / 2; i++) {
			int nowSize = size;
			String strPart = s.substring(0, i);
			int dupCnt = 1;
			for (int j = i; j <= size - i; j += i) {
				String nowPart = s.substring(j, j + i);
				if (nowPart.equals(strPart)) {
					nowSize -= i;
					dupCnt++;
				}
				if (!nowPart.equals(strPart) || j + 2 * i > size) {
					strPart = nowPart;
					if (dupCnt >= 1000) nowSize += 4;
					else if (dupCnt >= 100) nowSize += 3;
					else if (dupCnt >= 10) nowSize += 2;
					else if (dupCnt >= 2) nowSize += 1;
					dupCnt = 1;
				}
			}
			answer = Math.min(answer, nowSize);
		}
		return answer;
	}
}
