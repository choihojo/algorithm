import java.io.*;
import java.util.*;

// 암호는 서로 다른 L개의 알파벳 소문자들로 구성
// 최소 한 개의 모음(aeiou)과 최소 두 개의 자음으로 구성되어 있음
// 알파벳은 오름차순

// 암호로 사용했을 법한 문자 종류 C가지
// C가지를 조합하여 가능성 있는 암호를 모두 구하는 프로그램

public class Main {
	static int L;
	static int C;
	static String[] output;
	static String[] srr;
	static StringBuilder result = new StringBuilder();
	
//	C개의 알파벳 중에서 L개를 뽑는 조합
	static void combination(int cnt, int start) {
		if (cnt == L) {
//			뽑았으면 암호가 성립하는지 체크하는 메서드 호출
			check();
			return;
		}
		
		for (int i = start; i < C; i++) {
			output[cnt] = srr[i];
			combination(cnt + 1, i + 1);
		}
	}
	
	static void check() {
//		모음 개수
		int vowel = 0;
		String str = "";
		for (int i = 0; i < L; i++) {
			str += output[i];
		}
		if (str.contains("a")) vowel++;
		if (str.contains("e")) vowel++;
		if (str.contains("i")) vowel++;
		if (str.contains("o")) vowel++;
		if (str.contains("u")) vowel++;
//		모음이 1개 이상이고 자음이 2개 이상이면 결과에 추가
		if (vowel >= 1 && (L - vowel) >= 2) result.append(str).append("\n");
	}
	
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		srr = br.readLine().split(" ");
		L = Integer.parseInt(srr[0]);
		C = Integer.parseInt(srr[1]);
		srr = br.readLine().split(" ");
//		알파벳 오름차순으로 정렬
		Arrays.sort(srr);
		
		output = new String[L];
		
		combination(0, 0);
		
		if (result.length() > 0) {
			result.deleteCharAt(result.length() - 1);
			System.out.println(result);
		}
	}
}