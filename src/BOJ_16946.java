import java.io.*;
import java.util.*;

public class BOJ_16946 {
	static int N, M, groups[][], groupSize[];
	static boolean[][] walls;
	static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		walls = new boolean[N+2][M+2];
		groups = new int[N+2][M+2];
		for(int i = N; i > 0; i--) {
			String[] arr = br.readLine().split("");
			for(int j = 1; j <= M; j++) {
				walls[i][j] = (arr[j-1].equals("1") ? true : false);
			}
		}
		// 벽이 없는 공간 그룹화하기
		int groupIdx = 1;
		for(int i = N; i > 0; i--) {
			for(int j = 1; j <= M; j++) {
				if(!walls[i][j] && groups[i][j] == 0) {
					groupBFS(i, j, groupIdx);
					groupIdx++;
				}
			}
		}

		// 각 그룹에 속해있는 칸 수를 groupSize 배열에 넣음
		groupSize = new int[groupIdx+1];
		for(int i = N; i > 0; i--) {
			for(int j = 1; j <= M; j++) {
				if(groups[i][j] != 0) groupSize[groups[i][j]]++;
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i = N; i > 0; i--) {
			for(int j = 1; j <= M; j++) {
				sb.append(countBlock(i,j)+"");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public static int countBlock(int i, int j) {
		if(!walls[i][j]) return 0;
		else {
			Set<Integer> set = new HashSet<>();
			for(int k = 0; k < 4; k++) {
				int nextX = i + dx[k];
				int nextY = j + dy[k];
				if(nextX > 0 && nextX <= N && nextY > 0 && nextY <= M && groups[nextX][nextY] != 0) {
					set.add(groups[nextX][nextY]);
				}
			}
			int cnt = 1;
			for(int idx: set) {
				cnt += groupSize[idx];
			}
			return cnt%10;
		}
	}

	static void groupBFS(int i, int j, int idx) {

		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(i, j));
		groups[i][j] = idx;
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			for(int k = 0; k < 4; k++) {
				int nextX = p.x + dx[k];
				int nextY = p.y + dy[k];
				if(nextX > 0 && nextX <= N && nextY > 0 && nextY <= M && groups[nextX][nextY] == 0 && !walls[nextX][nextY]) {
					queue.add(new Point(nextX, nextY));
					groups[nextX][nextY] = idx;
				}
			}
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
