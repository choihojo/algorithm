import java.io.*;
import java.util.*;

/*
 * 1층 ~ F층으로 이루어진 고층 건물
 * 스타트링크가 있는 위치는 G층
 * 강호 현재 위치는 S층
 * 총 F층으로 이루어진 건물에서 S층에서 G층으로 이동해야 함
 * 엘리베이터 버튼은 U, D 2가지만 존재함
 * U 버튼 : 위로 U층을 이동하는 버튼
 * D 버튼 : 아래로 D층을 이동하는 버튼
 * 버튼을 몇 번 눌러야 하는지 구하고, 만약 엘리베이터로 갈 수 없다면 계단으로 가야 함
 */

public class Main {
	static int F, S, G, U, D;
	static BufferedReader br;
	static StringTokenizer st;
	static boolean[] visited;
	static int[] dHeight;
	static int depth;
	static boolean flag;
	
	static int bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(S);
		visited[S] = true;
		
		flag = false;
		outer : while (!queue.isEmpty()) {
			depth++;
			int size = queue.size();
			for (int s = 0; s < size; s++) {
				int poll = queue.poll();
				 
				for (int i = 0; i < 2; i++) {
					int height = poll + dHeight[i];
					if (height > F || height < 1 || visited[height]) {
						continue;
					}
					if (height == G) {
						flag = true;
						break outer;
					}
					visited[height] = true;
					queue.offer(height);
				}
			}
		}
		return depth;
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		visited = new boolean[F + 1];
		dHeight = new int[] {U, -D};
		
		if (S == G) {
			System.out.println(0);
		}
		else {
			bfs();
			System.out.println(flag ? depth : "use the stairs");
		}
	}
}