import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static String s;
	static int n;
	static int[] failTable;
	static int max;
	
	static void makeFailTable() {
		int patternLength = s.length();
		failTable = new int[patternLength];
		int j = 0;
		for (int i = 1; i < patternLength; i++) {
			while (j > 0 && s.charAt(i) != s.charAt(j)) {
				j = failTable[j - 1];
			}
			if (s.charAt(i) == s.charAt(j)) {
				failTable[i] = ++j;
			}
		}
		int subLength = (patternLength - failTable[patternLength - 1]);
		n = patternLength % subLength == 0 ? patternLength / subLength : 1;
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		while (!(s = br.readLine()).equals(".")) {
			makeFailTable();
			sb.append(n).append("\n");
		}
		System.out.println(sb);
	}
}