import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_9019 {
	static int T;
	static int[][] integerArr;
	static String[] ans;
	static boolean[] checked;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		integerArr = new int[T][2]; // [[첫 번째 변환 전, 첫 번째 변환 후], [두 번째 변환 전, 두 번째 변환 후], ...]
		ans = new String[T];
		for(int i = 0; i < T; i++) {
			checked = new boolean[10000];
			st = new StringTokenizer(br.readLine());
			integerArr[i][0] = Integer.parseInt(st.nextToken());
			integerArr[i][1] = Integer.parseInt(st.nextToken());

			LinkedList<DSLR> queue = new LinkedList<>();
			queue.add(new DSLR(integerArr[i][0], "", integerArr[i][1]));
			checked[integerArr[i][0]] = true;
			while(!queue.isEmpty()) {
				DSLR tmp = queue.poll();
				// D S L R을 하나씩 수행
				// D
				if(tmp.num*2%10000 == tmp.goal) {
					ans[i] = tmp.cmd+"D";
					break;
				} else {
					if(!checked[tmp.num*2%10000]) {
						checked[tmp.num*2%10000] = true;
						queue.add(new DSLR(tmp.num*2%10000, tmp.cmd+"D", tmp.goal));
					}
				}
				// S
				if((tmp.num+9999)%10000 == tmp.goal) {
					ans[i] = tmp.cmd+"S";
					break;
				} else {
					if(!checked[(tmp.num+9999)%10000]) {
						checked[(tmp.num+9999)%10000] = true;
						queue.add(new DSLR((tmp.num+9999)%10000, tmp.cmd+"S", tmp.goal));
					}
				}
				// L
				int lNum = (tmp.num%1000)*10 + tmp.num/1000;
				if(lNum == tmp.goal) {
					ans[i] = tmp.cmd+"L";
					break;
				} else {
					if (!checked[lNum]) {
						checked[lNum] = true;
						queue.add(new DSLR(lNum, tmp.cmd + "L", tmp.goal));
					}
				}
				// R
				int rNum = (tmp.num%10)*1000 + tmp.num/10;
				if(rNum == tmp.goal) {
					ans[i] = tmp.cmd+"R";
					break;
				} else {
					if (!checked[rNum]) {
						checked[rNum] = true;
						queue.add(new DSLR(rNum, tmp.cmd + "R", tmp.goal));
					}
				}
			}
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(String s: ans) bw.write(s+"\n");
		bw.flush();
		bw.close();
	}
}

class DSLR {
	int num;
	String cmd;
	int goal;

	public DSLR(int num, String cmd, int goal) {
		this.num = num;
		this.cmd = cmd;
		this.goal = goal;
	}
}
