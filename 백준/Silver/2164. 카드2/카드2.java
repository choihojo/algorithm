import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
		for (int i = 1; i <= N; i++) {
			deque.addLast(i);
		}
		while (deque.size() != 1) {
			deque.removeFirst();
			deque.addLast(deque.removeFirst());
		}
		System.out.println(deque.peek());
	}
}
