import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] srr = br.readLine().split(" ");
		int minX = Integer.parseInt(srr[0]);
		int maxX = Integer.parseInt(srr[0]);
		int minY = Integer.parseInt(srr[1]);
		int maxY = Integer.parseInt(srr[1]);
		for (int i = 1; i < N; i++) {
			srr = br.readLine().split(" ");
			if (minX > Integer.parseInt(srr[0])) {
				minX = Integer.parseInt(srr[0]);
			}
			if (maxX < Integer.parseInt(srr[0])) {
				maxX = Integer.parseInt(srr[0]);
			}
			if (minY > Integer.parseInt(srr[1])) {
				minY = Integer.parseInt(srr[1]);
			}
			if (maxY < Integer.parseInt(srr[1])) {
				maxY = Integer.parseInt(srr[1]);
			}
		}
		System.out.println((maxX - minX) * (maxY - minY));
	}
}
