import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BOJ_9250 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Trie trie = new Trie(null);
		while(N-->0) {
			String[] str = br.readLine().split("");
			for(String s : str) {
				// ???
			}

		}
	}
}

class TrieNode {
	private String content;
	private HashMap<Character, TrieNode> children;
	private boolean isWordEnd;
	TrieNode(String content, HashMap children, boolean isWordEnd) {
		this.content = content;
		this.children = children;
		this.isWordEnd = isWordEnd;
	}
}

class Trie {
	private TrieNode root;

	Trie(TrieNode root) {
		this.root = root;
	}
}
// 참고 : https://www.baeldung.com/trie-java
