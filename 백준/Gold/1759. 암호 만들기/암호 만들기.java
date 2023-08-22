import java.io.*;
import java.util.*;

// 암호는 서로 다른 L개의 알파벳 소문자들로 구성
// 최소 한 개의 모음(aeiou)과 최소 두 개의 자음으로 구성되어 있음
// 알파벳은 오름차순

// 암호로 사용했을 법한 문자 종류 C가지
// C가지를 조합하여 가능성 있는 암호를 모두 구하는 프로그램
// 시간제한 2초라서 그냥 부분집합으로 모든 경우의 수에 대해 가능한지 체크하면 될 듯?

public class Main {
	static int L;
	static int C;
	static boolean[] selected;
	static String[] srr;
	static StringBuilder sb = new StringBuilder();
	static StringBuilder result = new StringBuilder();
	
	static void subSet(int cnt) {
		if (cnt == C) {
			sb.setLength(0);
			for (int i = 0; i < C; i++) {
				if (selected[i]) sb.append(srr[i]);
			}
			check();
			return;
		}
		
		selected[cnt] = true;
		subSet(cnt + 1);
		selected[cnt] = false;
		subSet(cnt + 1);
	}
	
	static void check() {
//		모음 개수
		int vowel = 0;
		String sbStr = sb.toString();
//		길이가 L인지 검사
//		L 이상인 줄 알고 부분집합으로 했는데 L이었음 -> 조합인 듯..
		if (sbStr.length() == L) {
			if (sbStr.contains("a")) vowel++;
			if (sbStr.contains("e")) vowel++;
			if (sbStr.contains("i")) vowel++;
			if (sbStr.contains("o")) vowel++;
			if (sbStr.contains("u")) vowel++;
//			모음이 1개 이상이고 자음이 2개 이상
			if (vowel >= 1 && (L - vowel) >= 2) result.append(sbStr).append("\n");
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		srr = br.readLine().split(" ");
		L = Integer.parseInt(srr[0]);
		C = Integer.parseInt(srr[1]);
		srr = br.readLine().split(" ");
		Arrays.sort(srr);
		
		selected = new boolean[C];
		
		subSet(0);
		if (result.length() > 0) {
			result.deleteCharAt(result.length() - 1);
			System.out.println(result);
		}
	}
}