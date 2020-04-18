public class BE_2 {
	public static void main(String[] args) {
		int[][] office = {{5,-1,4},{6,3,-1},{2,-1,1}};
		String[] move = {"go","go","right","go","right","go","left","go"};
		System.out.println(solution(office, 1, 0, move));
	}

	static int solution(int[][] office, int r, int c, String[] move) {
		int answer = 0, currR = r, currC = c;
		int currDir = 0; // 0: N, 1: E, 2: S, 3: W
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		if(office[currR][currC] != -1) {
			answer += office[currR][currC];
			office[currR][currC] = 0;
		}
		for(int i = 0; i < move.length; i++) {
			if(move[i].equals("go")) {
				int nextR = currR + dr[currDir];
				int nextC = currC + dc[currDir];
				if(nextR >= 0 && nextR < office.length && nextC >= 0 && nextC < office[0].length && office[nextR][nextC] != -1) {
					currR = nextR;
					currC = nextC;
					if(office[currR][currC] != -1) {
						answer += office[currR][currC];
						office[currR][currC] = 0;
					}
				}
			} else if(move[i].equals("left")) {
				currDir = (currDir+3)%4;
			} else if(move[i].equals("right")) {
				currDir = (currDir+1)%4;
			}
		}
		return answer;
	}
}
