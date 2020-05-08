public class KAKAO19_ITN_5 {
	public static void main(String[] args) {
		int[] arr = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
		System.out.println(solution(arr, 3));
	}
	static int K, stoneArr[];
	static int solution(int[] stones, int k) {
		stoneArr = stones;
		K = k;
		int high = 200000001, low = 1, mid = (high+low)/2;

		while(high >= low) {
			if(biSearch(mid)) low = mid+1;
			else high = mid-1;
			mid = (high+low)/2;
		}

		return low;
	}

	static boolean biSearch(int num) {
		int cnt = 0;
		for(int i = 0; i < stoneArr.length; i++) {
			if(stoneArr[i] > num) cnt = 0;
			else cnt++;
			if(cnt >= K) return false;
		}
		return true;
	}

}
