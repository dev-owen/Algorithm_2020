import java.io.*;
import java.util.*;

public class BOJ_1202 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Jewelry[] jewArr = new Jewelry[N];
		int[] bagArr = new int[K];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			jewArr[i] = new Jewelry(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		for (int i = 0; i < K; i++) {
			bagArr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(jewArr); // jewArr 무게 오름차순 정렬
		Arrays.sort(bagArr); // bagArr 무게 오름차순 정렬

		PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> Integer.compare(y, x));
		long priceSum = 0L;
		int idx = 0;
		for (int i = 0; i < K; i++) {
			while (idx < N && jewArr[idx].weight <= bagArr[i]) {
				pq.add(jewArr[idx].price);
				idx++;
			}
			if (!pq.isEmpty()) priceSum += pq.poll();
		}
		System.out.println(priceSum);
	}

	static class Jewelry implements Comparable<Jewelry> {
		int weight;
		int price;

		private Jewelry(int weight, int price) {
			this.weight = weight;
			this.price = price;
		}

		@Override
		public int compareTo(Jewelry o) {
			return this.weight - o.weight;
		}
	}
}

