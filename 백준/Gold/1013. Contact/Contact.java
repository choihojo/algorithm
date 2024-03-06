import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static int T;
	static StringBuilder sb;
	
	/*
	 * (100+1+ | 01)+
	 * 1. (100+1+)+ -> 100...01...1
	 * 2. (01)+ -> 01...01
	 * 처음 시작할 때 0/1로 반드시 둘 중 한 경우로 나뉨'
	 * 근데 10011101이 있다고 할 때 마지막 구간을 01에서부터 읽을지 아니면 101로 읽을지 주의할 필요가 있음
	 */
	
	static boolean check(String s) {
		int idx = 0;
		
		while (idx < s.length()) {
			boolean flag = false;
			if (s.charAt(idx) == '1') {
				flag = true;
			}
			
			idx++;
			if (flag) {
				// 0 체크 (2개 이상)
				int cnt = 0;
				while (idx < s.length()) {
					if (s.charAt(idx) == '1') break;
					cnt++;
					idx++;
				}
				if (cnt < 2) {
					return false;
				}
				// 1 체크 (1개 이상)
				cnt = 0;
				while (idx < s.length()) {
					if (s.charAt(idx) == '0') break;
					cnt++;
					idx++;
				}
				if (cnt < 1) {
					return false;
				}
			} else {
				// 1 체크 (1개)
				int cnt = 0;
				while (idx < s.length()) {
					if (s.charAt(idx) == '0') break;
					else {
						cnt++;
						idx++;
						break;
					}
				}
				if (cnt != 1) {
					if ((idx - 3) >= 0) {
						if (s.charAt(idx - 2) == '1' && s.charAt(idx - 3) == '1') {
							idx -= 2;
							continue;
						}
						else {
							return false;
						}
					}
					else {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		
		for (int t = 0; t < T; t++) {
			sb.append(check(br.readLine()) ? "YES" : "NO").append("\n"); 
		}
		
		System.out.println(sb.deleteCharAt(sb.length() - 1));
	}
}