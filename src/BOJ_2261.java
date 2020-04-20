import java.io.*;
import java.util.*;

public class BOJ_2261 {
	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		List<Point_> arrayList = new ArrayList<>();

		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arrayList.add(new Point_(x, y));
		}

		Collections.sort(arrayList); // x좌표 순으로 정렬
		System.out.println(findShortest(arrayList, 0, n-1)); // arrayList의 0부터 n-1번째 값 중에 최소 거리를 찾는 메서드
	}

	static int findShortest(List<Point_> list, int start, int end) {
		int size = end - start + 1;
		if(size <= 3) { // Bruteforce로 가장 가까운 거리를 찾는다.
			return findByBruteForce(list, start, end);
		}

		// 분할 정복 (Divide and Conquer)
		int mid = (start + end) / 2;
		int left = findShortest(list, start, mid);
		int right = findShortest(list, mid, end);
		int res = Math.min(left, right); // 왼쪽에서, 오른쪽에서 각각 최소거리 중 작은 값

		// 경계선을 관통하는 두 점은 거리의 제곱이 res 보다 작은 경우만 list에 넣어준다.
		List<Point_> penetratedList = new ArrayList<>();

		for(int i = start; i <= end; i++) {
			int horDist = Math.abs(list.get(i).x - list.get(mid).x);
			if((int)Math.pow(horDist, 2) < res)
				penetratedList.add(list.get(i));
		}

		// y좌표 순서대로 정렬
		Collections.sort(penetratedList, new PointComparator());
		int listSize = penetratedList.size();
		for(int i = 0; i < listSize-1; i++) {
			for(int j = i+1; j < listSize; j++) {
				int verDist = Math.abs(list.get(j).x - list.get(i).x);
				if((int)Math.pow(verDist, 2) < res) {
					int dist = distanceSq(penetratedList.get(i), penetratedList.get(j));
					res = Math.min(res, dist);
				} else break;
			}
		}
		return res;
	}

	static int findByBruteForce(List<Point_> list, int start, int end) {
		int res = Integer.MAX_VALUE;
		for(int i = start; i < end; i++) {
			for(int j = i+1; j <= end; j++) {
				int dist = distanceSq(list.get(i), list.get(j));
				res = Math.min(dist, res);
			}
		}
		return res;
	}

	static int distanceSq(Point_ p1, Point_ p2) {
		return (int)(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
	}
}

class Point_ implements Comparable<Point_> {
	int x, y;

	Point_(int xPos, int yPos) {
		this.x = xPos;
		this.y = yPos;
	}

	@Override
	public int compareTo(Point_ p) {
		int res = this.x - p.x;
		if(res == 0) res = this.y - p.y;
		return res;
	}

}

class PointComparator implements Comparator<Point_>{ //y좌표 순으로 정렬하기 위한 Comparator
	@Override
	public int compare(Point_ p1, Point_ p2) {
		return p1.y-p2.y;
	}
}
