import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1182 {
	static int cnt, N, S;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		cnt = 0;
		dfs(0, 0);
		System.out.println(cnt);
 	}

 	static void dfs(int idx, int sum) {
		if(idx == N) return;
		dfs(idx+1, sum);
		if(sum + arr[idx] == S) cnt++;
		dfs(idx+1, sum + arr[idx]);
	}
}
