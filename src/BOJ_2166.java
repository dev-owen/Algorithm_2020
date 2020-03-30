import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2166 {
	static long[][] points;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		points = new long[N][2]; // points[i][0] : i번째 점의 x좌표, points[i][1] : i번째 점의 y좌표
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			points[i][0] = Long.parseLong(st.nextToken());
			points[i][1] = Long.parseLong(st.nextToken());
		}
		long sumArea = 0;
		for(int i = 1; i < N-1; i++) {
			sumArea += ccw(i);
		}
		System.out.println(sumArea%2 == 0 ? Math.abs(sumArea)/2 + ".0" : Math.abs(sumArea)/2 + ".5");
	}
	static long ccw(int idx) { // 0, idx, idx+1 세 개의 점을 더
		return points[0][0] * points[idx][1] + points[idx][0] * points[idx+1][1] + points[idx+1][0] * points[0][1] - (points[0][1] * points[idx][0] + points[idx][1] * points[idx+1][0] + points[idx+1][1] * points[0][0]);
	}
}