import java.util.*;

public class BOJ_2631 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int i, j, n = sc.nextInt(), max = 0, arr[] = new int[n + 1],
		dp[] = new int[n + 1]; // dp[i]는 i번째까지의 LIS(최장 증가 수열)

		for (i = 1; i <= n; i++) arr[i] = sc.nextInt();

		for (i = 1; i <= n; i++) {
			dp[i] = 1;
			for (j = 1; j < i; j++) {
				if (arr[j] < arr[i] && dp[i] < dp[j] + 1) dp[i]++;
			}
			if (max < dp[i]) max = dp[i];
		}
		System.out.println(n - max);
	}
}
