import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	static int[][] table;
	static int[] irr;
	static int[] orr;
	static int temp;
	static int total;
	static int min;
	static int diff;
	static List<Integer> list;
	
//	전체 중에 고른 절반의 맛들의 인덱스를 orr에 저장함
	public static void combination(int[] irr, int[] orr, int cnt, int start) {
		if (cnt == (N / 2)) {
			temp = 0;
//			조합했을 때의 맛을 더하여 temp에 저장함
			for (int i = 0; i < orr.length; i++) {
				for (int j = 0; j < orr.length; j++) {
					temp += table[orr[i]][orr[j]];
				}
			}
//			list에 temp를 추가함
//			list의 양쪽 원소가 서로 대칭되게 A음식과 B음식에 대한 맛으로 저장됨
			list.add(temp);
			return;
		}
		
		for (int i = start; i < irr.length; i++) {
			orr[cnt] = irr[i];
			combination(irr, orr, cnt + 1, i + 1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			table = new int[N][N];
			irr = new int[N];
			orr = new int[N / 2];
			total = 0;
			min = Integer.MAX_VALUE;
			list = new ArrayList<Integer>();
			
			for (int i = 0; i < N; i++) {
				String[] srr = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					table[i][j] = Integer.parseInt(srr[j]);
					total += table[i][j];
				}
			}

			for (int i = 0; i < N; i++) {
				irr[i] = i;
			}
			
			combination(irr, orr, 0, 0);
			
			int size = list.size();
			
//			서로 대칭되게 저장되어 있으므로 대칭된 원소끼리 빼줌
//			절반까지만 조사하면 전부를 조사한 것과 같음
			for (int i = 0; i < (size / 2); i++) {
				diff = Math.abs(list.get(i) - list.get(size - 1 - i));
				if (diff < min) {
					min = diff;
				}
			}
			
			sb.append("#").append(t).append(" ").append(min);
			if (t != T) {
				sb.append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
}