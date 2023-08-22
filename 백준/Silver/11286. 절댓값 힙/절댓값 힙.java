import java.io.*;
import java.util.*;

public class Main {
	static int N;
	
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
//		우선순위 큐 사용해서 comparator 구현
//		절댓값이 같다면 그 중 더 작은 값을 출력하게 함
//		절댓값이 다르다면 더 작은 절댒값을 출력하게 함
		Queue<Integer> pQueue = new PriorityQueue<>((o1, o2) ->
		{
			if (Math.abs(o1) == Math.abs(o2)) return (o1 - o2);
			else return (Math.abs(o1) - Math.abs(o2));
		});
		
		StringBuilder sb = new StringBuilder();
		int x = 0;
		for (int n = 0; n < N; n++) {
			x = Integer.parseInt(br.readLine());
			if (x == 0) {
				if (pQueue.size() == 0) sb.append(0).append("\n");
				else sb.append(pQueue.poll()).append("\n");
			}
			else {
				pQueue.offer(x);
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}
}