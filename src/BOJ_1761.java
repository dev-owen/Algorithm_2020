import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1761 {

    static ArrayList<Integer>[] list;
    static ArrayList<Integer>[] dlist;

    static int[] depth;
    static int[][] parent;
    static int[] distance;
    static boolean[] visited;

    static int N;
    static int K;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList[N + 1];
        dlist = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<Integer>();
            dlist[i] = new ArrayList<Integer>();
        }

        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
            dlist[a].add(c);
            dlist[b].add(c);
        }

        depth = new int[N + 1];
        distance = new int[N + 1];
        visited = new boolean[N + 1];

        int temp = 1;
        K = 0;

        while (temp <= N) {
            temp <<= 1;
            K++;
        }

        parent = new int[N + 1][K];
        dfs(1, 0, 0);
        fillParent();
        int Q = Integer.parseInt(br.readLine());

        for (int i = 1; i <= Q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long answer = getLCA(a, b);
            System.out.println(answer);
        }
    }

    private static void fillParent() {
        for (int i = 1; i < K; i++) {
            for (int j = 1; j <= N; j++) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
            }
        }
    }

    private static long getLCA(int a, int b) {
        if (depth[a] < depth[b]) {
            int temp = b;
            b = a;
            a = temp;
        }

        int orgA = a;
        int orgB = b;

        for (int i = K - 1; i >= 0; i--) {
            int diff = depth[a] - depth[b];
            if ((diff & (1 << i)) != 0) {
                a = parent[a][i];
            }
        }

        if (a == b) return distance[orgA] - distance[orgB];

        for (int i = K - 1; i >= 0; i--) {
            if (parent[a][i] != parent[b][i]) {
                a = parent[a][i];
                b = parent[b][i];
            }
        }
        int lca = parent[a][0];
        long answer = distance[orgA] + distance[orgB] - (distance[lca] * 2);
        return answer;
    }

    private static void dfs(int node, int dep, int dis) {
        visited[node] = true;
        depth[node] = dep;
        distance[node] = dis;

        for (int i = 0; i < list[node].size(); i++) {
            if (!visited[list[node].get(i)]) {
                parent[list[node].get(i)][0] = node;
                dfs(list[node].get(i), dep + 1, dis + dlist[node].get(i));
            }
        }
    }
}