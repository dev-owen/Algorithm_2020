import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_11724 {
	static int N, M;
	static boolean[] checked;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		checked = new boolean[N+1];
		ArrayList<LinkedList<Integer>> list = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			list.add(new LinkedList<>());
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			list.get(first).add(second);
			list.get(second).add(first);
		}
		int idx = 0;
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 1; i <= N; i++) {
			if(!checked[i]) {
				queue = new LinkedList<>();
				queue.add(i);
				checked[i] = true;
				while (!queue.isEmpty()) {
					int edge = queue.poll();
					for(int num : list.get(edge)) {
						if(!checked[num]) {
							queue.add(num);
							checked[num] = true;
						}
					}
				}
				idx++;
			}
		}
		System.out.println(idx);
	}
}
