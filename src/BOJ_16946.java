import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16946 {
	static int N, M;
	static boolean[][] walls, visited;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		walls = new boolean[N+2][M+2];
		for(int i = N; i > 0; i--) {
			String[] arr = br.readLine().split("");
			for(int j = 1; j <= arr.length; j++) {
				walls[i][j] = (arr[j-1].equals("1") ? true : false);
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = N; i > 0; i--) {
			for(int j = 1; j <= M; j++) {
				sb.append(search(i,j)+"");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public static int search(int i, int j) {
		if(!walls[i][j]) return 0;
		else {
			int cnt = 0;
			Queue<Point> queue = new LinkedList<>();
			visited = new boolean[N+2][M+2];
			queue.add(new Point(i, j));
			visited[i][j] = true;
			while(!queue.isEmpty()) {
				Point p = queue.poll();
				cnt++;
				int nextX, nextY;
				for(int k = 0; k < 4; k++) {
					nextX = p.x + dx[k];
					nextY = p.y + dy[k];
					if(nextX > 0 && nextX <= N && nextY > 0 && nextY <= M && !visited[nextX][nextY] && !walls[nextX][nextY]) {
						queue.add(new Point(nextX, nextY));
						visited[nextX][nextY] = true;
					}
				}
			}
			return cnt;
		}
	}
}

class Point {
	int x, y;
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
