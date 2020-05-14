import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14501 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] timeArr = new int[N + 1];
		int[] payArr = new int[N + 1];
		int dp[] = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			timeArr[i] = Integer.parseInt(st.nextToken());
			payArr[i] = Integer.parseInt(st.nextToken());
			dp[i] = payArr[i];
		}
		for (int i = 2; i <= N; i++) {
			for (int j = i - 1; j > 0; j--) {
				if (j + timeArr[j] <= i) dp[i] = Math.max(dp[j] + payArr[i], dp[i]);
			}
		}

		int maxPay = 0;

		for (int i = 1; i <= N; i++) {
			if (i + timeArr[i] <= N + 1) {
				maxPay = Math.max(dp[i], maxPay);
			}
		}

		System.out.println(maxPay);
	}
}
