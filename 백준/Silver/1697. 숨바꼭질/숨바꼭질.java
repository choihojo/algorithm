import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int K;
	static int[] start;
	static int[] visited;
	
	public static int bfsByQueue() {
		Queue<int[]> queue = new ArrayDeque<>();
	
		queue.offer(start);
		int[] poll = new int[] {0, 0, 0};
		int time = 0;
		
		while (!queue.isEmpty()) {
			poll = queue.poll();

			if (poll[1] == 1) poll[0]--;
			else if (poll[1] == 2) poll[0]++;
			else if (poll[1] == 3) poll[0] *= 2;
			
			if (poll[0] >= 0 && poll[0] <= 100_000) {
				if (visited[poll[0]] == 0) {
					visited[poll[0]] = poll[2];
					queue.offer(new int[] {poll[0], 1, poll[2] + 1});
					queue.offer(new int[] {poll[0], 2, poll[2] + 1});
					queue.offer(new int[] {poll[0], 3, poll[2] + 1});
				}
			}
			
			if (poll[0] == K) {
				time = poll[2];
				break;
			}
			
		}
		return time;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr = br.readLine().split(" ");
		N = Integer.parseInt(srr[0]);
		K = Integer.parseInt(srr[1]);
		start = new int[] {N, 0, 0};
		visited = new int[100001];
		System.out.println(bfsByQueue());
	}
}