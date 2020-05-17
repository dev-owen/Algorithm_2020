import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

public class HR_CompetitiveGaming {

	public static int numPlayers(int k, List<Integer> scores) {
		int idx = 0, tie = 1, cnt = 0;
		Collections.sort(scores);
		Collections.reverse(scores);
		int exValue = -1;
		for(int i = 0; i < scores.size(); i++) {
			if(scores.get(i) == exValue) tie++;
			else {
				idx += tie;
				tie = 1;
				exValue = scores.get(i);
			}
			if(idx <= k) cnt++;
			else break;
		}
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int k = Integer.parseInt(bufferedReader.readLine().trim());

		int scoresCount = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> scores = IntStream.range(0, scoresCount).mapToObj(i -> {
			try {
				return bufferedReader.readLine().replaceAll("\\s+$", "");
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		})
				.map(String::trim)
				.map(Integer::parseInt)
				.collect(toList());

		int result = HR_CompetitiveGaming.numPlayers(k, scores);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
