import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2343 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] lessons = new int[N+1];
		long[] lessonsSum = new long[N+1];
		st = new StringTokenizer(br.readLine());
		long sum = 0L;
		for(int i = 1; i <= N; i++) {
			lessons[i] = Integer.parseInt(st.nextToken());
			sum += lessons[i];
			lessonsSum[i] = sum;
		}
		// 1 이상 sum 이하 숫자 중에서 블루레이의 최솟값을 찾아야 한다.
		long low = 1;
		long high = sum;
		long mid = (high+low)/2;
		long ans = 0;
		while(low <= high) {
			// 블루레이의 크기가 mid일 때 M개의 블루레이에 모든 레슨을 담을 수 있는지 확인
			int cnt = 0;
			int lastIdx = 0;
			int idx = 0;
			while(idx < N) {
				idx++;
				if(lessonsSum[idx]-lessonsSum[lastIdx] > mid) {
					cnt++;
					idx--;
					lastIdx = idx;
					if(cnt == M) { // 블루레이 하나의 용량이 너무 작다는 의미. 하나의 용량을 늘려야 한다.
						low = mid+1;
						mid = (high+low)/2;
						break;
					}
				}
			}
			// cnt가 M보다 작거나 같다는 말은 블루레이 하나의 용량이 mid 이하라는 의미. 하나의 용량을 줄여야 한다.
			if(cnt < M) {
				ans = mid;
				high = mid-1;
				mid = (high+low)/2;
			}
		}
		System.out.println(ans);
	}
}