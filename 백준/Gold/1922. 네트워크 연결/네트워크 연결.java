import java.io.*;
import java.util.*;

// MST 연습하는 문제
// 크루스칼 알고리즘 이용

public class Main {
	static int N;
	static int M;
//	크루스칼 알고리즘은 간선 중심 알고리즘이므로 간선 리스트를 생성함
	static List<Edge> list;
//	크루스칼 알고리즘은 union-find를 이용함
	static int[] parents;
	static int sum;
	static int cnt;
	
	static void make() {
//		인덱싱 편하게 하기 위해 1 크게 설정함
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if (parents[a] == a) return a;
//		int rootA = find(parents[a]);
//		parents[a] = rootA;
//		return rootA;
//		아래 1줄은 위 3줄과 동일한 의미임
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
//		만약 rootA와 rootB가 이미 같았다면 false 반환함
//		루트가 같은데 합치면 사이클이 발생해서 MST 생성이 안됨
		if (rootA == rootB) return false;
//		rootB는 지금 최상단이므로 자기자신이 부모인데 rootA 하위에 붙여줌
		parents[rootB] = rootA;
		return true;
	}
	
	static void kruskal() {
		make();
		cnt = 0;
		sum = 0;
		for (int i = 0; i < list.size(); i++) {
			int from = list.get(i).from;
			int to = list.get(i).to;
			int weight = list.get(i).weight;
			if (union(from, to)) {
				sum += weight;
				cnt++;
			}
//			간선의 개수이므로 N이 아니라 (N - 1)일 때 탈출하는 것에 유의할 것
			if (cnt == (N - 1)) break;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
//		간선 입력받아서 저장함
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list.add(new Edge(from, to, weight));
		}
//		간선 오름차순으로 정렬함
		Collections.sort(list, (o1, o2) -> (o1.weight > o2.weight ? 1 : (o1.weight == o2.weight ? 0 : -1)));
		kruskal();
		System.out.println(sum);
	}
}

class Edge {
	int from;
	int to;
	int weight;
	public Edge(int from, int to, int weight) {
		super();
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
}