import java.util.*;

public class BOJ_1256 {
	static long[][] dp = new long[201][201]; // dp[i][j]는 iCj
	static String str = "";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(), M = sc.nextInt(), K = sc.nextInt();
		dp[1][0] = 1;
		dp[1][1] = 1;
		for (int i = 2; i <= 200; i++) {
			dp[i][0] = 1;
			for (int j = 1; j <= i; j++) {
				dp[i][j] = Math.min(1000000000, dp[i - 1][j - 1] + dp[i - 1][j]);
			}
		}
		if (dp[N + M][M] < K) {
			System.out.println(-1);
			return;
		}
		find(N, M, K, M + N);
		System.out.println(str);
	}

	static void find(int n, int m, long cnt, int len) {
		if (len <= 0) return;
		if (n == 0) {
			str += "z";
			find(n, m - 1, cnt - dp[n - 1 + m][m], len - 1);
		} else if (m == 0) {
			str += "a";
			find(n - 1, m, cnt, len - 1);
		} else if (dp[n - 1 + m][m] >= cnt) {
			str += "a";
			find(n - 1, m, cnt, len - 1);
		} else {
			str += "z";
			find(n, m - 1, cnt - dp[n - 1 + m][m], len - 1);
		}
	}
}
