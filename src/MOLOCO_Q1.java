public class MOLOCO_Q1 {
	public static void main(String[] args) {
		System.out.println(equalsWhenOneCharRemoved("xxy", "xy"));
		System.out.println(equalsWhenOneCharRemoved("xxy", "xyy"));
		System.out.println(equalsWhenOneCharRemoved("xx", "x"));
		System.out.println(equalsWhenOneCharRemoved("xxeeeeeeeeeeeeee", "xeeeeeeeeeeeeee"));
		System.out.println(equalsWhenOneCharRemoved("asfbsddhgdhsdfsdfds", "asfbsddkhgdhsdfsdfds"));
		System.out.println(equalsWhenOneCharRemoved("asdfgdfhdghjdfjdfgfgsdd", "akkfgdfhdghjdfjdfgfsdd"));
	}

	static boolean equalsWhenOneCharRemoved(String x, String y) {
		char[] xArr = x.toCharArray();
		char[] yArr = y.toCharArray();
		if (Math.abs(xArr.length - yArr.length) != 1) return false;

		boolean flag = true;
		int idx = 0, i = 0;
		if (xArr.length > yArr.length) {
			while (i < yArr.length && flag) {
				if (xArr[i + idx] == yArr[i]) i++;
				else {
					idx++;
					if (idx > 1) flag = false;
				}
			}
		} else {
			while (i < xArr.length && flag) {
				if (yArr[i + idx] == xArr[i]) i++;
				else {
					idx++;
					if (idx > 1) flag = false;
				}
			}
		}
		return flag;
	}
}
