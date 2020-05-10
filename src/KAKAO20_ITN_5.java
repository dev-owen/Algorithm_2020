import java.util.*;

public class KAKAO20_ITN_5 {
	public static void main(String[] args) {
		int[][] path = {{0, 1}, {0, 3}, {0, 7}, {8, 1}, {3, 6}, {1, 2}, {4, 7}, {7, 5}};
		int[][] order = {{8, 5}, {6, 7}, {4, 1}};
		System.out.println(solution(9, path, order));
	}

	static Node rootNode;
	static LinkedList<LinkedList<Integer>> mustGo = new LinkedList<>();
	static int[] orderFirst = new int[100001], orderSecond = new int[100001];
	static boolean checked[], answer = true;
	static int N;

	static boolean solution(int n, int[][] path, int[][] order) {
		N = n;
		rootNode = new Node(0, new LinkedList<>());
		checked = new boolean[n];
		for (int i = 0; i < n; i++) {
			mustGo.add(new LinkedList<>());
		}

		for (int i = 0; i < path.length; i++) {
			int first = path[i][0];
			int second = path[i][1];
			connect(first, second, rootNode);
		}

		for (int i = 0; i < order.length; i++) {
			orderFirst[i] = order[i][0];
			orderSecond[i] = order[i][1];
			mustGo.get(orderFirst[i]).add(orderSecond[i]);
		}
		checked[0] = true;
		dfs(0);
		return answer;
	}

	static void connect(int first, int second, Node node) {
		if (first == node.root) {
			node.child.add(new Node(second, new LinkedList<>()));
			mustGo.get(first).add(second);
			return;
		} else if (second == node.root) {
			node.child.add(new Node(first, new LinkedList<>()));
			mustGo.get(second).add(first);
			return;
		}
		for (Node n : node.child) {
			connect(first, second, n);
		}
		return;
	}

	static void dfs(int node) {
		for (int i : mustGo.get(node)) {
			if (checked[i]) {
				int cnt = 0;
				for (boolean b : checked) {
					if (b) cnt++;
				}
				if (cnt < N) answer = false;
				return;
			}
			checked[i] = true;
			dfs(i);
			checked[i] = false;
		}
		return;
	}

	private static class Node {
		int root;
		LinkedList<Node> child;

		private Node(int root, LinkedList<Node> child) {
			this.root = root;
			this.child = child;
		}
	}
}
