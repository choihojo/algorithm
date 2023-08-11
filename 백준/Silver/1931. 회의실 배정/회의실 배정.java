import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] list = new int[N][2];
		for (int n = 0; n < N; n++) {
			String[] srr = br.readLine().split(" ");
			for (int i = 0; i < 2; i++) {
				list[n][i] = Integer.parseInt(srr[i]);
			}
		}
//		끝나는 시간으로만 정렬하면 안됨
//		끝나는 시간을 오름차순으로 정렬하고 만약 똑같다면 시작하는 시간도 오름차순으로 정렬해야함
		Arrays.sort(list, (o1, o2) -> {
			if (o1[1] == o2[1]) {
				return o1[0] - o2[0];
			}
			else {
				return o1[1] - o2[1];
			}});
		
		List<Integer> idx = new ArrayList<>();
		
//		정렬한 뒤 첫 번째 회의 선택함
		idx.add(0);
		int j = 0;
//		선택한 회의의 종료시간보다 빠른 시작시간을 가지는 회의는 무시함
//		선택한 회의의 종료시간과 시작시간이 같거나 늦은 회의를 선택함
		for (int i = 1; i < N; i++) {
			if (list[j][1] <= list[i][0]) {
				idx.add(i);
//				새로운 회의 선택함
//				S_ij = a_m U S_mj
				j = i;
			}
		}
		
		System.out.println(idx.size());
	}
}
