public class Line_2 {
	public static void main(String[] args) {
		String[] arr = {"24553","24553","24553","24553"};
		System.out.println(solution("24551", arr));
	}
	public static int solution(String answer_sheet, String[] sheets) {
		int answer = 0, wrongAnsCnt, wrongAnsSeq, maxSeq;
		for(int i = 0; i < sheets.length; i++) {
			for(int j = i+1; j < sheets.length; j++) {
				wrongAnsCnt = 0;
				wrongAnsSeq = 0;
				maxSeq = 0;
				for(int k = 0; k < answer_sheet.length(); k++) {
					if(sheets[i].charAt(k) == sheets[j].charAt(k) && sheets[i].charAt(k) != answer_sheet.charAt(k)) {
						wrongAnsCnt++;
						wrongAnsSeq++;
					} else {
						maxSeq = Math.max(wrongAnsSeq, maxSeq);
						wrongAnsSeq = 0;
					}
				}
				int inProperIdx = wrongAnsCnt + (int)Math.pow(maxSeq, 2);
				answer = Math.max(inProperIdx, answer);
			}
		}
		return answer;
	}
}
