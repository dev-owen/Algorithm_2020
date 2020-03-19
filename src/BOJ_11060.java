import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11060 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		int[] dp = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		dp[0] = 0;
		for(int i = 1; i < N; i++) {
			int minStep = 2100000000;
			for(int j = i-1; j >= 0; j--) {
				if(arr[j] >= (i-j)) minStep = Math.min(minStep, dp[j]);
			}
			dp[i] = minStep+1;
		}
		System.out.println(dp[N-1] == 2100000001 ? -1 : dp[N-1]);
	}
}
