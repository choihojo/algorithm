import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int N;
	static String[] target;
	static String[] pattern;
	static int[] failTable;
	static StringBuilder sb;
	
	static void makeFailTable() {
		int patternLength = N;
		failTable = new int[patternLength];
//		패턴 포인터
		int j = 0;
//		타겟 포인터
		for (int i = 1; i < patternLength; i++) {
			while (j > 0 && !pattern[i].equals(pattern[j])) {
				j = failTable[j - 1];
			}
			if (pattern[i].equals(pattern[j])) {
				failTable[i] = ++j;
			}
		}
	}
	
	static int searchPattern() {
		int cnt = 0;
		int j = 0;
		int patternLength = N;
		int targetLength = 2 * N - 1;
		for (int i = 0; i < targetLength; i++) {
			while (j > 0 && !target[i].equals(pattern[j])) {
				j = failTable[j - 1];
			}
			if (target[i].equals(pattern[j])) {
				if (j == patternLength - 1) {
					cnt++;
					j = failTable[j];
				}
				else {
					j++;
				}
			}
		}
		return cnt;
	}
	
	static int getGCD(int a, int b) {
		if (a % b == 0) {
			return b;
		}
		return getGCD(b, a % b);
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		pattern = new String[N];
		for (int i = 0; i < N; i++) {
			pattern[i] = st.nextToken();
		}
		
		st = new StringTokenizer(br.readLine());
		target = new String[2 * N - 1];
		for (int i = 0; i < N; i++) {
			if (i == N - 1) target[i] = st.nextToken();
			else target[i] = target[i + N] = st.nextToken();
		}
	
//		System.out.println(Arrays.toString(pattern));
//		System.out.println(Arrays.toString(target));
		makeFailTable();
//		System.out.println(Arrays.toString(failTable));
		
		int numerator = searchPattern();
		int denominator = N;
		int gcd = getGCD(numerator, denominator);
		
		sb = new StringBuilder();
		sb.append(numerator / gcd).append("/").append(denominator / gcd);
		System.out.println(sb);
	}
}