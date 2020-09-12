import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2294 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] input = new int[n + 1];
        int[] dp = new int[k + 1];
        Arrays.fill(dp, 100001);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < n; i++) {
            for (int j = input[i]; j <= k; j++) {
                dp[j] = Math.min(dp[j], dp[j - input[i]] + 1);
            }
        }
        System.out.println(dp[k] == 100001 ? -1 : dp[k]);
    }
}
