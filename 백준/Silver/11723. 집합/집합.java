import java.io.*;


public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int set = 0;
		int x = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			String[] srr = br.readLine().split(" ");
			if (srr[0].equals("add")) {
				x = Integer.parseInt(srr[1]);
				set = (set | (1 << (x - 1)));
			}
			else if (srr[0].equals("remove")) {
				x = Integer.parseInt(srr[1]);
				if ((set & (1 << (x - 1))) != 0) {
					set -= (1 << (x - 1));
				}
			}
			else if (srr[0].equals("check")) {
				x = Integer.parseInt(srr[1]);
				sb.append((((set & (1 << (x - 1))) != 0) ? 1 : 0) + "\n");
			}
			else if (srr[0].equals("toggle")) {
				x = Integer.parseInt(srr[1]);
				set = (set ^ (1 << (x - 1)));
			}
			else if (srr[0].equals("all")) {
				set = (set | ((1 << 20) - 1));
			}
			else if (srr[0].equals("empty")) {
				set = 0;
			}
		}
		System.out.println(sb.toString());
	}
}