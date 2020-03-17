import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1891 {
	static int d;
	static long[] coord = new long[2];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		d = Integer.parseInt(st.nextToken());
		char[] firstBlockArr = st.nextToken().toCharArray(); // char array로 받는다.
		numToCoord(firstBlockArr, 0);
		// coord 에는 첫 번째 조각 좌표가 담겨져 있다.
		st = new StringTokenizer(br.readLine());
		coord[0] += Long.parseLong(st.nextToken());
		coord[1] += Long.parseLong(st.nextToken());
		// coord 에는 두 번째 조각 좌표가 담겨져 있다. 이를 다시 BlockArr로 바꾸어야 한다.
		if(coord[0] >= Math.pow(2, d) || coord[0] < 0 || coord[1] >= Math.pow(2, d) || coord[1] < 0) {
			System.out.println("-1");
			return;
		}
		System.out.println(coordToNum(coord, d));
	}

	static void numToCoord(char[] charArr, int idx) { // char arr와 idx를 파라미터로 받아서 해당 좌표를 출력
		if(idx >= charArr.length) return;
		// charArr의 idx에 해당하는 숫자가 1,2,3,4 인지에 따라서 coord에 적절한 값을 더해준다.
		int idxNum = charArr[idx] - '0';
		if(idxNum == 1) {
			coord[0] += Math.pow(2, charArr.length-idx-1); // x좌표에 2^(d-idx-1) 만큼 더해줌
			coord[1] += Math.pow(2, charArr.length-idx-1); // y좌표에 2^(d-idx-1) 만큼 더해줌
		}
		else if(idxNum == 2) coord[1] += Math.pow(2, charArr.length-idx-1);
		else if(idxNum == 4) coord[0] += Math.pow(2, charArr.length-idx-1);
		// idxNum == 3일 때는 아무것도 더해주지 않음
		numToCoord(charArr, idx+1);
	}

	static String coordToNum(long[] coord, int cnt) {
		if(cnt >= 1) {
			long[] tmpCoord = coord;
			boolean xAxis = tmpCoord[0] >= Math.pow(2, cnt-1);
			boolean yAxis = tmpCoord[1] >= Math.pow(2, cnt-1);
			if(xAxis && yAxis) {
				tmpCoord[0] -= Math.pow(2, cnt-1);
				tmpCoord[1] -= Math.pow(2, cnt-1);
				return "1"+coordToNum(tmpCoord, cnt-1);
			} else if(!xAxis && yAxis) {
				tmpCoord[1] -= Math.pow(2, cnt-1);
				return "2"+coordToNum(tmpCoord, cnt-1);
			} else if(!xAxis && !yAxis) {
				return "3"+coordToNum(tmpCoord, cnt-1);
			} else if(xAxis && !yAxis) {
				tmpCoord[0] -= Math.pow(2, cnt-1);
				return "4"+coordToNum(tmpCoord, cnt-1);
			}
		}
		return "";
	}
}
