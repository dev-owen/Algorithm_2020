import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206 {
	static int N, M, minPath = Integer.MAX_VALUE;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean[][] map = new boolean[1001][1001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		for(int i = 1; i <= M; i++) {
			char[] charArr = br.readLine().toCharArray();
			for(int j = 1; j <= N; j++) {
				map[j][i] = charArr[j-1] - '0' == 0 ? true : false;
			}
		}

		bfs();

		System.out.println(minPath != Integer.MAX_VALUE ? minPath : "-1");
	}

	static void bfs() {
		Queue<Move> queue = new LinkedList<>();

		boolean[][] checked = new boolean[1001][1001];
		checked[1][1] = true;

		queue.add(new Move(1,1,false, 1, checked));
		while(!queue.isEmpty()) {
			Move move = queue.poll();
			int x = move.x;
			int y = move.y;
			int cnt = move.cnt;
			boolean isBombUsed = move.isBombUsed;
			boolean[][] isChecked = move.isChecked;

			if(x == N && y == M) {
				minPath = Math.min(cnt, minPath);
				return;
			}

			for(int i = 0; i < 4; i++) {
				int nextX = x + dx[i];
				int nextY = y + dy[i];
				if(nextX >= 1 && nextX <= N && nextY >= 1 && nextY <= M) {
					if(map[nextX][nextY] && !isChecked[nextX][nextY]) {
						isChecked[nextX][nextY] = true;
						queue.add(new Move(nextX, nextY, isBombUsed, cnt+1, isChecked));
						isChecked[nextX][nextY] = false;
					} else {
						if(!isBombUsed && !isChecked[nextX][nextY]) {
							isChecked[nextX][nextY] = true;
							queue.add(new Move(nextX, nextY, true, cnt+1, isChecked));
							isChecked[nextX][nextY] = false;
						}
					}
				}
			}
		}
	}
}

class Move {
	int x;
	int y;
	boolean isBombUsed;
	int cnt;
	boolean[][] isChecked;
	public Move(int x, int y, boolean isBombUsed, int cnt, boolean[][] isChecked) {
		this.x = x;
		this.y = y;
		this.isBombUsed = isBombUsed;
		this.cnt = cnt;
		this.isChecked = isChecked;
	}
}