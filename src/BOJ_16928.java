import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_16928 {
	static int[] move = new int[101];
	static int[] minStep = new int[101];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		for(int i = 0; i < N+M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			move[start] = end;
		}
		for(int i = 2; i <= 100; i++) {
			minStep[i] = 210000000;
		}
		LinkedList<Obj> queue = new LinkedList<>();
		queue.add(new Obj(1,0));
		while(!queue.isEmpty()) {
			Obj obj = queue.poll();
			int nowPos = obj.pos;
			int nowCnt = obj.cnt;
			// 사다리나 뱀이 있는 경우
			if(move[nowPos] != 0) {
				minStep[move[nowPos]] = nowCnt;
				queue.add(new Obj(move[nowPos], nowCnt));
				continue; // 이 한 줄 때문에 1시간 잡아먹었다...
			}
			for(int i = 1; i <= 6; i++) {
				if(nowPos+i <= 100) {
					if(nowCnt+1 < minStep[nowPos+i]) {
						minStep[nowPos+i] = nowCnt+1;
						queue.add(new Obj(nowPos+i, nowCnt+1));
					}
				}
			}
		}
		System.out.println(minStep[100]);
	}
}

class Obj {
	int pos;
	int cnt;
	public Obj(int pos, int cnt) {
		this.pos = pos;
		this.cnt = cnt;
	}
}
