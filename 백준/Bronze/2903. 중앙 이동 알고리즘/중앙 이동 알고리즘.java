import java.io.*;

public class Main {
	public static int dot(int N) {
		if (N == 0) {
			return 2;
		}
		return 2 * dot(N - 1) - 1;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		System.out.println(dot(N) * dot(N));
	}	
}