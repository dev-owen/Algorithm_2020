import java.io.*;
import java.util.*;

public class BOJ_2698 {
	static int[][][] dp = new int[101][101][2];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		fillDP();

		while (T-->0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			System.out.println(dp[n][k][0]+dp[n][k][1]);
		}
	}

	static void fillDP() {
		dp[1][0][0] = 1; // 0
		dp[1][0][1] = 1; // 1
		dp[2][0][0] = 2; // 00, 10
		dp[2][0][1] = 1; // 01
		dp[2][1][1] = 1; // 11
		for(int n = 3; n <= 100; n++) {
			for(int k = 0; k < n; k++) {
				if(k == 0) dp[n][k][1] += dp[n-1][k][0];
				else dp[n][k][1] += (dp[n-1][k][0] + dp[n-1][k-1][1]);
				dp[n][k][0] += (dp[n-1][k][0] + dp[n-1][k][1]);
			}
		}
	}
}
