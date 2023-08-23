import java.io.*;
import java.util.*;

// 기본적인 위상정렬 문제
// N명의 학생들을 키 순서대로 줄을 세움
// M은 키를 비교한 횟수
// A B로 입력이 주어지면 A가 B의 앞에 서야 한다는 의미 : A -> B로 가리켜야 할 듯
// 학생들의 번호는 1번 ~ N번

public class Main {
	static int N;
	static int M;
//	진입차수 배열
	static int[] inDegree;
//	한 학생이 가리키는 사람이 여러 명일 수도 있으므로 리스트로 유향그래프 설계
	static List<List<Integer>> list;
//	사이클이 있는지 확인하려고 사용하는 변수 (그냥 심심해서)
	static int cnt;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr = br.readLine().split(" ");
		N = Integer.parseInt(srr[0]);
		M = Integer.parseInt(srr[1]);
		
//		인덱싱용으로 1 크게 설정
		inDegree = new int[N + 1];
		list = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}
		
		for (int m = 0; m < M; m++) {
			srr = br.readLine().split(" ");
			int from = Integer.parseInt(srr[0]);
			int to = Integer.parseInt(srr[1]);
			list.get(from).add(to);
			inDegree[to]++;
		}
		
		StringBuilder sb = new StringBuilder();
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				queue.offer(i);
				sb.append(i).append(" ");
				cnt++;
			}
		}
		
		int poll;
		while (!queue.isEmpty()) {
			poll = queue.poll();
			for (int e : list.get(poll)) {
				inDegree[e]--;
				if (inDegree[e] == 0) {
					queue.offer(e);
					sb.append(e).append(" ");
					cnt++;
				}
			}
		}
		
//		N이 아니면 사이클이 존재한다는 뜻 (애초에 사이클이 존재하는 테케는 없겠지만)
		if (cnt == N) {
			sb.deleteCharAt(sb.length() - 1);
			System.out.println(sb);
		}
	}
}
