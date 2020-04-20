import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_10026 {
	static char[][] RGB;
	static int n;
	static boolean[][] checked;
	static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		RGB = new char[n+2][n+2];
		for(int i = 1; i <= n; i++) {
			char[] cArr = br.readLine().toCharArray();
			for(int j = 0; j < n; j++) {
				RGB[i][j+1] = cArr[j];
			}
		}

		// 일반인
		checked = new boolean[n+2][n+2];
		int idxNormal = 0;
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(!checked[i][j]) {
					bfsNormal(i,j,RGB[i][j]);
					idxNormal++;
				}
			}
		}

		// 색맹
		checked = new boolean[n+2][n+2];
		int idxColorBlind = 0;
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(!checked[i][j]) {
					bfsColorBlind(i,j,RGB[i][j]);
					idxColorBlind++;
				}
			}
		}
		System.out.println(idxNormal + " " + idxColorBlind);
	}

	static void bfsNormal(int x, int y, char color) {
		Queue<Dot> queue = new LinkedList<>();
		queue.add(new Dot(x, y));
		checked[x][y] = true;
		while(!queue.isEmpty()) {
			Dot dot = queue.poll();
			int nowX = dot.x, nowY = dot.y, nextX, nextY;
			for(int i = 0; i < 4; i++) {
				nextX = nowX + dx[i];
				nextY = nowY + dy[i];
				if(nextX >= 1 && nextX <= n && nextY >= 1 && nextY <= n && !checked[nextX][nextY] && RGB[nextX][nextY] == color) {
					queue.add(new Dot(nextX, nextY));
					checked[nextX][nextY] = true;
				}
			}
		}
	}

	static void bfsColorBlind(int x, int y, char color) {
		Queue<Dot> queue = new LinkedList<>();
		queue.add(new Dot(x, y));
		checked[x][y] = true;
		while(!queue.isEmpty()) {
			Dot dot = queue.poll();
			int nowX = dot.x, nowY = dot.y, nextX, nextY;
			for(int i = 0; i < 4; i++) {
				nextX = nowX + dx[i];
				nextY = nowY + dy[i];
				if(nextX >= 1 && nextX <= n && nextY >= 1 && nextY <= n && !checked[nextX][nextY]) {
					if(color == 'R' || color == 'G') {
						if(RGB[nextX][nextY] == 'R' || RGB[nextX][nextY] == 'G') {
							queue.add(new Dot(nextX, nextY));
							checked[nextX][nextY] = true;
						}
					} else {
						if(RGB[nextX][nextY] == 'B') {
							queue.add(new Dot(nextX, nextY));
							checked[nextX][nextY] = true;
						}
					}
				}
			}
		}
	}
}

class Dot {
	int x,y;
	Dot(int x, int y) {
		this.x = x;
		this.y = y;
	}
}