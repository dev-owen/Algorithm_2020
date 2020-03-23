import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1202 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// 보석은 비싼 가격부터 담고(같은 가격이면 가벼운 보석), 가방은 작은 크기부터 채운다.
		PriorityQueue<Jewelry> pq = new PriorityQueue<>();
		int[] bagArr = new int[K];
		boolean[] bagUsed = new boolean[K];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int jewelryWeight = Integer.parseInt(st.nextToken());
			int jewelryPrice = Integer.parseInt(st.nextToken());
			pq.add(new Jewelry(jewelryWeight, jewelryPrice));
		}
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			bagArr[i] = Integer.parseInt(st.nextToken());

		}
		Arrays.sort(bagArr); // bagList 무게 오름차순 정렬

		long priceSum = 0L;
		while(!pq.isEmpty()) {
			Jewelry jewelry = pq.poll();
			int price = jewelry.price;
			int weight = jewelry.weight;
			for(int i = 0; i < bagArr.length; i++) {
				if(weight <= bagArr[i] && !bagUsed[i]) {
					bagUsed[i] = true;
					priceSum += price;
					break;
				}
			}
		}
		System.out.println(priceSum);
	}
}

class Jewelry implements Comparable<Jewelry>{
	int weight;
	int price;
	public Jewelry(int weight, int price) {
		this.weight = weight;
		this.price = price;
	}
	// 가격이 비쌀 수록, 같은 가격에서는 무게가 가벼울 수록
	@Override
	public int compareTo(Jewelry o) {
		if(o.price > this.price) return 1;
		else if(o.price == this.price) {
			if(o.weight < this.weight) return 1;
			else return -1;
		} else return -1;
	}
}