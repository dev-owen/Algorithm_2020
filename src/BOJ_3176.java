import java.io.*;
import java.util.*;

public class BOJ_3176 {
	private static class Node {
		int dest, length;

		Node(int dest, int length) {
			this.dest = dest;
			this.length = length;
		}
	}

	static int n, m, a, b, c;
	static int[] d;
	static int[][] par, qmax, qmin;
	static boolean[] visit;
	static HashMap<Integer, ArrayList<Node>> hm = new HashMap<>();
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = stoi(br.readLine());

		visit = new boolean[n + 1];
		d = new int[n + 1];
		par = new int[n + 1][21];
		qmax = new int[n + 1][21];
		qmin = new int[n + 1][21];
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			a = stoi(st.nextToken());
			b = stoi(st.nextToken());
			c = stoi(st.nextToken());

			if (!hm.containsKey(a)) {
				hm.put(a, new ArrayList<Node>());
			}
			hm.get(a).add(new Node(b, c));

			if (!hm.containsKey(b)) {
				hm.put(b, new ArrayList<Node>());
			}
			hm.get(b).add(new Node(a, c));

		}

		dfs(1, 0);
		f();
		m = stoi(br.readLine());

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			Node result = lca(stoi(st.nextToken()), stoi(st.nextToken()));
			bw.append(result.dest + " " + result.length + "\n");
		}
		bw.flush();
		bw.close();
	}

	private static void dfs(int here, int depth) {
		visit[here] = true;
		d[here] = depth;
		for (Node there : hm.get(here)) {
			if (visit[there.dest]) {
				continue;
			}
			dfs(there.dest, depth + 1);
			par[there.dest][0] = here;
			qmin[there.dest][0] = there.length;
			qmax[there.dest][0] = there.length;
		}
	}

	private static void f() {
		for (int j = 1; j < 21; j++) {
			for (int i = 1; i <= n; i++) {
				par[i][j] = par[par[i][j - 1]][j - 1];
				qmin[i][j] = Math.min(qmin[i][j - 1], qmin[par[i][j - 1]][j - 1]);
				qmax[i][j] = Math.max(qmax[i][j - 1], qmax[par[i][j - 1]][j - 1]);
			}
		}
	}

	private static Node lca(int tx, int ty) {
		int x = tx, y = ty;
		if (d[tx] > d[ty]) {
			y = tx;
			x = ty;
		}

		int rmin = Integer.MAX_VALUE;
		int rmax = Integer.MIN_VALUE;

		for (int i = 20; i >= 0; i--) {
			if (d[y] - d[x] >= (1 << i)) {
				rmin = Math.min(rmin, qmin[y][i]);
				rmax = Math.max(rmax, qmax[y][i]);
				y = par[y][i];
			}
		}

		if (x == y)
			return new Node(rmin, rmax);

		for (int i = 20; i >= 0; i--) {
			if (par[x][i] != par[y][i]) {
				rmin = Math.min(rmin, Math.min(qmin[x][i], qmin[y][i]));
				rmax = Math.max(rmax, Math.max(qmax[x][i], qmax[y][i]));
				x = par[x][i];
				y = par[y][i];
			}
		}
		rmin = Math.min(rmin, Math.min(qmin[x][0], qmin[y][0]));
		rmax = Math.max(rmax, Math.max(qmax[x][0], qmax[y][0]));
		return new Node(rmin, rmax);
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}