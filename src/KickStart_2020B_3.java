import java.util.*;
import java.io.*;

public class KickStart_2020B_3 {

	public static final long M = 1000000000;

	public static void main(String[] args) {
		Scanner s = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = s.nextInt();
		for (int ti = 1; ti <= t; ti++) {
			String str = s.next();
			Stack<Pair> dis = new Stack<>();
			Stack<Integer> fact = new Stack<>();
			Pair d = new Pair(0, 0);
			int f = 1;
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) >= '1' && str.charAt(i) <= '9') {
					dis.push(d);
					fact.push(f);

					f = str.charAt(i) - '0';
					d = new Pair(0, 0);
					i++;
				} else if (str.charAt(i) == ')') {
					d.first = (d.first * f) % M;
					d.second = (d.second * f) % M;
					Pair dtillnow = dis.pop();
					dtillnow.first = (dtillnow.first + d.first) % M;
					dtillnow.second = (dtillnow.second + d.second) % M;

					d = dtillnow;
					f = fact.pop();
				} else {
					char cc = str.charAt(i);
					if (cc == 'N') {
						d.first--;
					} else if (cc == 'S') {
						d.first++;
					} else if (cc == 'E') {
						d.second++;
					} else {
						d.second--;
					}

					d.first %= M;
					d.second %= M;
				}
			}

			d.first += 1;
			d.second += 1;

			if (d.first <= 0) {
				d.first = M + d.first;
			}
			if (d.second <= 0) {
				d.second = M + d.second;
			}

			System.out.println("Case #" + ti + ": " + d.second + " " + d.first);
		}
	}

	static class Pair implements Comparable<Pair> {
		long first;
		long second;

		Pair(long first, long second) {
			this.first = first;
			this.second = second;
		}

		public int compareTo(Pair other) {
			if (this.first < other.first)
				return -1;
			else
				return 1;
		}
	}
}