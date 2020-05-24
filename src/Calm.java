import java.util.*;

class Calm {
	static ArrayList<int[]> sumZeroTriplets(int arr[], int n) {
		ArrayList<int[]> answer = new ArrayList<>();
		for (int i = 0; i < n - 1; i++) {
			HashSet<Integer> s = new HashSet<Integer>();
			for (int j = i + 1; j < n; j++) {
				int x = -(arr[i] + arr[j]);
				if (s.contains(x)) {
					int[] triplet = {x, arr[i], arr[j]};
					Arrays.sort(triplet);
					boolean flag = true;
					for(int[] trip : answer) {
						if(trip[0] == triplet[0] && trip[1] == triplet[1] && trip[2] == triplet[2]) {
							flag = false;
							break;
						}
					}
					if(flag) answer.add(triplet);
				} else {
					s.add(arr[j]);
				}
			}
		}
		return answer;
	}

	public static void main(String[] args) {
		int arr[] = {-1, 0, 1, 2, -1, -4};
		int n = arr.length;
		System.out.println(sumZeroTriplets(arr, n));
	}
} 