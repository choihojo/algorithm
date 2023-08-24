import java.io.*;
import java.util.*;


// 벨트 위에는 같은 종류의 초밥이 둘 이상 가능
// k개의 접시를 연속해서 먹을 경우 쿠폰에 적힌 초밥 하나를 무료로 제공
// 쿠폰 초밥은 벨트 위에 없더라도 직접 만들어서 제공함
// N : 벨트에 놓인 접시의 수 (<= 3,000,000)
// d : 초밥의 가짓수 (<= 3,000)
// k : 연속해서 먹는 접시의 수 (<= 3000)
// c : 쿠폰 번호 c (<= d)
// 초밥 번호는 1 이상 d 이하의 정수

// DNA 문자열 문자하고 비슷한 듯?
// d의 크기를 가지는 배열을 만들고 k 길이의 덩어리를 한 칸씩 옮겨가면서 가짓수를 더하거나 빼주면 될 듯
// 그런데 주의할 점은 원형이니까 -k번째쯤부터 존재하는 것도 신경써줘야 됨
// 쿠폰 번호는 처음에 해당 인덱스의 값에 1을 더해주고 시작하면 됨

public class Main {
	static int N, d, k, c;
	static int[] dish;
	static int cnt = 0;
	static int max = 0;
//	원형 고려하기 위한 1부터 k까지 저장 배열
	static int[] save;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr = br.readLine().split(" ");
		N = Integer.parseInt(srr[0]);
		d = Integer.parseInt(srr[1]);
		k = Integer.parseInt(srr[2]);
		c = Integer.parseInt(srr[3]);
		
//		인덱싱 편하게 하기 위해서 + 1
//		dish는 해당 종류의 초밥이 몇 개나 나왔는지 저장하는 배열임
		dish = new int[d + 1];
//		save는 나왔던 초밥을 저장하는 배열임
		save = new int[N + 1];
		
//		쿠폰 초밥 미리 추가
		dish[c]++;
		cnt++;
		
		for (int i = 1; i <= N; i++) {
			save[i] = Integer.parseInt(br.readLine());
		}
		
//		처음 시작 k 길이
		for (int i = 1; i <= k; i++) {
//			처음에 새로운 종류의 초밥을 추가하는 경우 가짓수(cnt)++
			if (dish[save[i]] == 0) cnt++;
			dish[save[i]]++;
		}
		
//		max 초기화
		max = cnt;
		
		for (int i = (k + 1); i <= N; i++) {
//			한 칸씩 이동할 때 없어지는 초밥의 수가 1개였을 경우 가짓수(cnt)--
			if (dish[save[i - k]] == 1) cnt--;
			dish[save[i - k]]--;
			if (dish[save[i]] == 0) cnt++;
			dish[save[i]]++;
			if (cnt > max) max = cnt;
		}
		
//		위 과정까지 하면 1~k, 2~(k+1), ..., (N-k+1)~N까지 탐색했다는 뜻임
//		이제는 원형을 고려해서 (N-k+2)~1, ..., N~(k-1)까지 탐색해야 함
		for (int i = 1; i <= (k - 1); i++) {
			if (dish[save[N - k + i]] == 1) cnt--;
			dish[save[N - k + i]]--;
			if (dish[save[i]] == 0) cnt++;
			dish[save[i]]++;
			if (cnt > max) max = cnt;
		}
		
		System.out.println(max);
	}
}