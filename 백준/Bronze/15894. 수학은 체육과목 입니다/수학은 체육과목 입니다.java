import java.io.*;
import java.math.BigInteger;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		BigInteger b = new BigInteger(Integer.toString(n));
		System.out.println(b.multiply(new BigInteger("4")));
	}
}