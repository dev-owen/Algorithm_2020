import java.io.*;
import java.util.*;

public class BOJ_14502 {
	static int verSize, horSize, maxSafeZone = 0, map[][], tmpMap[][];
	static int[] dVer = { 1, 0, -1, 0 };
	static int[] dHor = { 0, 1, 0, -1 };
	static ArrayList<Virus> aryLst = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		verSize = Integer.parseInt(st.nextToken());
		horSize = Integer.parseInt(st.nextToken());
		map = new int[verSize+2][horSize+2];
		int startVer = 0, startHor = 0;
		for(int i = 0; i <= verSize+1; i++) {
			for(int j = 0; j <= horSize+1; j++) {
				if(i == 0 || j == 0 || i == verSize+1 || j == horSize+1) map[i][j] = -1;
			}
		}
		for(int i = 1; i <= verSize; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= horSize; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) {
					startVer = i;
					startHor = j;
				} else if(map[i][j] == 2) {
					aryLst.add(new Virus(i, j));
				}
			}
		}
		search(startVer, startHor, 3);
		System.out.println(maxSafeZone);
	}

	static void search(int ver, int hor, int leftWalls) {
		if(leftWalls == 0) {
			// 해당 map을 가지고 안전지대 찾아야 함.
			maxSafeZone = Math.max(findSafeZone(), maxSafeZone);
			return;
		}

		for(int i = ver; i >= 1; i--) {
			for(int j = hor; j >= 1; j--) {
				if(map[i][j] == 0 && !(i == ver && j == hor)) {
					// (ver, hor)에 벽을 짓고 탐색
					map[ver][hor] = 1;
					search(i, j, leftWalls-1);
					map[ver][hor] = 0;

					// (ver, hor)에 벽을 안 짓고 탐색
					search(i, j, leftWalls);
				}
			}
		}
		return;
	}

	// 벽 세개 세운 지도에서 안전지대 찾는 메서드
	static int findSafeZone() {
		tmpMap = new int[verSize+2][horSize+2];
		copy();
		for(int i = 0; i < aryLst.size(); i++) {
			dfs(aryLst.get(i).ver, aryLst.get(i).ver);
		}

		int cnt = 0;
		for(int i = 1; i <= verSize; i++) {
			for(int j = 1; j <= horSize; j++) {
				if(tmpMap[i][j] == 0) cnt++;
			}
		}
		return cnt;
	}

	static void dfs(int ver, int hor) {
		int nextVer, nextHor;

		for(int i = 0; i < 4; i++) {
			nextVer = ver + dVer[i];
			nextHor = hor + dHor[i];
			if(nextVer >= 1 && nextVer <= verSize && nextHor >= 1 && nextHor <= horSize && tmpMap[nextVer][nextHor] == 0) {
				tmpMap[nextVer][nextHor] = 2;
				dfs(nextVer, nextHor);
			}
		}
	}

	public static void copy() {
		for (int i = 1; i <= verSize; i++) {
			for(int j = 1; j <= horSize; j++) {
				tmpMap[i][j] = map[i][j];
			}
		}
	}
}

class Virus {
	int ver, hor;
	Virus(int ver, int hor) {
		this.ver = ver;
		this.hor = hor;
	}
}
