import java.io.*;
import java.util.*;

public class BOJ_9372 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		while (T-->0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			ArrayList<ArrayList<Integer>> flight = new ArrayList<>();
			for(int i = 0; i <= N; i++) {
				flight.add(new ArrayList<>());
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int first = Integer.parseInt(st.nextToken());
				int second = Integer.parseInt(st.nextToken());
				flight.get(first).add(second);
				flight.get(second).add(first);
			}
			boolean[] checked = new boolean[N+1];
			Queue<Integer> queue = new LinkedList<>();
			queue.add(1);
			checked[1] = true;
			int cnt = 0;
			while(!queue.isEmpty()) {
				int state = queue.poll();
				for(int i = 0; i < flight.get(state).size(); i++) {
					if(!checked[flight.get(state).get(i)]) {
						queue.add(flight.get(state).get(i));
						checked[flight.get(state).get(i)] = true;
						cnt++;
					}
				}
			}
			System.out.println(cnt);
		}
	}
}
