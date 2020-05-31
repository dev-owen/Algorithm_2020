import java.util.HashSet;
import java.util.Scanner;

public class Kids_5 {
	static HashSet<Integer> set = new HashSet<>();
	static int cntTriplets = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		pythagoreanTriplets(n);
		System.out.println(cntTriplets + " " + (n - set.size()));

	}

	static void pythagoreanTriplets(int limit) {

		int a, b, c = 0;
		int m = 2;
		while (m < limit) {
			for (int n = 1; n < m; ++n) {
				a = m * m - n * n;
				b = 2 * m * n;
				c = m * m + n * n;

				if (c > limit) break;

				set.add(a);
				set.add(b);
				set.add(c);
				// a,b,c가 서로소면 cnt++, 크기는 무조건 a <= b <= c
				System.out.println(Math.min(a, b) + " " + Math.max(a, b) + " " + c + " " + isPrime(Math.min(a, b), Math.max(a, b), c));
				if (isPrime(Math.min(a, b), Math.max(a, b), c)) cntTriplets++;
			}
			m++;
		}
	}

	static boolean isPrime(int a, int b, int c) {
		if (a == b && b == c) {
			if (a != 1) return false;
			else return true;
		}
		if (b % a != 0 && c % a != 0) return isPrime(Math.min(b % a, c % a), Math.max(b % a, c % a), a);
		else if (b % a != 0 && c % a == 0) return isPrime(b % a, a, a);
		else if (c % a != 0 && b % a == 0) return isPrime(c % a, a, a);
		else return isPrime(a, a, a);
	}
}
