import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr = br.readLine().split(" ");
		int M = Integer.parseInt(srr[0]);
		int N = Integer.parseInt(srr[1]);

		boolean[] isPrime = new boolean[N + 1];
		Arrays.fill(isPrime, true);

		isPrime[0] = false;
		isPrime[1] = false;
		
//		에라토스테네스의 체
//		2부터 끝까지에 대해 알아봄
		for (int i = 2; i <= N; i++) {
//			만약 i가 참이라면(소수라면)
			if (isPrime[i]) {
				int idx = i;
//				본인을 제외한 배수에 대해 전부 소수 여부를 거짓으로 체크함
				while ((idx += i) < isPrime.length) {
					isPrime[idx] = false;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = M; i <= N; i++) {
			if (isPrime[i]) {
				sb.append(i).append("\n");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}
}