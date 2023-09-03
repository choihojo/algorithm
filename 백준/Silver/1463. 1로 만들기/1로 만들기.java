import java.io.*;
import java.util.*;

//	DP를 연습하기 좋은 문제임
//	1. 최적 부분구조 : x = (x - 1) + 1 || (x / 2) + 1 || (x / 3) + 1
//	2. 중복 부분문제 : 특정 x에 대해 연산의 최솟값을 구했다면 그 최솟값이 다른 더 큰 문제를 풀 때 활용될 수 있음

public class Main {
	static int X;
	static int[] dp;
	
//	1. 반환값이 없는 하향식
	static void dfsVoid(int x) {
		if (x == 1) return;
		if (dp[x] == -1) {
			dfsVoid(x - 1);
			dp[x] = dp[x - 1] + 1;
			if (x % 2 == 0) {
				dfsVoid(x / 2);
				dp[x] = Math.min(dp[x], dp[x / 2] + 1);
			}
			if (x % 3 == 0) {
				dfsVoid(x / 3);
				dp[x] = Math.min(dp[x], dp[x / 3] + 1);
			}
		}
	}
	
//	2. 반환값이 있는 하향식
	static int dfsReturn(int x) {
		if (x == 1) return 0;
		if (dp[x] == -1) {
			dp[x] = dfsReturn(x - 1) + 1;
			if (x % 2 == 0) {
//				번외로 Math.min(dfsReturn(a), dfsReturn(b))의 형태가 될 경우 더 빨리 끝나는 것이 앞에 오는 것이 유리함
//				예를 들어 Math.min(dfsReturn(x - 1) + 1, dfsReturn(x / 2) + 1)보다는 Math.min(dfsReturn(x / 2) + 1, dfsReturn(x - 1) + 1)이 더 빠른 것임
//				dfsReturn(x - 1)을 수행하고 나면 상대적으로 스택에 더 많은 메서드가 쌓인 상태에서(깊어짐) dfsReturn(x / 2)를 수행하는 것이라 x가 클수록 속도 차이가 심해짐
				dp[x] = Math.min(dp[x], dfsReturn(x / 2) + 1);
			}
			if (x % 3 == 0) {
				dp[x] = Math.min(dp[x], dfsReturn(x / 3) + 1);
			}
		}
		return dp[x];
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		X = Integer.parseInt(br.readLine());
//		인덱싱 편하게 하기 위해 1 크게 설정하고 -1로 초기화함
//		dp[n]은 n을 1로 만드는 데 드는 연산의 최솟값임
		dp = new int[X + 1];
		for (int i = 1; i <= X; i++) {
			dp[i] = -1;
		}
		dp[1] = 0;
		
//		1. 반환값이 없는 하향식
//		아래 조건문이 하향식의 핵심인데 이렇게 하면 재귀를 깊게 들어가지 않고 결과를 도출할 수 있음
//		연산횟수 자체는 비슷하더라도 스택에 수많은 메서드가 쌓인 채 다른 연산을 수행하는 것과 스택의 공간이 여유로울 때 다른 연산을 수행하는 건 속도 차이가 유의미함
//		for (int i = 1; i <= X; i++) {
//			dfsVoid(i);
//		}
//		System.out.println(dp[X]);
		
//		2. 반환값이 있는 하향식
//		for (int i = 1; i <= X; i++) {
//			dfsReturn(i);
//		}
//		System.out.println(dfsReturn(X));
		
//		3. 상향식
		for (int i = 2; i <= X; i++) {
			dp[i] = dp[i - 1] + 1;
			if (i % 2 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 2] + 1);
			}
			if (i % 3 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 3] + 1);
			}
		}
		System.out.println(dp[X]);
	}
}
