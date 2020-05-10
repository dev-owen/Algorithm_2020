import java.util.*;

public class KAKAO20_ITN_4 {

	static int[][] boardCopy;
	static boolean[][] checked;
	static int[][] finished;
	static int N, cost = Integer.MAX_VALUE;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};

	public static void main(String[] args) {
		int[][] arr = {{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}};
		System.out.println(solution(arr));
	}

	static int solution(int[][] board) {
		N = board.length;
		boardCopy = board;
		LinkedList<P> list = new LinkedList<>();
		finished = new int[N][N];
		checked = new boolean[N][N];
		checked[0][0] = true;
		dfs(0,0,0,0, list);
		return cost;
	}

	static void dfs(int x, int y, int presentCost, int dir, LinkedList<P> list) {
		list.add(new P(x, y, presentCost));
		if(x == N-1 && y == N-1) {
			if(presentCost < cost) {
				cost = presentCost;
				finished = new int[N][N];
				for(P p : list) {
					finished[p.x][p.y] = p.cost;
				}
			} else {
				for(P p : list) {
					if(finished[p.x][p.y] == 0) finished[p.x][p.y] = p.cost;
				}
			}
			return;
		}
		if(x == 0 && y == 0) {
			if(boardCopy[0][1] == 0) {
				checked[0][1] = true;
				dfs(0,1,100,0, list);
				checked[0][1] = false;
			}
			if(boardCopy[1][0] == 0) {
				checked[1][0] = true;
				dfs(1,0,100,1, list);
				checked[1][0] = false;
			}
		}
		int nx, ny;
		for(int i = 0; i < 4; i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			if(nx >= 0 && nx < N && ny >= 0 && ny < N) {
				if(boardCopy[nx][ny] == 0 && !checked[nx][ny]) {
					checked[nx][ny] = true;
					if(i == dir || Math.abs(i - dir) == 2) {
						if(finished[nx][ny] == 0 || finished[nx][ny] >= presentCost + 100) dfs(nx, ny, presentCost + 100, i, list);
					} else {
						if(finished[nx][ny] == 0 || finished[nx][ny] >= presentCost + 600) dfs(nx, ny, presentCost + 600, i, list);
					}
					checked[nx][ny] = false;
				}
			}
		}
	}
}

class P {
	int x, y, cost;
	P(int x, int y, int cost) {
		this.x = x;
		this.y = y;
		this.cost = cost;
	}
}