public class KAKAO20_ITN_2 {
	public static void main(String[] args) {
		System.out.println(solution("100-200*300-500+20"));
		System.out.println(solution("50*6-3*2"));
	}

	static long solution(String expression) {
		long answer = 0;
		String[] resArr1, resArr2;
		String[][] calc = {{"\\+", "-", "\\*"}, {"\\+", "\\*", "-"}, {"-", "\\*", "\\+"}, {"-", "\\+", "\\*"}, {"\\*", "\\+", "-"}, {"\\*", "-", "\\+"}};
		for (int i = 0; i < 6; i++) {
			String[] strArr1 = expression.split(calc[i][0]);
			resArr1 = new String[strArr1.length];
			for (int j = 0; j < strArr1.length; j++) {
				String[] strArr2 = strArr1[j].split(calc[i][1]);
				resArr2 = new String[strArr2.length];
				for (int k = 0; k < strArr2.length; k++) {
					String[] strArr3 = strArr2[k].split(calc[i][2]);
					resArr2[k] = String.valueOf(calculation(strArr3, calc[i][2]));
				}
				resArr1[j] = String.valueOf(calculation(resArr2, calc[i][1]));
			}
			answer = Math.max(answer, Math.abs(calculation(resArr1, calc[i][0])));
		}
		return answer;
	}

	static long calculation(String[] strArr, String calc) {
		long ans = Long.parseLong(strArr[0]);
		for (int i = 1; i < strArr.length; i++) {
			if (calc.equals("\\+")) ans += Long.parseLong(strArr[i]);
			else if (calc.equals("-")) ans -= Long.parseLong(strArr[i]);
			else if (calc.equals("\\*")) ans *= Long.parseLong(strArr[i]);
		}
		return ans;
	}
}
