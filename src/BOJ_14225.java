import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14225 {
	static int arr[] = new int[21], N;
	static boolean[] checked = new boolean[20000001];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		recur(0, 0);
		for(int i = 1; i <= 20000000; i++) {
			if(!checked[i]) {
				System.out.println(i);
				return;
			}
		}
	}

	static void recur(int sum, int idx) {
		if(idx > N) return;
		checked[sum] = true;
		recur(sum + arr[idx], idx+1);
		recur(sum, idx+1);
	}
}