import java.io.*;


public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String word = br.readLine();
		char[] crr = new char[word.length()];
		for (int i = 0; i < crr.length; i++) {
			crr[i] = word.charAt(i);
		}
		boolean flag = true;
		boolean wlag = true;
		while (wlag) {
			if (crr.length % 2 == 0) {
				for (int i = 0; i < ((crr.length) / 2); i++) {
					if (crr[i] != crr[crr.length - 1 - i]) {
						flag = false;
						break;
					}
				}
				wlag = false;
			}
			else {
				for (int i = 0; i < ((crr.length) / 2); i++) {
					if (crr[i] != crr[crr.length - 1 - i]) {
						flag = false;
						break;
					}
				}
				wlag = false;
			}
		}
		System.out.println(flag ? 1 : 0);
	}
}