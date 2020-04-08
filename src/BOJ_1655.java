import java.io.*;
import java.util.PriorityQueue;

public class BOJ_1655 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
		PriorityQueue<Integer> minHeap = new PriorityQueue<>((o1, o2) -> o1 - o2);

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			if(i%2 == 0) { // maxHeap에 숫자 추가
				maxHeap.add(num);
			} else { // minHeap에 숫자 추가
				minHeap.add(num);
			}

			if(!maxHeap.isEmpty() && !minHeap.isEmpty()) {
				if(maxHeap.peek() > minHeap.peek()) { // swap
					int tmp = maxHeap.poll();
					maxHeap.add(minHeap.poll());
					minHeap.add(tmp);
				}
			}

			bw.write(maxHeap.peek() + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
