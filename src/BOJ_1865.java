import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1865 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());

		while (TC-- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());

			List<Edge> edgeList = new ArrayList<>();
			List<Edge> wormhallList = new ArrayList<>();
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				edgeList.add(new Edge(S, E, T));
				edgeList.add(new Edge(E, S, T));
			}
			for (int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				wormhallList.add(new Edge(S, E, (-1) * T));
			}

			long[][] nodes = new long[N+1][N+1];
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					nodes[i][j] = Integer.MAX_VALUE;
				}
			}

			// N-1 Edge Relaxation
			for(int i = 1; i <= N; i++) {
				nodes[i][i] = 0;
				for(int j = 0; j < 2*M; j++) {
					Edge edge = edgeList.get(j);
					nodes[edge.start][edge.end] = Math.min(nodes[edge.start][edge.end], edge.time);
					if(nodes[i][edge.end] > nodes[i][edge.start] + edge.time) {
						nodes[i][edge.end] = nodes[i][edge.start] + edge.time;
					}
				}
			}

			boolean isNegative = false;

			// Negative Cycle
			outerloop:
			for(int i = 1; i <= N; i++) {
				for(int j = 0; j < W; j++) {
					Edge wormhall = wormhallList.get(j);
					if(nodes[i][wormhall.start] + wormhall.time + nodes[i][wormhall.end] < 0) {
						isNegative = true;
						break outerloop;
					}
				}
			}
			System.out.println(isNegative ? "YES" : "NO");
		}
	}
}

class Edge {
	int start, end, time;

	Edge(int start, int end, int time) {
		this.start = start;
		this.end = end;
		this.time = time;
	}
}
