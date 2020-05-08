import java.util.*;

public class KAKAO19_ITN_3 {
	public static void main(String[] args) {
		String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] banned_id = {"*rodo", "*rodo", "******"};

		System.out.println(solution(user_id, banned_id));
	}

	static int ban_size, answer = 0;
	static ArrayList<LinkedList<String>> aryLst;
	static HashSet<String> hs;
	static HashSet<HashSet<String>> ans;
	static String[] banned_id_;

	static int solution(String[] user_id, String[] banned_id) {
		aryLst = new ArrayList<>();
		ban_size = banned_id.length;
		banned_id_ = banned_id;
		for (String s : banned_id) {
			LinkedList<String> list = new LinkedList<>();
			for (String user : user_id) {
				if (user.length() == s.length()) {
					boolean matched = true;
					for (int i = 0; i < user.length(); i++) {
						if (user.charAt(i) != s.charAt(i) && s.charAt(i) != '*') {
							matched = false;
							break;
						}
					}
					if (matched) list.add(user);
				}
			}
			aryLst.add(list);
		}
		hs = new HashSet<>();
		ans = new HashSet<>();
		dfs(0);
		return answer;
	}

	static void dfs(int idx) {
		if (idx == ban_size) {
			if(!ans.contains(hs)) {
				answer++;
				ans.add(hs);
			}
			return;
		}

		for (String s : aryLst.get(idx)) {
			if (hs.contains(s)) continue;
			else {
				hs.add(s);
				dfs(idx + 1);
				hs.remove(s);
			}
		}
		return;
	}
}
