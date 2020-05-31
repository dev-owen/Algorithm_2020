import java.io.*;

public class Kids_3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int surplus = 0;
		int max = Integer.MIN_VALUE;
		while(n-- > 0) {
			int next = Integer.parseInt(br.readLine());
			surplus += next;
			if(surplus < next) surplus = next;
			max = Math.max(max, surplus);
		}
		System.out.print(max);
	}
}
