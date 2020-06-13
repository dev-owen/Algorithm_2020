import java.io.*;
import java.util.*;

public class BOJ_2437 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> aryLst = new ArrayList<>();
		int[] arr = new int[N + 1];
		int[] sum = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			aryLst.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(aryLst);
		for (int i = 1; i <= N; i++) {
			arr[i] = aryLst.get(i - 1);
			sum[i] = arr[i] + sum[i - 1];
			if (sum[i - 1] < arr[i] - 1) {
				System.out.println(sum[i - 1] + 1);
				return;
			}
		}
		System.out.println(sum[N]+1);
	}
}
