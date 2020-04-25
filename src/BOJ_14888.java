import java.io.*;
import java.util.*;

public class BOJ_14888 {
	static int N, maxSum = Integer.MIN_VALUE, minSum = Integer.MAX_VALUE, arr[];
	static String[] ariths = { "+", "-", "*", "/" };
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int[] arithArr = new int[4];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			arithArr[i] = Integer.parseInt(st.nextToken());
		}

		for(int i = 0; i < 4; i++) {
			if(arithArr[i] > 0) {
				arithArr[i]--;
				operate(1, arr[1], ariths[i], arithArr, arr[0]); // 연산할 숫자, 연산, 남은 연산 갯수, 지금까지 연산 결과
				arithArr[i]++;
			}
		}
		System.out.println(maxSum +"\n"+ minSum);
	}

	static void operate(int idx, int target, String arith, int[] arithArr, int sum) {
		int nowSum = sum;

		if (arith.equals("+")) nowSum += target;
		else if (arith.equals("-")) nowSum -= target;
		else if (arith.equals("*")) nowSum *= target;
		else nowSum /= target;

		if(idx == N-1) {
			maxSum = Math.max(maxSum, nowSum);
			minSum = Math.min(minSum, nowSum);
			return;
		}

		for(int i = 0; i < 4; i++) {
			if(arithArr[i] > 0) {
				arithArr[i]--;
				operate(idx+1, arr[idx+1], ariths[i], arithArr, nowSum);
				arithArr[i]++;
			}
		}
	}
}
