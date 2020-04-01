import java.io.*;
import java.util.StringTokenizer;

public class BOJ_1761 {
	static int[] depth = new int[40001], parent = new int[40001], distFromRoot = new int[40001];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		// 처음 받는 노드를 루트로 잡아서 거기서부터의 길이를 계산
		st = new StringTokenizer(br.readLine());
		int rootIdx = Integer.parseInt(st.nextToken());
		int otherIdx = Integer.parseInt(st.nextToken());
		parent[otherIdx] = rootIdx;
		depth[rootIdx] = 1;
		distFromRoot[otherIdx] = Integer.parseInt(st.nextToken());
		for(int i = 1; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int firstIdx = Integer.parseInt(st.nextToken());
			int secondIdx = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			if(distFromRoot[firstIdx] > 0) {
				parent[secondIdx] = firstIdx;
				depth[secondIdx] = depth[firstIdx]+1;
				distFromRoot[secondIdx] = distFromRoot[firstIdx]+dist;
			}
			else if(distFromRoot[secondIdx] > 0) {
				parent[firstIdx] = secondIdx;
				depth[firstIdx] = depth[secondIdx]+1;
				distFromRoot[firstIdx] = distFromRoot[secondIdx]+dist;
			}
			else { // rootIdx에서 트리 생성해야 함
				if(firstIdx == rootIdx) {
					parent[secondIdx] = firstIdx;
					depth[secondIdx] = 1;
					distFromRoot[secondIdx] = dist;
				} else {
					parent[firstIdx] = secondIdx;
					depth[firstIdx] = 1;
					distFromRoot[firstIdx] = dist;
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int firstNode = Integer.parseInt(st.nextToken());
			int secondNode = Integer.parseInt(st.nextToken());
			bw.write(distFromRoot[firstNode] + distFromRoot[secondNode] - 2 * distFromRoot[lca(firstNode, secondNode)] + "\n");
		}
		bw.flush();
		bw.close();
	}

	static int lca(int firstIdx, int secondIdx) { // 두 노드의 최소 공통 노드 idx를 찾아서 반환
		int diff = depth[firstIdx] - depth[secondIdx];

		if(diff < 0) {
			while (diff != 0) {
				diff++;
				secondIdx = parent[secondIdx];
			}
		} else {
			while (diff != 0) {
				diff++;
				firstIdx = parent[firstIdx];
			}
		}
		while (firstIdx != secondIdx) {
			firstIdx = parent[firstIdx];
			secondIdx = parent[secondIdx];
		}
		return firstIdx;
	}
}