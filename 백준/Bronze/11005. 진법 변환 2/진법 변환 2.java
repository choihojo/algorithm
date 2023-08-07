import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr = br.readLine().split(" ");
		int num = Integer.parseInt(srr[0]);
		int n = Integer.parseInt(srr[1]);
		int r = 0;
		StringBuilder sb = new StringBuilder();
		int c = 0;
		int q = num;
		while (q >= n) {
			r = q % n;
			if (r >= 10) {
				c = (r - 10 + 'A');
			}
			else {
				c = r + '0';
			}
			sb.insert(0, (char) c);
			if (q >= n) {
				q = q / n;
			}
		}
		if (q >= 10) {
			c = (q - 10 + 'A');
		}
		else {
			c = q + '0';
		}
		sb.insert(0, (char) c);
		System.out.println(sb.toString());
	}
}