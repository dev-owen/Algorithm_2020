import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class BOJ_6087 {
    static int rowLen, colLen;
    static char[][] map;
    static int[] dRow = {1, 0, -1, 0}, dCol = {0, 1, 0, -1};
    static Queue<Dot> queue = new PriorityQueue<>();
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        colLen = Integer.parseInt(input[0]);
        rowLen = Integer.parseInt(input[1]);
        map = new char[rowLen][colLen];
        int startRow = 0, startCol = 0;
        for (int i = 0; i < rowLen; i++) {
            char[] charArr = br.readLine().toCharArray();
            for (int j = 0; j < colLen; j++) {
                map[i][j] = charArr[j];
                if (map[i][j] == 'C') {
                    startRow = i;
                    startCol = j;
                }
            }
        }
        boolean[][] visited = new boolean[rowLen][colLen];
        visited[startRow][startCol] = true;
        queue.add(new Dot(startRow, startCol, 0, -1, visited));
        bfs();
        System.out.println(ans);
    }

    static void bfs() {
        while (!queue.isEmpty()) {
            Dot dot = queue.poll();
            int row = dot.row, col = dot.col, cnt = dot.cnt, dir = dot.dir;
            boolean[][] visited = dot.visited;
            if (map[row][col] == 'C' && !visited[row][col]) {
                ans = cnt;
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nRow = row + dRow[i], nCol = col + dCol[i], nDir = i, nCnt = cnt;
                if (nRow >= 0 && nRow < rowLen && nCol >= 0 && nCol < colLen) {
                    if (visited[nRow][nCol] || map[nRow][nCol] == '*' || nCnt >= ans) continue;

                    if (nDir != dir && dir != -1) nCnt = cnt + 1;
                    else nCnt = cnt;

                    visited[row][col] = true;
                    queue.add(new Dot(nRow, nCol, nCnt, nDir, visited));
                }
            }
        }
    }

    private static class Dot implements Comparable<Dot> {
        int row, col, cnt, dir;
        boolean[][] visited;

        Dot(int row, int col, int cnt, int dir, boolean[][] visited) {
            this.row = row;
            this.col = col;
            this.cnt = cnt;
            this.dir = dir;
            this.visited = visited;
        }

        @Override
        public int compareTo(Dot dot) {
            return this.cnt - dot.cnt;
        }
    }
}

