import java.io.*;
import java.util.*;

public class KickStartRoundF_1 {
    static LinkedList<Integer> inputArr;
    static HashMap<Integer, ArrayList<Integer>> hashMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()), X = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            inputArr = new LinkedList<>();
            while (st.hasMoreTokens()) {
                inputArr.add(Integer.parseInt(st.nextToken()));
            }

            hashMap = new HashMap<>();
            for (int j = 1; j <= N; j++) {
                int num = inputArr.poll();
                int cnt = num / X;
                if (!hashMap.containsKey(cnt)) {
                    ArrayList<Integer> aryLst = new ArrayList<>();
                    aryLst.add(j);
                    hashMap.put(cnt, aryLst);
                } else {
                    ArrayList<Integer> aryLst = hashMap.get(cnt);
                    aryLst.add(j);
                    hashMap.put(cnt, aryLst);
                }
            }
            Object[] mapKey = hashMap.keySet().toArray();
            Arrays.sort(mapKey);
            sb.append("Case #" + i + ": ");
            for (int key : hashMap.keySet()) {
                ArrayList<Integer> aryLst = hashMap.get(key);
                for (int num : aryLst) {
                    sb.append(num + " ");
                }
            }
            if (i < T) sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
