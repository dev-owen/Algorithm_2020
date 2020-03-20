import java.io.*;
import java.util.Stack;

public class BOJ_9935 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] firstCharArr = br.readLine().toCharArray();
		char[] secondCharArr = br.readLine().toCharArray();
		int bombSize = secondCharArr.length;
		Stack<Character> answerStack = new Stack<>();
		Stack<Integer> bombStack = new Stack<>(); // bomb의 첫 단어에 대한 answerStack idx만 넣는다.
		char bombFirst = secondCharArr[0];
		char bombLast = secondCharArr[bombSize-1];
		for(int i = 0; i < firstCharArr.length; i++) {
			// 한 글자씩 answerStack에 넣는다.
			answerStack.push(firstCharArr[i]);
			if(firstCharArr[i] == bombFirst) bombStack.push(answerStack.size()-1);
			else if(firstCharArr[i] == bombLast) {
				int latestBombFirstIdx = bombStack.peek();
				boolean flag = true;
				for(int j = 0; j < bombSize; j++) {
					if(answerStack.elementAt(latestBombFirstIdx+j) != secondCharArr[j]) {
						flag = false;
						break;
					}
				}
				if(flag) {
					bombStack.pop();
					for(int j = 0; j < bombSize; j++) answerStack.pop();
				}
			}
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String ans = "";
		while(!answerStack.isEmpty()) {
			ans = answerStack.pop() + ans;
		}
		bw.write(ans == "" ? "FRULA" : ans);
		bw.flush();
		bw.close();
	}
}
