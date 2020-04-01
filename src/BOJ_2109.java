import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2109 {
	static int n;
	static boolean[] checked;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		PriorityQueue<Lecture> pq = new PriorityQueue<>();
		int maxDay = 0;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int pay = Integer.parseInt(st.nextToken());
			int day = Integer.parseInt(st.nextToken());
			maxDay = Math.max(day, maxDay);
			pq.add(new Lecture(pay, day));
		}
		checked = new boolean[maxDay+1];
		int sum = 0;
		while(!pq.isEmpty()) {
			Lecture lecture = pq.poll();
			for(int i = lecture.day; i >= 1; i--) {
				if(!checked[i]) {
					checked[i] = true;
					sum += lecture.pay;
					break;
				}
			}
		}
		System.out.println(sum);
	}
}

class Lecture implements Comparable<Lecture> {
	int pay;
	int day;
	public Lecture(int pay, int day) {
		this.pay = pay;
		this.day = day;
	}

	@Override
	public int compareTo(Lecture target) {
		if(target.pay > this.pay) { // pay가 높은게 우선순위가 높다
			return 1;
		} else if(target.pay == this.pay) {
			if(target.day < this.day) { // 같은 pay면 day가 낮은게 우선순위가 높다.
				return 1;
			}
		}
		return -1;
	}
}