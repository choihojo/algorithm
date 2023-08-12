import java.io.*;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr = br.readLine().split(" ");
		int x = Integer.parseInt(srr[0]);
		int y = Integer.parseInt(srr[1]);
		
		int w = Integer.parseInt(srr[2]);
		int h = Integer.parseInt(srr[3]);
		int wMax = w;
		int wMin = 0;
		int hMax = h;
		int hMin = 0;
		
		int min1 = wMax - x;
		int min2 = x - wMin;
		int min3 = hMax - y;
		int min4 = y - hMin;
		
		int[] min = new int[] {min1, min2, min3, min4};
		Arrays.sort(min);
		System.out.println(min[0]);
	}
}