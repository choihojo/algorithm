import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static BufferedReader br;
	static StringTokenizer st;
	static List<List<Integer>> list;
	static int[] parents;
	static StringBuilder sb;
	
	/*
	 * 우선 list에 (x, y)를 넣을 때 x < y로 넣어줌
	 * (x, y)에서 x를 기준으로 오름차순으로 정렬함
	 * x가 이미 방문되어 있다면 y는 자식임
	 * x가 방문되어 있지 않는 경우는 없을 듯함
	 * 방문체크 배열 쓰지 않고 그냥 y가 무조건 자식일 것 같음 -> 아님
	 * (1, 6) (3, 6)일 때 3이 6의 자식이기 때문임
	 * 따라서 방문체크 배열 써야 함
	 * 부모 배열을 방문체크 배열로 겸해서 씀
	 */
	
	static void bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(1);
		parents[1] = 1;
		
		int poll = 0;
		while (!queue.isEmpty()) {
			poll = queue.poll();
			
			for (int i = 0; i < list.get(poll).size(); i++) {
				int child = list.get(poll).get(i);
				if (parents[poll] != child) {
					parents[child] = poll;
					queue.offer(child);
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}
		for (int i = 0; i < (N - 1); i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.get(a).add(b);
			list.get(b).add(a);
		}
		
		parents = new int[N + 1];
		bfs();
		
		sb = new StringBuilder();
		for (int i = 2; i <= N; i++) {
			sb.append(parents[i]).append("\n");
		}
		System.out.println(sb.deleteCharAt(sb.length() - 1));
	}
}