import java.io.*;
import java.util.*;

// 부여 번호는 1 이상 100 이하 (중간에 번호가 비어있을 수 있음)
// 다자 간 통화를 통해 동시에 전달
// 연락받을 수 없는 사람도 존재함
// 입력받는 데이터는 from, to, from, to, ... 순서로 됨
// 일단 예시만 보면 인접행렬보다는 연결리스트로 푸는 것이 적절해보임

public class Solution {
    static int length;
    static int start;
    static List<List<Integer>> graph;
    static boolean[] visited;
    static int max;
    static List<Integer> last;
	
    static void bfs() {
    	Queue<Integer> queue = new ArrayDeque<>();
		visited = new boolean[101];
		
    	queue.offer(start);
    	visited[start] = true;
    	last = new ArrayList<>();
    	
    	int poll;
    	int size;
    	
    	while (!queue.isEmpty()) {
//    		depth 측정을 위한 큐의 사이즈
    		size = queue.size();
    		
    		for (int s = 0; s < size; s++) {
    			poll = queue.poll();
    			for (int e : graph.get(poll)) {
    				if (!visited[e]) {
    					visited[e] = true;
    					queue.offer(e);
    				}
    			}
//    			해당 depth에서 poll한 것들 중에서 최댓값을 저장함
    			last.add(poll);
    		}
    		
    		max = -1;
			for (int e : last) {
//	    		depth 1에서 poll한 것들 중에서 최댓값, 2에서 poll한 것들 중에 최댓값, ... 이런 식으로 갱신해나감
    			if (e > max) max = e;
			}
//    		depth 하나 탐색이 끝났으면 리스트를 초기화해줌
    		last.clear();
    	}
    }
    
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= 10; t++) {
			String[] srr = br.readLine().split(" ");
			length = Integer.parseInt(srr[0]);
			start = Integer.parseInt(srr[1]);
			graph = new ArrayList<>();
//			인덱싱 편하게 하기 위해서 1 크게 설정
			for (int i = 0; i <= 100; i++) {
				graph.add(new ArrayList<>());
			}
			
			srr = br.readLine().split(" ");
			for (int i = 0; i < length; i += 2) {
				int from = Integer.parseInt(srr[i]);
				int to = Integer.parseInt(srr[i + 1]);
				graph.get(from).add(to);
			}
			
			bfs();
			
			sb.append("#").append(t).append(" ").append(max).append("\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}
}