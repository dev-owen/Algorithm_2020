import java.io.*;
import java.util.*;

public class BOJ_1865 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(st.nextToken());

		while (TC-->0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());

			List<Edge> edgeList = new ArrayList<>();
			int[] wormHallEnds = new int[W];
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
				edgeList.add(new Edge(S, E, (-1) * T));
				wormHallEnds[i] = E;
			}

			int[] nodes;
			boolean isNegative = false;
			for(int w = 0; w < W; w++) {

				nodes = new int[N+1];
				Arrays.fill(nodes, Integer.MAX_VALUE);
				nodes[wormHallEnds[w]] = 0;

				// N-1 Edge Relaxation
				outerloop:
				for(int i = 1; i <= N; i++) {
					for(Edge edge : edgeList) {
						if(nodes[edge.start] != Integer.MAX_VALUE && nodes[edge.end] > nodes[edge.start] + edge.time) {
							nodes[edge.end] = nodes[edge.start] + edge.time;
							// Negative Cycle
							if (i == N) {
								isNegative = true;
								break outerloop;
							}
						}
					}
				}
			}
			sb.append((isNegative ? "YES" : "NO") + "\n");
		}
		System.out.println(sb);
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
