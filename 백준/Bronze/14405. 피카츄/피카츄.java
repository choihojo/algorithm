import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static String S;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		S = br.readLine();
		
		int start = 0;
		
		boolean flag = true;
		
		while (start < S.length()) {
			if (S.charAt(start) == 'p') {
				start++;
				if (start < S.length() && S.charAt(start) == 'i') {
					start++;
					continue;
				}
				else {
					flag = false;
					break;
				}
			}
			else  if (S.charAt(start) == 'k') {
				start++;
				if (start < S.length() && S.charAt(start) == 'a') {
					start++;
					continue;
				}
				else {
					flag = false;
					break;
				}
			}
			else  if (S.charAt(start) == 'c') {
				start++;
				if (start < S.length() && S.charAt(start) == 'h') {
					start++;
					if (start < S.length() && S.charAt(start) == 'u') {
						start++;
						continue;
					}
					else {
						flag = false;
						break;
					}
				}
				else {
					flag = false;
					break;
				}
			}
			else {
				flag = false;
				break;
			}
		}
		
		System.out.println(flag ? "YES" : "NO");
	}
}