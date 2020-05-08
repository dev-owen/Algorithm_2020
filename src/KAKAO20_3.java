public class KAKAO20_3 {
	public static void main(String[] args) {
		int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
		int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};

		System.out.println(solution(key, lock));
	}

	static boolean solution(int[][] key, int[][] lock) {
		int M = key.length, N = lock.length;
		int[][] door = new int[3 * N + 1][3 * N + 1];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				door[2 * N - i][N + 1 + j] = lock[i][j];
			}
		}
		int[][] flipKey = new int[M][M];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				flipKey[i][j] = key[M - 1 - i][j];
			}
		}
		for (int i = 0; i < 4; i++) {
			int[][] nowKey = rotateKey(flipKey, i);
			for (int j = N - M; j <= 2 * N; j++) {
				for (int k = N - M; k <= 2 * N; k++) {
					boolean isOpen = true;
					loop:
					for (int m = 0; m < M; m++) {
						for (int n = 0; n < M; n++) {
							if (nowKey[m][n] + door[j + m][k + n] == 1) continue;
							else {
								if (m >= N + 1 && m <= 2 * N && n >= N + 1 && n <= 2 * N) {
									isOpen = false;
									break loop;
								}
							}
						}
					}
					if (isOpen) return true;
				}
			}
		}
		return false;
	}

	static int[][] rotateKey(int[][] key, int dir) {
		if (dir == 0) return key;
		int M = key.length;
		int[][] newKey = new int[M][M];
		if (dir == 1) {
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < M; j++) {
					newKey[i][j] = key[j][M - 1 - i];
				}
			}
		} else if (dir == 2) {
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < M; j++) {
					newKey[i][j] = key[M - 1 - i][M - 1 - j];
				}
			}
		} else if (dir == 3) {
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < M; j++) {
					newKey[i][j] = key[M - 1 - j][i];
				}
			}
		}
		return newKey;
	}
}
