import java.util.*;

class Calm {
	static ArrayList<int[]> sumZeroTriplets(int arr[], int n) {
		ArrayList<int[]> aryLst = new ArrayList<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i : arr) {
			if (map.containsKey(i)) map.replace(i, map.get(i) + 1);
			else map.put(i, 1);
		}
		if (map.containsKey(0)) {
			if (map.get(0) == 3) {
				int[] ansArr = {0, 0, 0};
				aryLst.add(ansArr);
			}
		}
		Iterator<Integer> keys = map.keySet().iterator();
		ArrayList<Integer> singleArr = new ArrayList<>();

		while (keys.hasNext()) {
			Integer key = keys.next();
			if (map.get(key) == 2) {
				if (map.containsKey((-2) * key)) {
					int[] ansArr = {key, key, (-2) * key};
					aryLst.add(ansArr);
				}
			}
			singleArr.add(key);
		}
		int len = singleArr.size();
		Collections.sort(singleArr);

		for (int i = 0; i < len - 1; i++) {
			int l = i + 1;
			int r = len - 1;
			int x = singleArr.get(i);
			while (l < r) {
				if (x + singleArr.get(l) + singleArr.get(r) == 0) {
					int[] ans = new int[3];
					ans[0] = x;
					ans[1] = singleArr.get(l);
					ans[2] = singleArr.get(r);
					aryLst.add(ans);

					l++;
					r--;
				} else if (x + singleArr.get(l) + singleArr.get(r) < 0) l++;
				else r--;
			}
		}
		return aryLst;
	}

	public static void main(String[] args) {
		int arr[] = {-1, 0, 1, 2, -1, -4};
		int n = arr.length;
		System.out.println(sumZeroTriplets(arr, n));
	}
} 