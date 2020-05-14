import java.io.*;
import java.util.*;

public class BOJ_1707 {
	static int nodes[], V, E;
	static ArrayList<Integer>[] graph;
	static final int RED = 1, BLUE = 2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			nodes = new int[V + 1];
			graph = new ArrayList[V + 1];
			for (int i = 1; i <= V; i++) {
				graph[i] = new ArrayList<>();
			}
			while (E-- > 0) {
				st = new StringTokenizer(br.readLine());
				int first = Integer.parseInt(st.nextToken());
				int second = Integer.parseInt(st.nextToken());
				graph[first].add(second);
				graph[second].add(first);
			}
			for (int i = 1; i <= V; i++) {
				if (nodes[i] == 0) DFS(i, RED);
			}

			// biGraph인지 판별
			boolean biGraph = true;

			loop:
			for (int i = 1; i <= V; i++) {
				for (int j : graph[i]) {
					if (nodes[i] == nodes[j]) {
						biGraph = false;
						break loop;
					}
				}
			}
			System.out.println(biGraph ? "YES" : "NO");
		}
	}

	static void DFS(int node, int color) {
		nodes[node] = color;

		for (int i = 0; i < graph[node].size() ; i++) {
			if (nodes[graph[node].get(i)] == 0) DFS(graph[node].get(i), 3 - color);
		}
	}
}
