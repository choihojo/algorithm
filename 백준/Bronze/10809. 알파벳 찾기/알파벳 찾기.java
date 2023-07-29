import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		int idx;
		
		for (int i = 0; i < alphabet.length(); i++) {
			idx = -1;
			for (int j = 0; j < str.length(); j++) {
				if (str.charAt(j) == alphabet.charAt(i)) {
					idx = j;
					break;
				}
			}
			System.out.print(idx + " ");
		}
	}
}