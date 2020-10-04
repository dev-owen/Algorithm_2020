import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KickStart_2020A_2 {
    static int[][] stacks, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            stacks = new int[N + 1][K + 1];
            dp = new int[N + 1][P + 1];
            for (int j = 1; j <= N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 1; k <= K; k++) {
                    stacks[j][k] = Integer.parseInt(st.nextToken()) + stacks[j][k - 1];
                }
                for (int p = 1; p <= P; p++) {
                    for (int k = 0; k <= Math.min(K, p); k++) {
                        dp[j][p] = Math.max(dp[j][p], dp[j - 1][p - k] + stacks[j][k]);
                    }
                }
            }
            System.out.println("Case #" + i + ": " + dp[N][P]);
        }
    }
}
