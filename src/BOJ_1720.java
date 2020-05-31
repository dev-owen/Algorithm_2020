import java.util.Scanner;

public class BOJ_1720 {
	static int[] dp = new int[31]; // 중복 포함
	static int[] sym = new int[31]; // 중복 없음

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		dp[1] = 1;
		dp[2] = 3;
		for (int i = 3; i <= 30; i++) {
			dp[i] = dp[i - 1] + dp[i - 2] * 2;
		}
		sym[1] = 1;
		sym[2] = 3;
		for (int i = 3; i <= 30; i++) {
			if (i % 2 == 0) {
				sym[i] = (dp[i / 2] + dp[i / 2 - 1] * 2 + dp[i]) / 2;
			} else {
				sym[i] = (dp[(i - 1) / 2] + dp[i]) / 2;
			}
		}
		System.out.println(sym[n]);
	}
}
