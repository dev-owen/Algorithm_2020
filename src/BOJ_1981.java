import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1981 {
	static int n, arrays[][];
	static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
	static boolean[][] checked;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		arrays = new int[n+2][n+2];
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				arrays[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int low = 0, high = 200, mid, ans = 201;
		while(high >= low) {
			mid = (low+high)/2;
			if(pass(mid)) { // mid값 이내에서 차이가 난다.
				high = mid-1;
				ans = Math.min(ans, mid);
			} else { // mid 값을 초과하는 차이가 발생한다.
				low = mid+1;
			}
		}
		System.out.println(ans);
	}

	static boolean pass(int criteria) {
		int initMin, initMax, x = 1, y = 1;
		if(arrays[1][1] >= arrays[n][n]) {
			initMin = arrays[n][n];
			initMax = arrays[1][1];
		} else {
			initMin = arrays[1][1];
			initMax = arrays[n][n];
		}
		Queue<Position> queue = new LinkedList<>();
		checked = new boolean[n+2][n+2];
		queue.add(new Position(x, y, initMin, initMax));

		while(!queue.isEmpty()) {
			Position status = queue.poll();
			int nowX = status.x;
			int nowY = status.y;
			if(nowX == n && nowY == n) return true;
			int nowMin = status.minVal;
			int nowMax = status.maxVal;

			if(checked[nowX][nowY]) continue;

			checked[nowX][nowY] = true;
			for(int i = 0; i < 4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				if(nextX >= 1 && nextX <= n && nextY >= 1 && nextY <= n && !checked[nextX][nextY]) {
					if(arrays[nextX][nextY] > nowMax) {
						if(arrays[nextX][nextY] - nowMin <= criteria) {
							queue.add(new Position(nextX, nextY, nowMin, arrays[nextX][nextY]));
						}
					} else if(arrays[nextX][nextY] < nowMin) {
						if(nowMax - arrays[nextX][nextY] <= criteria) {
							queue.add(new Position(nextX, nextY, arrays[nextX][nextY], nowMax));
						}
					} else {
						queue.add(new Position(nextX, nextY, nowMin, nowMax));
					}
				}
			}
		}
		return false;
	}
}

class Position {
	int x, y, minVal, maxVal;

	Position(int x, int y, int minVal, int maxVal) {
		this.x = x;
		this.y = y;
		this.maxVal = maxVal;
		this.minVal = minVal;
	}
}
