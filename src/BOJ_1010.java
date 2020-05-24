import java.io.*;
import java.util.*;

public class BOJ_1010 {
	static long[][] dp = new long[31][31];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i = 1; i < 31; i++) {
			dp[1][i] = i;
		}
		for (int i = 2; i < 31; i++) {
			for (int j = i; j < 31; j++) {
				if (i == j) {
					dp[i][j] = 1;
				} else if (j - i == 1) {
					dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j - 2];
				} else {
					for (int t = j - 1; t > i - 2; t--) {
						dp[i][j] += dp[i - 1][t];
					}
				}
			}
		}
		for (int i = 0; i < n; i++) {
			String tc = br.readLine();
			st = new StringTokenizer(tc, " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			System.out.println(dp[a][b]);
		}
	}
}
