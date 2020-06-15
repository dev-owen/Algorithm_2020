import java.io.*;
import java.util.*;

public class BOJ_1507 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] roads = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				roads[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// floyd-warshall
		boolean[][] canErased = new boolean[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j) canErased[i][j] = true;
				else {
					for (int k = 1; k <= N; k++) {
						if (k != i && k != j) {
							if(roads[i][k] + roads[k][j] < roads[i][j]) {
								System.out.println(-1);
								return;
							}
							if (roads[i][k] + roads[k][j] == roads[i][j]) canErased[i][j] = true;
						}
					}
				}
			}
		}
		int sum = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (!canErased[i][j]) sum += roads[i][j];
			}
		}
		System.out.println(sum/2);
	}
}
