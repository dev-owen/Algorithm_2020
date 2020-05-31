import java.io.*;
import java.util.*;

public class BOJ_7579 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] memorys = new int[101];
		int[] costs = new int[101];
		int[][] dp = new int[101][10001]; // i번째까지 탐색 하고, j만큼의 비용으로 차지할 수 있는 메모리의 크기
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			memorys[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			costs[i] = Integer.parseInt(st.nextToken());
		}
		int answer = Integer.MAX_VALUE;
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= 10000; j++) {
				if(j - costs[i] >= 0) dp[i][j] = Math.max(dp[i][j], dp[i-1][j-costs[i]]+memorys[i]); // i번째 선택 X, O 중 큰 값 저장
				dp[i][j] = Math.max(dp[i-1][j], dp[i][j]);
				if(dp[i][j] >= M) {
					answer = Math.min(answer, j);
				}
			}
		}
		System.out.println(answer);
	}
}
