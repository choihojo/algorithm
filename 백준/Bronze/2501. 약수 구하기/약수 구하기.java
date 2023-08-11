import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr = br.readLine().split(" ");
		int N = Integer.parseInt(srr[0]);
		int K = Integer.parseInt(srr[1]);
		int cnt = 0;
		int result = 0;
		
		for (int i = 1; i <= N; i++) {
			if (N % i == 0) {
				cnt++;
				if (cnt == K) {
					result = i;
					break;
				}
			}
		}
		System.out.println(result);
	}	
}