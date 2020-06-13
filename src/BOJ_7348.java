import java.io.*;
import java.util.*;

public class BOJ_7348 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		int[] corridor = new int[201];
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int start = (Integer.parseInt(st.nextToken()) + 1) / 2;
				int end = (Integer.parseInt(st.nextToken()) + 1) / 2;
				for (int j = Math.min(start, end); j <= Math.max(start, end); j++) {
					corridor[j]++;
				}
			}
			int max = 0;
			for(int i = 1; i <= 200; i++) {
				max = Math.max(corridor[i], max);
			}
			System.out.println(max*10);
			corridor = new int[201];
		}
	}
}
