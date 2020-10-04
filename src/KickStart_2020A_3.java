import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KickStart_2020A_3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[] diffs = new int[N - 1];
            st = new StringTokenizer(br.readLine());
            int exNum = Integer.parseInt(st.nextToken());
            for (int j = 0; j < N - 1; j++) {
                int num = Integer.parseInt(st.nextToken());
                diffs[j] = num - exNum;
                exNum = num;
            }
            int low = 1;
            int high = 1000000000;
            int mid = (high + low) / 2;
            while (high > low) {
                int cnt = 0;
                for (int j = 0; j < N - 1; j++) {
                    cnt += Math.max((diffs[j] - 1) / mid, 0);
                }
                if (cnt > K) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
                mid = (high + low) / 2;
            }
            System.out.println("Case #" + i + ": " + low);
        }
    }
}
