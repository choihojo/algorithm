import java.io.*;
import java.util.*;
public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static boolean[] reserved;
	static boolean[] visited;
	static int[][] guests;
	static int[] arrived;
	static int N;
	static int waiting() {
		Arrays.sort(guests, (o1, o2) -> (o1[1] > o2[1] ? 1 : (o1[1] == o2[1] ? 0 : -1)));
		int max = -1;
		int idx = 0;
		int waitTime = 0;
		int cnt = 0;
		for (int t = 1; t <= 300_000; t++) {
			if (cnt == N) break;
			if (t <= 200_000) {
				if (reserved[t]) {
					if (!visited[t]) {
						visited[t] = true;
						waitTime = (t - arrived[t]);
						if (max < waitTime) max = waitTime;
						cnt++;
						continue;
					}
				}
			}
			while (visited[guests[idx][0]]) idx++;
			if (t < guests[idx][1]) continue;
			visited[guests[idx][0]] = true;
			waitTime = (t - guests[idx][1]);
			if (max < waitTime) max = waitTime;
			cnt++;
		}
		return max;
	}
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		reserved = new boolean[200_001];
		visited = new boolean[200_001];
		guests = new int[N][2];
		arrived = new int[200_001];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int reserveTime = Integer.parseInt(st.nextToken());
			int arriveTime = Integer.parseInt(st.nextToken());
			guests[n][0] = reserveTime;
			guests[n][1] = arriveTime;
			arrived[reserveTime] = arriveTime;
			if (reserveTime >= arriveTime) reserved[reserveTime] = true;
		}
		System.out.println(waiting());
	}
}