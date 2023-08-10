import java.io.*;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pQue = new PriorityQueue<>();
		int x = 0;
		for (int n = 0; n < N; n++) {
			x = Integer.parseInt(br.readLine());
			if (x == 0) {
				if (pQue.peek() == null) {
					System.out.println(0);
				}
				else {
					System.out.println(pQue.poll());
				}
			}
			else {
				pQue.add(x);
			}
		}
		
	}
}
