import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, home[], p[];
	static ArrayList<int[]> c;
	static Queue<int[]> q;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(bf.readLine());
		//입력 받으면서 집과 1000이하로 거리차이가 나는 편의점을 모두 큐에 넣는다.
		//큐에 넣은건 리스트에 넣지 말자
		for(int tc = 1; tc <= t; tc++) {
			boolean ishappy = false;
			n = Integer.parseInt(bf.readLine());
			c = new ArrayList<>();
			q = new ArrayDeque<int[]>();
			st = new StringTokenizer(bf.readLine());
			home = new int[2];
			home[0] = Integer.parseInt(st.nextToken());
			home[1] = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(bf.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				if(Math.abs(home[0] - x) + Math.abs(home[1] - y) <= 1000) {
					q.offer(new int[] {x, y});
				}else {
					c.add(new int[] {x, y});
				}
			}
			
			st = new StringTokenizer(bf.readLine());
			p = new int[2];
			p[0] = Integer.parseInt(st.nextToken());
			p[1] = Integer.parseInt(st.nextToken());
			c.add(p);
			
			while(!q.isEmpty()) {
				int temp[] = q.poll();
				int x = temp[0];
				int y = temp[1];
				if(x == p[0] && y == p[1]) {
					ishappy = true;
					break;
				}
				for(int i = c.size() - 1; i >= 0; i--) {
					int nx = c.get(i)[0];
					int ny = c.get(i)[1];
					if(Math.abs(nx - x) + Math.abs(ny - y) <= 1000) {
						q.offer(new int[] {nx, ny});
						c.remove(i);
					}
				}
			}
			
			if(Math.abs(p[0] - home[0]) + Math.abs(p[1] - home[1]) <= 1000) {
				ishappy = true;
			}
			
			
			if(ishappy) {
				System.out.println("happy");
			}else {
				System.out.println("sad");
			}
		}
	}
}
