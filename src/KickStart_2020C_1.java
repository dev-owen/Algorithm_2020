import java.io.*;
import java.util.*;

public class KickStart_2020C_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int[] arr = new int[N];
			int cnt = 0, exVal = 0;
			boolean flag = false;
			for (int j = 0; j < N; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			for (int j = 0; j < N; j++) {
				if(arr[j] == K) {
					flag = true;
					exVal = arr[j];
				} else if (flag) {
					if (exVal-1 == arr[j]) {
						if(arr[j] == 1) {
							cnt++;
							flag = false;
							exVal = 0;
						} else exVal = arr[j];
					} else {
						flag = false;
						exVal = 0;
					}
				}
			}
			System.out.println("Case #" + i + ": " + cnt);
		}
	}
}
