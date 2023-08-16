import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static boolean[][] input;
	static int black = 0;
	static StringBuilder sb;
	static void quadTree(int size, int[] start) {
//		재귀 돌리기 전에 black 초기화
		black = 0;
		
		for (int i = start[0]; i < start[0] + size; i++) {
			for (int j = start[1]; j < start[1] + size; j++) {
				if (input[i][j]) {
					black++;
				}
			}
		}
//		black의 값이 size^2이면 모두 black이라는 뜻임
		if (black == (size * size)) {
			sb.append(1);
		}
//		black의 값이 0이면 모두 white라는 뜻임
		else if (black == 0) {
			sb.append(0);
		}
		else {
//			모두 black이거나 white가 아니면 분할 정복
//			괄호를 어떻게 넣을지가 이 문제의 핵심인 듯
//			가장 쉬운 경우부터 생각해보면 됨
//			11110000
//			11110000
//			11110000
//			11110000
//			00001111
//			00001111
//			00001111
//			00001111 이런 식의 8*8 인풋이 있다고 했을 때 결과는 (1001)이 나와야 함
//			한마디로 4개로 쪼개지기 전에 한 덩어리의 양쪽에 괄호가 붙어야 된다는 뜻임
//			즉 쪼개지기 전에 여는 괄호를 넣고, 쪼개지고 나서(4개의 재귀가 종료되고) 닫는 괄호를 넣으면 됨
			sb.append("(");
			quadTree(size / 2, new int[] {start[0], start[1]});
			quadTree(size / 2, new int[] {start[0], start[1] + size / 2});
			quadTree(size / 2, new int[] {start[0] + size / 2, start[1]});
			quadTree(size / 2, new int[] {start[0] + size / 2, start[1] + size / 2});
			sb.append(")");
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = new boolean[N][N];
		String str;
		
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			for (int j = 0; j < N; j++) {
				input[i][j] = str.charAt(j) == '1' ? true : false;
			}
		}
		sb = new StringBuilder();
		quadTree(N, new int[] {0, 0});
		System.out.println(sb);
	}
}