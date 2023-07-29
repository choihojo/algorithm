import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String[] srr = new String[str.length()];
		for (int i = 0; i < str.length(); i++) {
			srr[i] = String.valueOf(str.charAt(i));
		}
		int sum = 0;
		for (int j = 0; j < srr.length; j++) {
			if ("ABC".contains(srr[j])) {
				sum += 3;
			} else if ("DEF".contains(srr[j])) {
				sum += 4;
			} else if ("GHI".contains(srr[j])) {
				sum += 5;
			} else if ("JKL".contains(srr[j])) {
				sum += 6;
			} else if ("MNO".contains(srr[j])) {
				sum += 7;
			} else if ("PQRS".contains(srr[j])) {
				sum += 8;
			} else if ("TUV".contains(srr[j])) {
				sum += 9;
			} else if ("WXYZ".contains(srr[j])) {
				sum += 10;
			}
		}
		
		System.out.println(sum);
	}
}