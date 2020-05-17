import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.toList;

public class HR_StringChain {

	static int findChainLen(String word, List<String> words, HashMap<String, Integer> wordToLongestChain) {
		int cur = 0;
		for (int i = 0; i < word.length(); i++) {
			String nextWord = word.substring(0, i) + word.substring(i + 1);
			if (words.contains(nextWord)) {
				if (wordToLongestChain.containsKey(nextWord)) {
					cur = Math.max(cur, wordToLongestChain.get(nextWord));
				} else {
					int nextLen = findChainLen(nextWord, words, wordToLongestChain);
					cur = Math.max(cur, nextLen + 1);
				}
			}
		}
		return cur;
	}

	public static int longestChain(List<String> words) {
		int max = Integer.MIN_VALUE;
		HashMap<String, Integer> wordToLongestChain = new HashMap<>();

		for (String word : words) {
			if (max > word.length()) continue;
			int cur = findChainLen(word, words, wordToLongestChain) + 1;
			wordToLongestChain.put(word, cur);
			max = Math.max(max, cur);
		}
		return max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int wordsCount = Integer.parseInt(bufferedReader.readLine().trim());

		List<String> words = IntStream.range(0, wordsCount).mapToObj(i -> {
			try {
				return bufferedReader.readLine();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		})
				.collect(toList());

		int result = HR_StringChain.longestChain(words);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
