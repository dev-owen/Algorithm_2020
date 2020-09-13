import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12872 {
    static final int INF = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        long[][] dp = new long[101][101]; // dp[i][j] : i개의 플레이리스트를 j개의 노래로 구성
        dp[1][1] = N;
        for (int i = 2; i <= P; i++) {
            for (int j = Math.min(i, N); j >= 1; j--) {
                dp[i][j] = (dp[i - 1][j - 1] * Math.max(N - (j - 1), 0) + dp[i - 1][j] * Math.max(j - M, 0)) % INF;
            }
        }
        System.out.println(dp[P][N]);
    }
}