import java.io.*;
import java.util.*;

public class KickStart_2020C_3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= T; i++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] arr = new int[N];
			int sum = 0, minSum = 0;
			long cnt = 0;
			HashMap<Integer, Integer> prefixSumMap = new HashMap<>(); // prefixSum.get(i) = j 인 경우 prefixSum이 i인 경우가 j
			prefixSumMap.put(0, 1);
			for (int j = 0; j < N; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
				sum += arr[j];
				minSum = Math.min(sum, minSum);
				int k = 0;
				while (sum - k * k >= minSum && k <= Math.sqrt(100*100000)) {
					if (prefixSumMap.containsKey(sum - k * k)) {
						cnt += prefixSumMap.get(sum - k * k);
					}
					k++;
				}
				if (prefixSumMap.containsKey(sum)) prefixSumMap.put(sum, prefixSumMap.get(sum) + 1);
				else prefixSumMap.put(sum, 1);
			}
			System.out.println("Case #" + i + ": " + cnt);
		}
	}
}
