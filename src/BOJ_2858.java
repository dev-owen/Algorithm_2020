import java.util.Scanner;

public class BOJ_2858 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int R = sc.nextInt();
		int B = sc.nextInt();
		int sum = (R+4)/2;
		int multiple = R+B;
		int L = findL(sum, multiple);
		System.out.println(L + " " + (sum-L));
	}

	static int findL(int sum, int multiple) {
		int L = 0;
		for(int i = 1; i < sum; i++) {
			if(i * (sum - i) == multiple) {
				L =  Math.max(i, sum - i);
				break;
			}
		}
		return L;
	}
}
