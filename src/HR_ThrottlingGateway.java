import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

public class HR_ThrottlingGateway {

	public static int droppedRequests(List<Integer> requestTime) {
		HashMap<Integer, Integer> map = new HashMap<>();
		LinkedList<Integer> tenQueue = new LinkedList<>();
		LinkedList<Integer> sixtyQueue = new LinkedList<>();
		HashMap<Integer, Integer> drop = new HashMap<>();

		for (int i : requestTime) {
			if (!map.containsKey(i)) {
				map.put(i, 1);
				if (i <= 10) tenQueue.add(i);
				if (i <= 60) sixtyQueue.add(i);
			} else map.replace(i, map.get(i) + 1);
		}

		Iterator<Integer> iter = map.keySet().iterator();

		while (iter.hasNext()) {
			int key = iter.next();
			if (map.get(key) > 3) drop.put(key, map.get(key) - 3);
		}

		int tenSecReqCnt = 0;

		for (int i : tenQueue) {
			if (!drop.containsKey(i)) drop.put(i, 0);
			if (tenSecReqCnt + map.get(i) <= 20) tenSecReqCnt += map.get(i);
			else {
				if (tenSecReqCnt >= 20) drop.put(i, Math.max(drop.get(i), map.get(i)));
				else drop.put(i, Math.max(drop.get(i), tenSecReqCnt + map.get(i) - 20));
				tenSecReqCnt += map.get(i);
			}
		}

		iter = map.keySet().iterator();
		while (iter.hasNext()) {
			int key = iter.next();
			if (tenQueue.contains(key)) continue;
			else {
				while (!tenQueue.isEmpty()) {
					int passed = tenQueue.peek();
					if (key - passed >= 10) {
						tenQueue.poll();
						tenSecReqCnt -= map.get(passed);
					} else break;
				}
				if (!drop.containsKey(key)) drop.put(key, 0);
				if (tenSecReqCnt + map.get(key) <= 20) tenSecReqCnt += map.get(key);
				else {
					if (tenSecReqCnt >= 20) drop.put(key, Math.max(drop.get(key), map.get(key)));
					else drop.put(key, Math.max(drop.get(key), tenSecReqCnt + map.get(key) - 20));
					tenSecReqCnt += map.get(key);
				}
			}
		}

		int sixtySecReqCnt = 0;

		for (int i : sixtyQueue) {
			if (!drop.containsKey(i)) drop.put(i, 0);
			if (sixtySecReqCnt + map.get(i) <= 60) sixtySecReqCnt += map.get(i);
			else {
				if (sixtySecReqCnt >= 60) drop.put(i, Math.max(drop.get(i), map.get(i)));
				else drop.put(i, Math.max(drop.get(i), sixtySecReqCnt + map.get(i) - 60));
				sixtySecReqCnt += map.get(i);
			}
		}

		iter = map.keySet().iterator();
		while (iter.hasNext()) {
			int key = iter.next();
			if (sixtyQueue.contains(key)) continue;
			else {
				while (!sixtyQueue.isEmpty()) {
					int passed = sixtyQueue.peek();
					if (key - passed >= 60) {
						sixtyQueue.poll();
						sixtySecReqCnt -= map.get(passed);
					} else break;
				}
				if (!drop.containsKey(key)) drop.put(key, 0);
				if (sixtySecReqCnt + map.get(key) <= 60) sixtySecReqCnt += map.get(key);
				else {
					if (sixtySecReqCnt >= 60) drop.put(key, Math.max(drop.get(key), map.get(key)));
					else drop.put(key, Math.max(drop.get(key), sixtySecReqCnt + map.get(key) - 20));
					sixtySecReqCnt += map.get(key);
				}
			}
		}

		Iterator<Integer> dropIter = drop.keySet().iterator();
		int dropCnt = 0;
		while (dropIter.hasNext()) {
			int dropKey = dropIter.next();
			dropCnt += drop.get(dropKey);
		}
		return dropCnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int requestTimeCount = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> requestTime = IntStream.range(0, requestTimeCount).mapToObj(i -> {
			try {
				return bufferedReader.readLine().replaceAll("\\s+$", "");
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		})
				.map(String::trim)
				.map(Integer::parseInt)
				.collect(toList());

		int result = HR_ThrottlingGateway.droppedRequests(requestTime);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
