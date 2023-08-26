import java.io.*;
import java.util.*;

// 최대한 공평하게 선거구 획정
// 백준시는 N개의 구역으로 나누어져 있음 (1번 ~ N번)
// 구역을 두 개의 선거구로 나눠야 함
// 선거구는 구역을 하나 이상 포함해야함 + 한 선거구에 포함된 구역은 모두 연결됨
// 중간에 통하는 인접한 구역은 0개 이상 -> 뭔 소리지?
// 인구 차이의 최솟값을 구해야함

// 부분집합 + flood fill인 듯 -> 가지치기 해야하나?

public class Main {
//	구역의 개수 N;
	static int N;
	static List<List<Integer>> graph;
	static int[] population;
	static boolean[] visited;
	static int[] selected;
	static int sum1;
	static int sum2;
	static int min = Integer.MAX_VALUE;
	
	static void subset(int cnt) {
		if (cnt == N) {
			bfs();
			return;
		}
		selected[cnt + 1] = 1;
		subset(cnt + 1);
		selected[cnt + 1] = 2;
		subset(cnt + 1);
	}
	
	static void bfs() {
		sum1 = 0;
		sum2 = 0;
		for (int i = 1; i <= N; i++) {
			visited[i] = false;
		}
		Queue<Integer> queue = new ArrayDeque<>();
		int poll;
		
//		1번 구역 탐색을 위해 방문체크하고 큐에 넣어줌
		for (int i = 1; i <= N; i++) {
			if (selected[i] == 1) {
				queue.offer(i);
				visited[i] = true;
				sum1 += population[i];
				break;
			}
		}
		if (queue.size() == 0) return;
		
//		1번 구역 탐색
		while (!queue.isEmpty()) {
			poll = queue.poll();
			for (int e : graph.get(poll)) {
				if (visited[e]) continue;
				if (selected[e] == 1) {
					visited[e] = true;
					sum1 += population[e];
					queue.offer(e);
				}
			}
		}
		
//		2번 구역 탐색을 위해 방문체크하고 큐에 넣어줌
		for (int i = 1; i <= N; i++) {
			if (selected[i] == 2) {
				queue.offer(i);
				visited[i] = true;
				sum2 += population[i];
				break;
			}
		}
		if (queue.size() == 0) return;
		
//		2번 구역 탐색
		while (!queue.isEmpty()) {
			poll = queue.poll();
			for (int e : graph.get(poll)) {
				if (visited[e]) continue;
				if (selected[e] == 2) {
					visited[e] = true;
					sum2 += population[e];
					queue.offer(e);
				}
			}
		}
		
		boolean flag = true;
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) flag = false;
		}
		if (flag) {
			min = Math.min(min, Math.abs(sum1 - sum2));
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new ArrayList<>();
//		1 크게 설정
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		population = new int[N + 1];
		visited = new boolean[N + 1];
		selected = new int[N + 1];
		
		String[] srr;
		srr = br.readLine().split(" ");
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(srr[i - 1]);
		}
		
		for (int i = 1; i <= N; i++) {
			srr = br.readLine().split(" ");
			for (int j = 1; j < srr.length; j++){
				graph.get(i).add(Integer.parseInt(srr[j]));
			}
		}
		
		subset(0);		
		if (min == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
	}
}