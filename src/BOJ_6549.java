import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6549 {
	static int arr[], n;
	static long maxSize;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(true) {
			n = Integer.parseInt(st.nextToken());
			if(n == 0) break;
			arr = new int[n];
			maxSize = 0;
			for(int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			calculate(0,n);
			System.out.println(maxSize);
			st = new StringTokenizer(br.readLine());
		}
	}

	static void calculate(int start, int end) {
		int minHeight = arr[start];
		int minHeightIdx = start;
		for(int i = start+1; i < end; i++) {
			if(arr[i] < minHeight) {
				minHeight = arr[i];
				minHeightIdx = i;
			}
		}
		maxSize = Math.max(maxSize, (long)minHeight*(end-start));
		if(minHeightIdx > start) calculate(start, minHeightIdx);
		if(minHeightIdx < end-1) calculate(minHeightIdx+1, end);
	}
}