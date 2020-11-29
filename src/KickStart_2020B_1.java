import java.io.*;
import java.util.*;

public class KickStart_2020B_1 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T =  Integer.parseInt(st.nextToken());
		for(int i = 1; i <= T; i++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] arr = new int[N];
			int cnt = 0;
			for(int j = 0; j < N; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			for(int j = 1; j < N-1; j++) {
				if(arr[j-1] < arr[j] && arr[j] > arr[j+1]) cnt++;
			}
			System.out.println("Case #"+i+": "+cnt);
		}
	}
}
