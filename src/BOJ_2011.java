import java.util.*;

public class BOJ_2011 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();

		int[] dp = new int[5001];
		dp[0] = 1;
		dp[1] = 1;
		if (input.charAt(0) == '0') { // 0으로 시작하는 경우
			System.out.println(0);
			return;
		}
		for (int i = 1; i < input.length(); i++) {
			char pri = input.charAt(i - 1); // 이전 숫자
			if (input.charAt(i) >= '1' && input.charAt(i) <= '9') { // 혼자올수있음
				dp[i + 1] += dp[i];
				dp[i + 1] %= 1000000;
			}
			if (!(pri == '0' || pri > '2' || (pri == '2' && input.charAt(i) > '6'))) {
				// 두글자가 될 수 있다면
				dp[i + 1] += dp[i - 1];
				dp[i + 1] %= 1000000;
			}
			dp[i + 1] %= 1000000;
		}
		System.out.println(dp[input.length()] % 1000000);
	}
}
