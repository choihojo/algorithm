import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int M;
	static int[] listN;
	static int[] listM;
	static boolean[] output;
	
//	이진 탐색
	static void binarySearch(int idx, int start, int end) {
//		끝내 값을 찾지 못한 경우
		if (start > end) {
			output[idx] = false;
			return;
		}
//		값을 찾은 경우
		if (listM[idx] == listN[(start + end) / 2]) {
			output[idx] = true;
			return;
		}
//		찾고자 하는 값이 중간값보다 작으면 왼쪽 범위로 좁힘
		else if (listM[idx] < listN[(start + end) / 2]) {
			binarySearch(idx, start, (start + end) / 2 - 1);
		}
//		찾고자 하는 값이 중간값보다 크면 오른쪽 범위로 좁힘
		else {
			binarySearch(idx, (start + end) / 2 + 1, end);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr;
		N = Integer.parseInt(br.readLine());
		listN = new int[N];
		srr = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			listN[i] = Integer.parseInt(srr[i]);
		}
		
		M = Integer.parseInt(br.readLine());
		listM = new int[M];
		output = new boolean[M];
		srr = br.readLine().split(" ");
		for (int i = 0; i < M; i++) {
			listM[i] = Integer.parseInt(srr[i]);
		}
		
//		이진 탐색을 돌리려면 대상이 정렬된 상태여야 함
		Arrays.sort(listN);
		
//		정렬된 상태에서 M의 각 원소가 있는지 이진 탐색 수행
//		M 리스트는 정렬할 필요 없음
		for (int i = 0; i < M; i++) {
			binarySearch(i, 0, N - 1);
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < M; i++) {
			if (output[i]) sb.append(1);
			else sb.append(0);
			sb.append(" ");
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}
}