import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1495 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] V = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			V[i] = Integer.parseInt(st.nextToken());
		}
		boolean[][] arr = new boolean[M+1][N+1];
		arr[S][0] = true;
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j <= M; j++) {
				if(arr[j][i-1]) { // i번째에서 j+V[i] 또는 j-V[i]가 가능할 수 있다.
					if(j+V[i] >= 0 && j+V[i] <= M) arr[j+V[i]][i] = true;
					if(j-V[i] >= 0 && j-V[i] <= M) arr[j-V[i]][i] = true;
				}
			}
		}
		int ans = -1;
		for(int i = 0; i <= M; i++) {
			if(arr[i][N]) ans = i;
		}
		System.out.println(ans);
	}
}