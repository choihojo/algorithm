import java.io.*;
import java.util.*;

class Vertex {
    int to;
    int weight;

    public Vertex(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

public class Main {
    static int N, M;
    static List<List<Vertex>> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int n = 0; n <= N; n++) {
            list.add(new ArrayList<>());
        }
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            list.get(A).add(new Vertex(B, C));
            list.get(B).add(new Vertex(A, C));
        }

        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        br.close();

        int result = binarySearch(from, to, 1, 1_000_000_000);
        System.out.println(result);
    }

    static boolean bfs(int from, int to, int mid) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        visited[from] = true;
        queue.offer(from);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (current == to) {
                return true;
            }

            for (Vertex vertex : list.get(current)) {
                int next = vertex.to;
                int weight = vertex.weight;
                if (!visited[next] && weight >= mid) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
        return false;
    }

    static int binarySearch(int from, int to, int start, int end) {
        int result = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (bfs(from, to, mid)) {
                result = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return result;
    }
}