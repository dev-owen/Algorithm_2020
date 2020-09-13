import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_3012 {
    static char input[];
    static boolean check[][];
    static long dp[][];
    static final long MOD = 100000L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        input = str.toCharArray();
        check = new boolean[N + 1][N + 1];
        dp = new long[N + 1][N + 1];
        long result = solve(0, N - 1);

        if (result >= MOD) System.out.println(String.format("%05d", result - MOD));
        else System.out.println(result);
    }

    public static long solve(int s, int e) { // 분할정복
        char open[] = "({[".toCharArray();
        char close[] = ")}]".toCharArray();
        if (check[s][e]) return dp[s][e]; // 반복되는 연산을 최소화
        if (s > e) return 1;

        for (int k = s + 1; k <= e; k += 2) {
            for (int l = 0; l < 3; l++) {
                if (input[s] == open[l] || input[s] == '?') {
                    if (input[k] == close[l] || input[k] == '?') {
                        dp[s][e] += solve(s + 1, k - 1) * solve(k + 1, e); // s [s+1 ...k-1] k [k+1 ... e]
                        if (dp[s][e] >= MOD) dp[s][e] = MOD + dp[s][e] % MOD;
                        check[s][e] = true;
                    }
                }
            }
        }
        return dp[s][e];
    }
}
