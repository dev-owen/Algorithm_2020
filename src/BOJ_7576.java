import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576 {
	static int M, N, MAX;
	static Queue<Node> q;
	static int[][] arr;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // 가로 길이
		N = Integer.parseInt(st.nextToken()); // 세로 길이

		arr = new int[N][M];
		for(int i=0; i<N;i++) {
			String[] str = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
		BFS(arr, N, M);
	}

	public static void BFS(int[][] arr, int N, int M) {
		q = new LinkedList<Node>();

		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j] == 1) q.add(new Node(i,j));
			}
		}

		while(!q.isEmpty()) {
			Node node = q.poll();
			for(int i=0; i<4; i++) {
				int nextX = node.x + dx[i];
				int nextY = node.y + dy[i];

				if(nextX <0 || nextX >=N || nextY <0 || nextY >=M) continue;
				if(arr[nextX][nextY] !=0) continue;

				// 처음에 1이 표시된 토마토에 인접해 있는 토마토는 2값을 저장 -> 하루 지나서 숙성된다는 의미
				arr[nextX][nextY] = arr[node.x][node.y]+1;
				q.add(new Node(nextX,nextY));
			}
		}
		MAX = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j] == 0) {
					System.out.println(-1);
					return;
				}
				MAX = Math.max(MAX, arr[i][j]);
			}
		}
		// arr 배열에서 가장 큰 값이 숙성되는데 가장 오래 걸린 토마토에 해당하므로(MAX) 그 값에서 1을 빼주면 답
		System.out.println(MAX-1);
	}
}

class Node {
	int x;
	int y;

	Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}