import java.util.*;
import java.io.*;

public class BOJ_1916 {
	static int N, M, map[][], minCost[];
	static final int INF = 1000000001;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		minCost = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			minCost[i] = INF;
			for(int j = 1; j <= N; j++) {
				map[i][j] = INF;
			}
		}
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			map[first][second] = Math.min(weight, map[first][second]);
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		dijkstra(start);
		System.out.println(minCost[end]);
	}

	static void dijkstra(int start) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(start);
		map[start][start] = 0;
		minCost[start] = 0;
		int i, before;
		while (!pq.isEmpty()) {
			before = pq.poll();
			for (i = 1; i <= N; i++) {
				if (minCost[i] > map[before][i] + minCost[before]) {
					minCost[i] = map[before][i] + minCost[before];
					pq.add(i);
				}
			}
		}
	}
}
