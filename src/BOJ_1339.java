import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

public class BOJ_1339 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		HashMap<Character, Integer> map = new HashMap<>();
		PriorityQueue<AlphabetNumber> pq = new PriorityQueue<>();
		while(N-->0) {
			char[] wordArr = br.readLine().toCharArray();
			for(int i = wordArr.length-1; i >= 0; i--) {
				if(map.containsKey(wordArr[i])) { // 이미 해당 알파벳이 들어가 있으면 값을 더해서 다시 put
					map.replace(wordArr[i], map.get(wordArr[i])+(int)Math.pow(10, wordArr.length-i-1));
				} else {
					map.put(wordArr[i], (int)Math.pow(10, wordArr.length-i-1));
				}
			}
		}
		// HashMap의 K, V들을 ProrityQueue에 넣어준다
		Iterator<Character> keys = map.keySet().iterator();
		while(keys.hasNext()) {
			Character key = keys.next();
			pq.add(new AlphabetNumber(key, map.get(key)));
		}
		int maxSum = 0;
		int idx = 9;
		while(!pq.isEmpty()) {
			AlphabetNumber an = pq.poll();
			maxSum += an.number*idx;
			idx--;
		}
		System.out.println(maxSum);
	}
}

class AlphabetNumber implements Comparable<AlphabetNumber> {
	char alphabet;
	int number;
	public AlphabetNumber(char alphabet, int number) {
		this.alphabet = alphabet;
		this.number = number;
	}

	@Override
	public int compareTo(AlphabetNumber target) { // 숫자가 클수록 우선순위가 높다
		if(this.number <= target.number) return 1;
		else return -1;
	}
}
