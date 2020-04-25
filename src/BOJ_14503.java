import java.io.*;
import java.util.*;

public class BOJ_14503 {
	static int n, m, cnt;
	static int[][] area;
	static int[] dVer = { -1, 0, 1, 0 };
	static int[] dHor = { 0, 1, 0, -1 };
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		area = new int[n][m];
		st = new StringTokenizer(br.readLine());
		int startVer = Integer.parseInt(st.nextToken());
		int startHor = Integer.parseInt(st.nextToken());
		int startDir = Integer.parseInt(st.nextToken());

		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				area[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cnt = 0;
		go(startVer, startHor, startDir);
	}

	static void go(int ver, int hor, int dir) {
		if (area[ver][hor] == 0) {
			area[ver][hor] = 2; // 0 : 청소 X, 1 : 벽, 2 : 청소 O
			cnt++;
		}
		int nextVer, nextHor, nextDir;

		for (int i = 0; i < 4; i++) {
			nextDir = (dir + 3 - i) % 4;
			nextVer = ver + dVer[nextDir];
			nextHor = hor + dHor[nextDir];
			if (nextVer >= 0 && nextVer < n && nextHor >= 0 && nextHor < m) {
				if (area[nextVer][nextHor] == 0) {
					go(nextVer, nextHor, nextDir);
					return;
				}
			}
		}

		nextVer = ver - dVer[dir];
		nextHor = hor - dHor[dir];
		if (area[nextVer][nextHor] != 1) {
			go(nextVer, nextHor, dir);
			return;
		}

		System.out.println(cnt);
	}
}
