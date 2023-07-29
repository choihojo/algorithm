import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr = br.readLine().split(" ");
		int size = srr.length;
		
		try {
			if (srr[0].equals("")) {
				size -= 1;
			}
		} catch (Exception e) {
			
		} finally {
			System.out.println(size);
		}

	}
}