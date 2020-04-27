import java.io.*;
import java.util.*;


public class BOJ_14502 {
	static int rowSize, colSize, maxSafeZone = 0, map[][], tmpMap[][];
	static int[] dRow = {1, 0, -1, 0};
	static int[] dCol = {0, 1, 0, -1};
	static ArrayList<Virus> virusArray = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		map = new int[rowSize][colSize];

		for (int i = 0; i < rowSize; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < colSize; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) virusArray.add(new Virus(i, j));
			}
		}
		buildWalls(0, 0);
		System.out.println(maxSafeZone);
	}

	static void buildWalls(int idx, int cnt) {
		if (cnt == 3) {
			// 벽이 3개 세워졌으므로 안전지대를 찾아야 함.
			maxSafeZone = Math.max(findSafeZone(), maxSafeZone);
			return;
		}
		for (int i = cnt; i < rowSize * colSize; i++) {
			int row = i / colSize;
			int col = i % colSize;

			if (map[row][col] == 0) {
				map[row][col] = 1;
				buildWalls(i + 1, cnt + 1);
				map[row][col] = 0;
			}
		}
	}

	static int findSafeZone() {
		tmpMap = new int[rowSize][colSize];
		copy();
		for (Virus virus : virusArray) {
			dfs(virus.row, virus.col);
		}
		int cnt = 0;
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < colSize; j++) {
				if (tmpMap[i][j] == 0) cnt++;
			}
		}
		return cnt;
	}

	static void dfs(int row, int col) {
		int nextRow, nextCol;

		for (int i = 0; i < 4; i++) {
			nextRow = row + dRow[i];
			nextCol = col + dCol[i];
			if (nextRow >= 0 && nextRow < rowSize && nextCol >= 0 && nextCol < colSize && tmpMap[nextRow][nextCol] == 0) {
				tmpMap[nextRow][nextCol] = 2;
				dfs(nextRow, nextCol);
			}
		}
	}

	public static void copy() {
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < colSize; j++) {
				tmpMap[i][j] = map[i][j];
			}
		}
	}
}

class Virus {
	int row, col;

	Virus(int row, int col) {
		this.row = row;
		this.col = col;
	}
}
