import java.util.*;
import java.io.*;

// 기본적인 MST를 이용하는 문제
// union-find에 기반한 크루스칼 알고리즘 이용

public class Main {
	static int V;
	static int E;
	static int[] parents;
	static Edge[] eList;
//	가중치의 합을 저장할 변수
	static long sum;
//	몇 개의 노드를 연결했는지 저장할 변수
	static int cnt;
	
	static void make() {
//		인덱싱 편하게 하기 위해서 1 크게 초기화
		parents = new int[V + 1];
		for (int i = 1; i < parents.length; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int e) {
		if (parents[e] == e) return e;
//		최적화
//		find(e)가 아니라 find(parents[e])임을 유의할 것
		return parents[e] = find(parents[e]);
	}
	
	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB) return false;
		parents[rootB] = rootA;
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr = br.readLine().split(" ");
		V = Integer.parseInt(srr[0]);
		E = Integer.parseInt(srr[1]);
		
		eList = new Edge[E];
		
		for (int i = 0; i < E; i++) {
			srr = br.readLine().split(" ");
			int from = Integer.parseInt(srr[0]);
			int to = Integer.parseInt(srr[1]);
			int weight = Integer.parseInt(srr[2]);
			eList[i] = new Edge(from, to, weight);
		}
		
//		가중치에 대해 오름차순으로 정렬
		Arrays.sort(eList, (o1, o2) -> (o1.weight - o2.weight));
		
//		최소 단위 서로소 집합 생성
		make();
		
		for (int i = 0; i < E; i++) {
			Edge edge = eList[i];
			int from = edge.from;
			int to = edge.to;
			if (union(from, to)) {
				cnt++;
				sum += edge.weight;
			}
			if (cnt == (V - 1)) break;
		}
		
		if (cnt == (V - 1)) System.out.println(sum);
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