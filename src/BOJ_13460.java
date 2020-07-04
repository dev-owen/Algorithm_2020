import java.io.*;
import java.util.LinkedList;

public class BOJ_13460 {
	static int holeRow, holeCol;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		boolean[][] board = new boolean[N][M];
		int redRow = 0, redCol = 0, blueRow = 0, blueCol = 0;
		for(int i = 0; i < N; i++) {
			str = br.readLine().split("");
			for(int j = 0; j < M; j++) {
				board[i][j] = !str[j].equals("#"); // # 이면 false
				if(str[j].equals("R")) {
					redRow = i;
					redCol = j;
				} else if(str[j].equals("B")) {
					blueRow = i;
					blueCol = j;
				} else if(str[j].equals("O")) {
					holeRow = i;
					holeCol = j;
				}
			}
		}

		LinkedList<Point> queue = new LinkedList<>();
		int cnt = 0;
		queue.add(new Point(redRow, redCol, blueRow, blueCol, 0, -1));
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			for(int i = 0; i < 4; i++) {

			}
		}

		System.out.println(-1);
	}

	private static class Point {
		int redRow, redCol, blueRow, blueCol, cnt, dir;
		private Point(int redRow, int redCol, int blueRow, int blueCol, int cnt, int dir) {
			this.redRow = redRow;
			this.redCol = redCol;
			this.blueRow = blueRow;
			this.blueCol = blueCol;
			this.cnt = cnt;
			this.dir = dir;
		}
	}
}
