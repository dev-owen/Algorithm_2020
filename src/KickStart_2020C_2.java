import java.io.*;
import java.util.*;

public class KickStart_2020C_2 {
	private static List<Integer> topo;
	private static int parent[];
	private static boolean processed[];
	private static boolean cycle;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(st.nextToken());
		for (int t = 0; t < tc; t++) {
			cycle = false;
			topo = new ArrayList<>();
			parent = new int[26];
			Arrays.fill(parent, -1);
			processed = new boolean[26];
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			Set<Integer> ele = new HashSet<>();
			char grid[][] = new char[r][c];
			for (int i = 0; i < r; i++) {
				grid[i] = br.readLine().toCharArray();
			}
			Set<Integer> graph[] = new HashSet[26];
			for (int i = 0; i < 26; i++) {
				graph[i] = new HashSet<>();
			}
			for (int i = 0; i < 26; i++) {
				int gc[][] = new int[r][c];
				for (int j = 0; j < r; j++) {
					for (int k = 0; k < c; k++) {
						gc[j][k] = grid[j][k] - 'A';
						ele.add(gc[j][k]);
					}
				}
				for (int j = 0; j < r; j++) {
					for (int k = 0; k < c; k++) {
						if (gc[j][k] == i) {
							gc[j][k] = -1;
						}
					}
				}
				for (int j = r - 1; j >= 0; j--) {
					for (int k = 0; k < c; k++) {
						if (gc[j][k] == -1) {
							for (int l = j - 1; l >= 0; l--) {
								if (gc[l][k] != -1 && gc[l][k] != i)
									graph[i].add(gc[l][k]);
							}
						}
					}
				}
			}
			boolean vis[] = new boolean[26];
			for (int j = 0; j < 26; j++) {
				if (!vis[j]) {
					dfs(graph, j, vis);
				}
			}
			if (cycle) {
				out.write("Case #"+(t + 1)+": " + Integer.toString(-1));
			}
			else {
				String ans = "";
				Collections.reverse(topo);
				for (int element: topo) {
					if (ele.contains(element)) {
						ans += (char)(element + 'A');
					}
				}
				out.write("Case #"+(t + 1)+": " + ans);
			}
			out.newLine();
		}
		out.close();
	}
	private static void dfs(Set<Integer> grph[], int ver, boolean v[]) {
		v[ver] = true;
		for (int ch: grph[ver]) {
			if (!v[ch]) {
				parent[ch] = ver;
				dfs(grph, ch, v);
			}
			else if (!processed[ch]) {
				if (parent[ver] == ch && parent[ver] != -1) {
					cycle = true;
				}
			}
		}
		processed[ver] = true;
		topo.add(ver);
	}
}