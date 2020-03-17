import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1790 {
	static int N;
	static int k;
	static int[] digitStartIndex = new int[11]; // N번째 자리가 시작되는 숫자의 idx
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		digitStartIndex[1] = 1;
		for(int i = 2; i <= 9; i++) {
			digitStartIndex[i] = digitStartIndex[i-1]+(int)(Math.pow(10,i-1)-Math.pow(10,i-2))*(i-1);
		}
		digitStartIndex[10] = 2100000000; // 에러 방
		int kDigitRange = 1;
		while(true) {
			if(k >= digitStartIndex[kDigitRange+1] && kDigitRange < 9) kDigitRange++;
			else break;
		}
		// k가 몇 번째 자리(kDigitRange) 숫자들이 정렬되어 있는 구간에 위치하는지 확인
		k -= digitStartIndex[kDigitRange];
		int frontNumber = k/kDigitRange; // k 앞에 숫자가 몇개나 있는지 확인
		frontNumber += Math.pow(10, kDigitRange-1)-1;
		if(frontNumber < N) {
			char[] answerArr = String.valueOf(frontNumber+1).toCharArray();
			System.out.println(answerArr[k%kDigitRange]);
		} else {
			System.out.println("-1");
		}
	}
}
