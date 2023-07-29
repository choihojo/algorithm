import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int loop = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < loop; i++) {
			sb.setLength(0);
			String[] srr = br.readLine().split(" ");
			for (int j = 0; j < srr[1].length(); j++) {
				for (int k = 0; k < Integer.parseInt(srr[0]); k++) {
					sb.append(srr[1].charAt(j));
				}
			}
			System.out.println(sb.toString());
		}
	}
}
