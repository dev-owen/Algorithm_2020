import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class BOJ_12886 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strArr = br.readLine().split(" ");
		LinkedList<Integer[]> queue = new LinkedList<>();
		Integer[] arr = new Integer[3];
		for(int i = 0; i < 3; i++) {
			arr[i] = Integer.parseInt(strArr[i]);
		}
		queue.add(arr);
		int total = arr[0] + arr[1] + arr[2];
		boolean[][] checked = new boolean[total][total]; // 첫번째 작은 수, 두 번째 작은 수가 나온 적이 있는지
		if(total%3 != 0) {
			System.out.println(0);
			return;
		}
		boolean flag = false;
		while(!queue.isEmpty()) {
			Integer[] tmp = queue.poll();
			Arrays.sort(tmp);
			int small = tmp[0];
			int medium = tmp[1];
			int large = tmp[2];
			if(small == medium && medium == large) {
				flag = true;
				break;
			}
			if(checked[small][medium]) continue;
			else {
				Integer[] tmp2 = new Integer[3];
				checked[small][medium] = true;
				tmp2[0] = small*2;
				tmp2[1] = medium;
				tmp2[2] = large-small;
				queue.add(tmp2);

				if(small < medium) {
					Integer[] tmp3 = new Integer[3];
					tmp3[0] = small*2;
					tmp3[1] = medium-small;
					tmp3[2] = large;
					queue.add(tmp3);
				}

				if(medium < large) {
					Integer[] tmp4 = new Integer[3];
					tmp4[0] = small;
					tmp4[1] = medium*2;
					tmp4[2] = large-medium;
					queue.add(tmp4);
				}
			}
		}
		System.out.println(flag ? 1 : 0);
	}
}