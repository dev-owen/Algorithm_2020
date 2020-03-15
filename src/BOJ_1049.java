import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1049 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int setPriceMin = 2100000000;
		int singlePriceMin = 2100000000;
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			// 6개 세트의 최소값 찾기
			setPriceMin = Math.min(setPriceMin, Integer.parseInt(st.nextToken()));
			// 낱개의 최소값 찾기
			singlePriceMin = Math.min(singlePriceMin, Integer.parseInt(st.nextToken()));
		}
		if(setPriceMin <= 6*singlePriceMin) System.out.println(Math.min((N/6)*setPriceMin+(N%6)*singlePriceMin, ((N/6)+1)*setPriceMin));
		else System.out.println(N*singlePriceMin);
	}
}