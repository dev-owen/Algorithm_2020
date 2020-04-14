import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_11438 {
	static HashMap<Integer, ArrayList<Integer>> treeMap = new HashMap<>();
	static int[] depth; // 해당 노드의 깊이
	static int[][] parent; // k = parent[a][b] 인 경우 a의 2^b 번째 조상이 k이다.

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		depth = new int[N+1];
		parent = new int[N+1][18];
		treeMap.put(1, new ArrayList<>());
		depth[1] = 0;

		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int firstNode = Integer.parseInt(st.nextToken());
			int secondNode = Integer.parseInt(st.nextToken());
			if(!treeMap.containsKey(firstNode)) {
				treeUpdate(secondNode, firstNode);
			}
			else if(!treeMap.containsKey(secondNode)) {
				treeUpdate(firstNode, secondNode);
			}
		}

		for(int i = 1; i <= 17; i++) { // 나머지 parent 채운다.
			for(int j = 1; j <= N; j++) {
				parent[j][i] = parent[parent[j][i-1]][i-1];
			}
		}

		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int firstNode = Integer.parseInt(st.nextToken());
			int secondNode = Integer.parseInt(st.nextToken());
			sb.append(lca(firstNode, secondNode) + "\n");
		}
		System.out.println(sb);
		br.close();
	}

	static void treeUpdate(int parentNode, int childNode) {
		treeMap.put(childNode, new ArrayList<>());
		depth[childNode] = depth[parentNode]+1;
		parent[childNode][0] = parentNode;
		ArrayList<Integer> tmp = treeMap.get(parentNode);
		tmp.add(childNode);
		treeMap.replace(parentNode, tmp);
	}


	static int lca(int first, int second) {
		int low, high;

		if(depth[first] > depth[second]) {
			low = first;
			high = second;
		} else {
			low = second;
			high = first;
		}

		for(int i = 17; i >= 0; i--) {
			int diff = depth[low] - depth[high];
			if(Math.pow(2, i) <= diff) {
				low = parent[low][i]; // low를 위로 끌어올린다.
			}
		}

		if( low == high ) return high;

		// 이제 같은 선(depth) 상에 있으므로 조상을 하나씩 찾아 나간다.
		for(int i = 17; i >= 0; i--) {
			if(parent[low][i] != parent[high][i]) {
				low = parent[low][i];
				high = parent[high][i];
			}
		}

		high = parent[high][0];

		return high;
	}
}
