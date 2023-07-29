import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr = br.readLine().split(" ");
		StringBuilder sb = new StringBuilder();
		
		for (int i = 2; i >= 0; i--) {
			sb.append(srr[0].charAt(i));
		}
		int n1 = Integer.parseInt(sb.toString());
		
		sb.setLength(0);
		
		for (int i = 2; i >= 0; i--) {
			sb.append(srr[1].charAt(i));
		}
		int n2 = Integer.parseInt(sb.toString());

		System.out.println(n1 > n2 ? n1 : n2);
		
		
	}
}