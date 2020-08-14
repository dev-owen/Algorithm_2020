import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class BOJ_16954 {
    static ArrayList<boolean[][]> aryList = new ArrayList<>();
    static int[] dx = {1, 1, 0, -1, -1, -1, 0, 1}, dy = {0, 1, 1, 1, 0, -1, -1, -1}; // 0: 3시, 시계 반대방향... ex. 2: 12시, 4: 9시,

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[][] initArray = new boolean[8][8];
        for (int i = 7; i >= 0; i--) {
            char[] inputCharArray = br.readLine().toCharArray();
            for (int j = 0; j <= 7; j++) {
                initArray[i][j] = inputCharArray[j] != '#';
            }
        }
        aryList.add(initArray);
        for (int i = 1; i <= 7; i++) {
            boolean[][] tmpArray = new boolean[8][8];
            for (int j = 0; j <= 7; j++) {
                for (int k = 0; k <= 7; k++) {
                    tmpArray[j][k] = initArray[j][k];
                }
            }
            boolean[][] movedArray = arrayMoveWithTime(tmpArray, i);
            aryList.add(movedArray);
        }

        Point initPoint = new Point(0, 0, 0, -1);
        LinkedList<Point> queue = new LinkedList<>();
        queue.add(initPoint);
        boolean flag = false;
        while (!queue.isEmpty()) {
            Point p = queue.pop();
            int nowX = p.x, nowY = p.y, nowT = p.time, exDir = p.dir;
            if (nowX == 7 && nowY == 7) {
                flag = true;
                break;
            }
            for (int i = 0; i <= 7; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                int nextT = nowT + 1;
                int nowDir = i;
                if (nextX >= 0 && nextX <= 7 && nextY >= 0 && nextY <= 7 && nowDir + exDir != 7) {
                    if (nowT <= 7) {
                        if (aryList.get(nowT)[nextX][nextY]) {
                            if (nextT <= 7) {
                                if (aryList.get(nextT)[nextX][nextY]) queue.add(new Point(nextX, nextY, nextT, nowDir));
                            } else queue.add(new Point(nextX, nextY, nextT, nowDir));
                        }
                    } else {
                        if (nextT <= 7) {
                            if (aryList.get(nextT)[nextX][nextY]) queue.add(new Point(nextX, nextY, nextT, nowDir));
                        } else queue.add(new Point(nextX, nextY, nextT, nowDir));
                    }
                }
            }
        }
        System.out.println(flag ? 1 : 0);
    }

    private static boolean[][] arrayMoveWithTime(boolean[][] arr, int time) {
        boolean[][] resArray = new boolean[8][8];
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if (i + time <= 7) {
                    if (!arr[i + time][j]) resArray[i][j] = false;
                    else resArray[i][j] = true;
                } else resArray[i][j] = true;
            }
        }
        return resArray;
    }

    private static class Point {
        int x, y, time, dir;

        private Point(int x, int y, int time, int dir) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.dir = dir;
        }
    }
}

