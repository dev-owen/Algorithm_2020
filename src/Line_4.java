import java.util.*;

public class Line_4 {
	public static void main(String[] args) {
		String[][] snapshots = {{"ACCOUNT1", "100"},{"ACCOUNT2", "150"}};
		String[][] transactions = {{"1", "SAVE", "ACCOUNT2", "100"},{"2", "WITHDRAW", "ACCOUNT1", "50"},{"1", "SAVE", "ACCOUNT2", "100"},{"4", "SAVE", "ACCOUNT3", "500"},{"3", "WITHDRAW", "ACCOUNT2", "30"}};
		System.out.println(solution(snapshots, transactions));

	}

	public static String[][] solution(String[][] snapshots, String[][] transactions) {
		String[][] answer;
		Map<String, Integer> account = new HashMap<>();
		ArrayList<String> accountName = new ArrayList<>();
		boolean[] ids = new boolean[100001];

		for(int i = 0; i < snapshots.length; i++) {
			account.put(snapshots[i][0], Integer.parseInt(snapshots[i][1]));
			accountName.add(snapshots[i][0]);
		}

		for(int i = 0; i < transactions.length; i++) {
			int id = Integer.parseInt(transactions[i][0]);
			if(!ids[id]) {
				ids[id] = true;
				String name = transactions[i][2];
				int money = Integer.parseInt(transactions[i][3]);
				if(account.containsKey(name)) {
					if(transactions[i][1] == "SAVE") {
						account.replace(name, account.get(name) + money);
					} else {
						account.replace(name, account.get(name) - money);
					}
				} else {
					account.put(name, money);
					accountName.add(name);
				}
			}
		}
		Collections.sort(accountName);
		answer = new String[accountName.size()][2];
		for(int i = 0; i < accountName.size(); i++) {
			answer[i][0] = accountName.get(i);
			answer[i][1] = String.valueOf(account.get(answer[i][0]));
		}
		return answer;
	}
}
