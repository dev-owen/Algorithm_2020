import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1300 {
	static long N, K, res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Long.parseLong(br.readLine());
		K = Long.parseLong(br.readLine());

		long left = 1;
		long right = K;

		System.out.println(biSearch(left, right));
	}

	static long biSearch(long left, long right) {
		int cnt = 0;
		long mid = (left + right) / 2;
		if (left > right) return res;
		for (int i = 1; i <= N; i++) {
			cnt += Math.min(mid / i, N);
		}

		if (K <= cnt) {
			res = mid;
			return biSearch(left, mid - 1);
		} else {
			return biSearch(mid + 1, right);
		}
	}
}
