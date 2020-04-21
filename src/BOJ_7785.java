import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_7785 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		HashSet<String> set = new HashSet<>();
		while(N-->0) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			String status = st.nextToken();
			if(status.equals("enter")) set.add(name);
			else set.remove(name);
		}
		List<String> list = new ArrayList<>(set);
		Collections.sort(list);
		Collections.reverse(list);
		StringBuilder sb = new StringBuilder();
		for(String s: list) {
			sb.append(s + "\n");
		}
		System.out.println(sb);
	}
}
