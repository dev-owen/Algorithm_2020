import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KickStart_2020A_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T =  Integer.parseInt(st.nextToken());
        for(int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int[] houses = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                houses[j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(houses);
            int cnt = 0;
            for(int j = 0; j < N; j++) {
                if(B >= houses[j]) {
                    cnt++;
                    B -= houses[j];
                }
            }
            System.out.println("Case #"+i+": "+cnt);
        }

    }
}
