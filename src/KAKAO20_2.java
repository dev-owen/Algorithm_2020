import java.util.ArrayList;

public class KAKAO20_2 {
	public static void main(String[] args) {
		System.out.println(solution("()))((()"));
	}

	static String solution(String p) {
		if (p.equals("")) return "";
		else if (correctStr(p)) return p;
		int crit = 0, index = 0, size = p.length();

		char[] pArr = p.toCharArray();
		for (char c : pArr) {
			index++;
			if (c == '(') crit++;
			else crit--;
			if (crit == 0) break;
		}
		String u = p.substring(0, index);
		String v = p.substring(index, size);
		if (correctStr(u)) return u + solution(v);
		else {
			String answer = "(";
			answer += solution(v);
			answer += ")";
			if (u.length() != 0) {
				u = u.substring(1, u.length() - 1);
				char[] uArr = u.toCharArray();
				for (char c : uArr) {
					if (c == '(') answer += ')';
					else answer += '(';
				}
			}
			return answer;
		}
	}

	static boolean correctStr(String s) {
		if (s.equals("")) return true;
		char[] charArr = s.toCharArray();
		int crit = 0;
		for (char c : charArr) {
			if (c == '(') crit++;
			else crit--;
			if (crit < 0) return false;
		}
		if (crit == 0) return true;
		else return false;
	}
}
