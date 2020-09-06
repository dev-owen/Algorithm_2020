import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2146 {
    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[][] inputArr = new int[n][n];
        int[][] distArr = new int[n][n];
        int[][] groupArr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                inputArr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (inputArr[i][j] == 1 && groupArr[i][j] == 0) { //a가 1이고 g를 초기화 안했다면
                    Queue<Dot> q = new LinkedList<Dot>();
                    groupArr[i][j] = ++cnt; //g를 초기화
                    q.add(new Dot(i, j));
                    while (!q.isEmpty()) {
                        Dot p = q.remove();
                        int x = p.x, y = p.y;
                        for (int k = 0; k < 4; k++) {
                            int nx = x + dx[k], ny = y + dy[k];
                            if (0 <= nx && nx < n && 0 <= ny && ny < n)
                                if (inputArr[nx][ny] == 1 && groupArr[nx][ny] == 0) {
                                    q.add(new Dot(nx, ny));
                                    groupArr[nx][ny] = cnt;
                                }
                        }
                    }
                }
            }
        }

        Queue<Dot> q = new LinkedList<Dot>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distArr[i][j] = -1;
                if (inputArr[i][j] == 1) {
                    q.add(new Dot(i, j));
                    distArr[i][j] = 0;
                }
            }
        }
        while (!q.isEmpty()) {
            Dot p = q.remove();
            int x = p.x, y = p.y;
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k], ny = y + dy[k];
                if (0 <= nx && nx < n && 0 <= ny && ny < n)
                    if (distArr[nx][ny] == -1) {
                        distArr[nx][ny] = distArr[x][y] + 1;
                        groupArr[nx][ny] = groupArr[x][y];
                        q.add(new Dot(nx, ny));
                    }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 4; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];
                    if (0 <= x && x < n && 0 <= y && y < n) {
                        if (groupArr[i][j] != groupArr[x][y]) ans = Math.min(ans, distArr[i][j] + distArr[x][y]);
                    }
                }
            }
        }
        System.out.println(ans);
    }

    private static class Dot {
        int x;
        int y;

        public Dot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

