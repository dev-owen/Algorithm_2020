import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_13576 {
	static int[] pi;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = br.readLine();
		int n = inputStr.length();
		pi = new int[n];
		int j = 0;
		for(int i = 1; i < n; i++) {
			while(j > 0 && inputStr.charAt(i) != inputStr.charAt(j)) {
				j = pi[j - 1];
			}
			if(inputStr.charAt(i) == inputStr.charAt(j)) pi[i] = ++j;
		}
		ArrayList<Integer> list = new ArrayList<>();
		list.add(n);
		int idx = n-1;
		while(idx > 0) {
			idx = pi[idx];
			if(idx > 0) list.add(idx);
			idx--;
		}
		Collections.sort(list);

		StringBuilder sb = new StringBuilder();
		sb.append(list.size() + "\n");
		// 전체 문자열에서 부분 문자열의 갯수를 구하는 메서드
		for(int i : list) {
			sb.append(i + " " + kmp(inputStr, inputStr.substring(0,i))+ "\n");
		}
		System.out.println(sb);
	}

	static int kmp(String s, String p) {
		int n = s.length(), m = p.length(), j = 0;
		ArrayList<Integer> list = new ArrayList<>();

		for (int i = 0 ; i < n ; i++) {
			while (j > 0 && s.charAt(i) != p.charAt(j)) {
				j = pi[j-1];
			}
			if (s.charAt(i) == p.charAt(j)) {
				if (j == m-1) {
					list.add(i-m+1);
					j = pi[j];
				}
				else j++;
			}
		}
		return list.size();
	}
}