import java.io.*;
import java.util.*;

//앞서 푼 것처럼 배열로 풀 수도 있고 큐로 풀 수도 있음
//배열로 푸는 게 더 섬세한 작업이 요구되는 듯
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr = br.readLine().split(" ");
		int N = Integer.parseInt(srr[0]);
		int K = Integer.parseInt(srr[1]);
		
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			queue.offer(i);
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		
		while (!queue.isEmpty()) {
			for (int i = 0; i < (K - 1); i++) {
				queue.offer(queue.poll());
			}
			sb.append(queue.poll()).append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append(">");
		System.out.println(sb.toString());
		
	}
}
