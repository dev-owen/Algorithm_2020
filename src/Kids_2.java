import java.util.*;

public class Kids_2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ArrayList<Character> list = new ArrayList<>();
		HashSet<Character> set = new HashSet<>();
		String str = in.nextLine();
		char[] chArr = str.toCharArray();

		for(char c : chArr) {
			if(list.contains(c)) set.add(c);
			else list.add(c);
		}

		for(char c : set) {
			list.remove(Character.valueOf(c));
		}

		if(list.size() == 0) System.out.println("-");
		else System.out.println(list.get(0) + " " + list.get(list.size()-1));
	}
}
