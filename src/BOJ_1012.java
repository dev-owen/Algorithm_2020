import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1012 {
	static int rowLen, colLen, cabbageCnt;
	static boolean cabbages[][];
	static int[] dRow = {1, 0, -1, 0};
	static int[] dCol = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		while(T-->0) {
			st = new StringTokenizer(br.readLine());
			rowLen = Integer.parseInt(st.nextToken());
			colLen = Integer.parseInt(st.nextToken());
			cabbageCnt = Integer.parseInt(st.nextToken());
			cabbages = new boolean[rowLen][colLen];
			for(int i = 0; i < cabbageCnt; i++) {
				st = new StringTokenizer(br.readLine());
				int row = Integer.parseInt(st.nextToken());
				int col = Integer.parseInt(st.nextToken());
				cabbages[row][col] = true;
			}
			int idx = 0;
			for(int i = 0; i < rowLen; i++) {
				for(int j = 0; j < colLen; j++) {
					if(cabbages[i][j]) {
						idx++;
						bfs(i,j);
					}
				}
			}
			System.out.println(idx);
		}
	}

	static void bfs(int row, int col) {
		cabbages[row][col] = false;
		int nRow, nCol;
		for(int i = 0; i < 4; i++) {
			nRow = row + dRow[i];
			nCol = col + dCol[i];
			if(nRow >= 0 && nRow < rowLen && nCol >= 0 && nCol < colLen) {
				if(cabbages[nRow][nCol]) bfs(nRow, nCol);
			}
		}
	}
}
