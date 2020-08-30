import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_16954 {
    public static final int[] dx = {0, 1, 0, -1, 0, 1, -1, -1, 1};
    public static final int[] dy = {0, 0, 1, 0, -1, 1, 1, -1, -1};
    public static int ans;
    public static char[][] map;
    public static Queue<Pos> q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[8][8];
        q = new LinkedList<>();
        for (int i = 0; i < 8; i++) {
            char[] ch = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                map[i][j] = ch[j];
            }
        }
        q.add(new Pos(7, 0));
        ans = (bfs()) ? 1 : 0;
        System.out.println(ans);
    }

    private static boolean bfs() {
        while (!q.isEmpty()) {
            int size = q.size();
            for (int t = 0; t < size; t++) {
                Pos pos = q.poll();
                if (map[pos.row][pos.col] == '#') continue;
                for (int i = 0; i < 9; i++) {
                    int nx = pos.row + dx[i], ny = pos.col + dy[i];
                    if (nx < 0 || nx >= 8 || ny < 0 || ny >= 8) continue;
                    if (nx == 0 && ny == 7) {
                        return true;
                    }
                    if (map[nx][ny] != '#') {
                        q.add(new Pos(nx, ny));
                    }
                }
            }
            down();
        }
        return false;
    }

    private static void down() {
        for (int i = 7; i >= 0; i--) {
            for (int j = 7; j >= 0; j--) {
                if (i - 1 < 0) map[i][j] = '.';
                else map[i][j] = map[i - 1][j];
            }
        }
    }

    private static class Pos {
        int row, col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}