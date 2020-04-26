import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_3184 {
	static int N, M, garden[][], sheepNum, wolfNum, sheepSum, wolfSum;
	static boolean[][] checked;
	static int[] dRow = {1, 0, -1, 0};
	static int[] dCol = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strArr = br.readLine().split(" ");
		N = Integer.parseInt(strArr[0]);
		M = Integer.parseInt(strArr[1]);
		garden = new int[N + 2][M + 2];
		checked = new boolean[N + 2][M + 2];

		for (int i = 1; i <= N; i++) {
			strArr = br.readLine().split("");
			for (int j = 1; j <= M; j++) {
				if (strArr[j - 1].equals(".")) garden[i][j] = 0;
				else if (strArr[j - 1].equals("#")) garden[i][j] = 1;
				else if (strArr[j - 1].equals("v")) garden[i][j] = 2;
				else if (strArr[j - 1].equals("o")) garden[i][j] = 3;
			}
		}
		sheepSum = 0;
		wolfSum = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (garden[i][j] != 1 && !checked[i][j]) {
					sheepNum = 0;
					wolfNum = 0;
					bfs(i, j);
					if(sheepNum <= wolfNum) sheepNum = 0;
					else wolfNum = 0;
					sheepSum += sheepNum;
					wolfSum += wolfNum;
				}
			}
		}
		System.out.println(sheepSum + " " + wolfSum);
	}

	static void bfs(int row, int col) {
		Queue<_Point> queue = new LinkedList<>();
		queue.add(new _Point(row, col));
		checked[row][col] = true;
		if (garden[row][col] == 2) wolfNum++;
		else if (garden[row][col] == 3) sheepNum++;

		while (!queue.isEmpty()) {
			_Point p = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nRow = p.row + dRow[i];
				int nCol = p.col + dCol[i];
				if (nRow >= 1 && nRow <= N && nCol >= 1 && nCol <= M && !checked[nRow][nCol] && garden[nRow][nCol] != 1) {
					queue.add(new _Point(nRow, nCol));
					checked[nRow][nCol] = true;
					if (garden[nRow][nCol] == 2) wolfNum++;
					else if (garden[nRow][nCol] == 3) sheepNum++;
				}
			}
		}
	}
}

class _Point {
	int row, col;

	_Point(int row, int col) {
		this.row = row;
		this.col = col;
	}
}
