import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11657 {
	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[] cities = new int[N+1];
		int[][] routes = new int[N+1][N+1];
		for(int i = 1; i <= N; i++) {
			cities[i] = 2100000000;
			for(int j = 1; j <= N; j++) {
				routes[i][j] = 10001;
			}
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			routes[a][b] = Math.min(c, routes[a][b]);
		}
		cities[1] = 0;
		// 모든 edge에 대한 edge relation을 최대 M-1번 수행
		boolean[] checkEdges = new boolean[N+1], nextCheckEdges;
		checkEdges[1] = true;
		for(int i = 1; i < M; i++) {
			for(int j = 1; j <= N; j++) {
				nextCheckEdges = new boolean[N+1];
				if(checkEdges[j] = true) {
					for(int k = 1; k <= N; k++) {
						if(routes[j][k] <= 10000) { // 경로가 있다는 의미
							cities[k] = Math.min(cities[k], cities[j]+routes[j][k]);
							nextCheckEdges[k] = true;
						}
					}
				}
				checkEdges = nextCheckEdges;
			}
		}
		// if cities[1] < 0 이면 전체 -1 출력
		if(cities[1] < 0) {
			System.out.println(-1);
			return;
		}
		// 한 번 edge relation 돌아서 negative cycle이 존재하면 전체 -1 출력
		checkEdges = new boolean[N+1];
		checkEdges[1] = true;
		for(int j = 1; j <= N; j++) {
			nextCheckEdges = new boolean[N+1];
			if(checkEdges[j] = true) {
				for(int k = 1; k <= N; k++) {
					if(routes[j][k] <= 10000) { // 경로가 있다는 의미
						if(cities[k] < cities[j]+routes[j][k]) { // negative 경로가 있다는 의미
							System.out.println(-1);
							return;
						}
						nextCheckEdges[k] = true;
					}
				}
			}
			checkEdges = nextCheckEdges;
		}

		// if cities[i] = 2100000000 이면 그 도시는 -1 출력
		for(int i = 2; i <= N; i++) {
			System.out.println(cities[i] < 2100000000 ? cities[i] : -1);
		}
	}
}
