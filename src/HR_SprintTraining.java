import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.toList;

public class HR_SprintTraining {
	public static int getMostVisited(int n, List<Integer> sprints) {
		int[] increment = new int[n+2];
		for(int i = 0; i < sprints.size()-1; i++) {
			int min = Math.min(sprints.get(i), sprints.get(i+1));
			int max = Math.max(sprints.get(i), sprints.get(i+1));
			increment[min]++;
			increment[max+1]--;
		}
		int[] scores = new int[n+1];
		int score = 0;
		for(int i = 1; i <= n; i++) {
			score += increment[i];
			scores[i] = score;
		}
		int maxValue = -1, idx = 0;
		for(int i = 1; i <= n; i++) {
			if(scores[i] > maxValue) {
				maxValue = scores[i];
				idx = i;
			}
		}
		return idx;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int n = Integer.parseInt(bufferedReader.readLine().trim());

		int sprintsCount = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> sprints = IntStream.range(0, sprintsCount).mapToObj(i -> {
			try {
				return bufferedReader.readLine().replaceAll("\\s+$", "");
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		})
				.map(String::trim)
				.map(Integer::parseInt)
				.collect(toList());

		int result = HR_SprintTraining.getMostVisited(n, sprints);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
