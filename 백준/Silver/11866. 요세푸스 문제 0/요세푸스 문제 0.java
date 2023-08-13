import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr = br.readLine().split(" ");
		int N = Integer.parseInt(srr[0]);
		int K = Integer.parseInt(srr[1]);
		
		int[] irr = new int[N];
		int idx = (K - 1);
		
//		모두 제거됐는지 확인하기 위한 변수 cnt
		int cnt = 0;
		
//		제거된 사람을 건너뛰기 위한 변수 num
//		처음에는 바로 제거하면 되므로 num을 K로 초기화함
//		제거하면 num은 1로 초기화됨
		int num = K;
		
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		while (cnt < N) {
//			이 문제에서는 굳이 while을 할 필요가 없긴 하지만 N보다 K가 더 큰 경우도 고려하기 위해 넣음
			while (idx >= N) {
				idx -= N;
			}
			if (irr[idx] == 0 && num != K) {
				num++;
				idx++;
			}
			else if (irr[idx] == 0 && num == K) {
				irr[idx] = 1;
				sb.append(idx + 1).append(", ");
//				한번에 idx에 K를 더해주면 도중에 제거된 사람이 있었는지 알기 힘듦
//				따라서 1씩 더해주는 방식을 취함
//				irr[idx]가 0이고 num이 K가 아니었을 때만 num과 idx가 증가함
//				만약 irr[idx]가 0이 아니면(1) 이미 제거됐다는 뜻이므로 idx만 증가시킴
				idx++;
				cnt++;
				num = 1;
			}
			else {
				idx += 1;
			}
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append(">");
		
		System.out.println(sb.toString());
	}		
//	1 2 3
}