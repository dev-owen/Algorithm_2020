import java.io.*;
import java.util.*;

public class BOJ_11559 {
	static char board[][];
	static boolean checked[][];
	static Set<Point> gSet;
	static int[] dRow = {1, 0, -1, 0};
	static int[] dCol = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new char[6][12];
		for (int i = 0; i < 12; i++) {
			char[] charArr = br.readLine().toCharArray();
			for (int j = 0; j < 6; j++) {
				board[j][11 - i] = charArr[j];
			}
		}
		int cnt = 0;
		while (true) {
			checked = new boolean[6][12];
			gSet = new HashSet<>();
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 12; j++) {
					if (!checked[i][j] && board[i][j] != '.') bfs(i, j, board[i][j], new HashSet<>());
				}
			}
			if (gSet.size() == 0) break;
			else {
				cnt++;
				// gSet에 들어있는 값들 다 빼주고
				for(Point p: gSet) {
					board[p.row][p.col] = '.';
				}
				// 빈칸 밀어서 채워넣어줌
				for(int i = 0; i < 6; i++) {
					ArrayList<Character> aryLst = new ArrayList<>();
					for(int j = 0; j < 12; j++) {
						if(board[i][j] != '.') aryLst.add(board[i][j]);
					}
					for(int j = 0; j < 12; j++) {
						if(j < aryLst.size()) board[i][j] = aryLst.get(j);
						else board[i][j] = '.';
					}
				}
			}
		}
		System.out.println(cnt);
	}

	static void bfs(int row, int col, char c, Set<Point> set) {
		Queue<Point> queue = new LinkedList<>();
		checked[row][col] = true;
		set.add(new Point(row, col));
		queue.add(new Point(row, col));
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			int nRow, nCol;
			for (int i = 0; i < 4; i++) {
				nRow = p.row + dRow[i];
				nCol = p.col + dCol[i];
				if (nRow >= 0 && nRow < 6 && nCol >= 0 && nCol < 12) {
					if(!checked[nRow][nCol] && board[nRow][nCol] == c) {
						checked[nRow][nCol] = true;
						set.add(new Point(nRow, nCol));
						queue.add(new Point(nRow, nCol));
					}
				}
			}
		}
		if(set.size() >= 4) {
			for(Point p : set) {
				gSet.add(p);
			}
		}
		return;
	}

	private static class Point {
		int row, col;

		private Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
}
