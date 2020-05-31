import java.io.*;
import java.util.StringTokenizer;

public class Kids_4 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long width = Long.parseLong(st.nextToken());
		long height = Long.parseLong(st.nextToken());
		int n = Integer.parseInt(br.readLine());
		long[][] pixelArr = new long[n][4]; // x_min, y_min, x_max, y_max
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			long x_min = Long.parseLong(st.nextToken());
			long y_min = Long.parseLong(st.nextToken());
			long x_len = Long.parseLong(st.nextToken());
			long y_len = Long.parseLong(st.nextToken());
			pixelArr[i][0] = x_min;
			pixelArr[i][1] = y_min;
			pixelArr[i][2] = x_min + x_len - 1L;
			pixelArr[i][3] = y_min + y_len - 1L;
		}
		long blocks = 0, opens = width*height;
		for (int i = 0; i < n; i++) {
			blocks += (pixelArr[i][2] - pixelArr[i][0] + 1L) * (pixelArr[i][3] - pixelArr[i][1] + 1L);
			for (int j = i + 1; j < n; j++) {
				if (pixelArr[j][0] <= pixelArr[i][2] && pixelArr[j][1] <= pixelArr[i][3] && pixelArr[i][0] < pixelArr[j][2] && pixelArr[i][1] < pixelArr[j][3]) {
					blocks -= (pixelArr[i][2] - pixelArr[j][0] + 1L) * (pixelArr[i][3] - pixelArr[j][1] + 1L);
				} else if (pixelArr[i][0] <= pixelArr[j][2] && pixelArr[i][1] <= pixelArr[j][3] && pixelArr[j][0] < pixelArr[i][2] && pixelArr[j][1] < pixelArr[i][3]) {
					blocks -= (pixelArr[j][2] - pixelArr[i][0] + 1L) * (pixelArr[j][3] - pixelArr[i][1] + 1L);
				}
			}
		}
		System.out.println(blocks + " " + (opens - blocks));
	}
}
