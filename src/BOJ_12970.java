import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12970 {
	static int N, K;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		System.out.println(ab() != "" ? ab() : "-1");
	}

	static String ab() {
		int bNum = N;
		String str = "";
		for(int i = 0; i < N; i++) {
			str += "B";
		}
		if(bNum*(N-bNum) >= K) return str;
		else {
			StringBuilder sb = new StringBuilder(str);
			for(int i = 1; i <= N/2; i++) {
				bNum--;
				if(bNum*(N-bNum) >= K) { // A, B 갯수는 여기서 확정 A는 (N-bNum)개, B는 bNum개
					for(int j = 0; j < N-bNum; j++) {
						sb.setCharAt(j,'A');
					} // 0 ... (N-bNum-1) 까지 A, N-bNum ... N-1 까지 B
					int change = bNum*(N-bNum) - K; // 이만큼 가장 오른쪽 A가 오른쪽으로 움직여야 함
					if(change > 0) {
						sb.setCharAt(N-bNum-1+change, 'A');
						sb.setCharAt(N-bNum-1, 'B');
					}
					return sb.toString();
				}
			}
			if(N/2*(N - N/2) < K) return "";
		}
		return str;
	}
}
