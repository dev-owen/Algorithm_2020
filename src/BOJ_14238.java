import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_14238 {
    // A,B,C 갯수가 1행 2행 3행에 들어가고 4행은 이전 글자, 5행은 그 이전 글자를 나타낸다
    static int[][][][][] dp = new int[51][51][51][3][3];

    // DP - Top Down
    static int go(int a, int b, int c, int p1, int p2) {

        // 주어진 a,b,c를 모두 다 소진했다면 - 이건 가능한 경우임
        if (a + b + c == 0) {
            // 메모이제이션
            dp[a][b][c][p1][p2] = 1;
            return 1;
        }

        // 이미 구했던 적이 있는 값이라면 그냥 출력
        if (dp[a][b][c][p1][p2] != -1) {
            return dp[a][b][c][p1][p2];
        }

        // 아직 쓸 수 있는 a가 남아있으면 & 그 a를 썼을 때의 결과가 참이면(1)
        if (a > 0 && go(a - 1, b, c, 0, p1) == 1) {
            dp[a][b][c][p1][p2] = 1;
            return 1;
        }
        // 아래도 같은 원리. 단 b와 c는 p1,p2조건이 걸리므로 주의
        if (b > 0 && p1 != 1 && go(a, b - 1, c, 1, p1) == 1) {
            dp[a][b][c][p1][p2] = 1;
            return 1;
        }
        if (c > 0 && p1 != 2 && p2 != 2 && go(a, b, c - 1, 2, p1) == 1) {
            dp[a][b][c][p1][p2] = 1;
            return 1;
        }

        // 앞선 if에 하나도 걸리지 못했다면 이건 불가능한 경우
        dp[a][b][c][p1][p2] = 0;
        return 0;
    }

    // 출력을 위한 함수
    static String back(int a, int b, int c, int p1, int p2) {
        // abc를 모두 소진했다면 빈 문자열을 출력하자 - 종료
        if (a + b + c == 0) {
            return "";
        }

        // 아직 사용 가능한 a가 있고 & a를 사용한 결과가 가능한 경우라면
        if (a > 0 && go(a - 1, b, c, 0, p1) == 1) {
            // A를 출력하자
            return "A" + back(a - 1, b, c, 0, p1);
        }
        // 아래도 다 마찬가지 원리
        if (b > 0 && p1 != 1 && go(a, b - 1, c, 1, p1) == 1) {
            return "B" + back(a, b - 1, c, 1, p1);
        }
        if (c > 0 && p1 != 2 && p2 != 2 && go(a, b, c - 1, 2, p1) == 1) {
            return "C" + back(a, b, c - 1, 2, p1);
        }

        return "";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int[] abc = new int[3];
        // a,b,c 갯수를 각각 입력받자
        for (int i = 0; i < s.length(); i++) {
            abc[s.charAt(i) - 'A']++;
        }

        // d는 -1로 초기화 - 아직 구한 적 없는 값이란 뜻
        for (int i = 0; i <= 50; i++) {
            for (int j = 0; j <= 50; j++) {
                for (int k = 0; k <= 50; k++) {
                    for (int l = 0; l < 3; l++) {
                        Arrays.fill(dp[i][j][k][l], -1);
                    }
                }
            }
        }

        int ans = go(abc[0], abc[1], abc[2], 0, 0);

        if (ans == 0) System.out.println(-1);
        else System.out.println(back(abc[0], abc[1], abc[2], 0, 0));
    }
}
