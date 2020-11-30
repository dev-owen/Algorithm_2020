import java.io.*;
import java.util.*;

public class KickStart_2020B_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			long D = Long.parseLong(st.nextToken());
			long arr[] = new long[N];
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[j] = Long.parseLong(st.nextToken());
			}
			for (int j = N - 1; j >= 0; j--) {
				D = (D / arr[j]) * arr[j];
			}
			System.out.println("Case #" + i + ": " + D);
		}
	}
}
