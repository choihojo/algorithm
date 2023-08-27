
import java.io.*;
import java.util.*;
import java.util.function.*;

public class Main {
	static int[][] map;
	static int N;
	static int[] minEdge;
	static int result;
	static boolean[] visited;
	static int[] idx;
	static int cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Function<String,Integer> stoi = Integer::parseInt;
        N = stoi.apply(st.nextToken());
        map = new int[N][N];
        result = 0;
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                map[i][j] = stoi.apply(st.nextToken());
                if(map[i][j] < 0){
                    result -= map[i][j];
                    map[i][j] = 0;
                }
            }
        }
        result /= 2;
        prim();
    }

    private static void prim() {
        minEdge = new int[N];
        idx = new int[N];
        Arrays.fill(minEdge, Integer.MAX_VALUE);
        minEdge[0] = 0;
        visited = new boolean[N];
        Queue<Vertex> pQueue = new PriorityQueue<>((o1, o2) -> (o1.weight > o2.weight ? 1 : (o1.weight == o2.weight ? 0 : -1)));
        pQueue.offer(new Vertex(0, 0));
        Vertex poll = null;
        int pNo = 0;
        int pWeight = 0;
        StringBuilder sb = new StringBuilder();
        
        while (!pQueue.isEmpty()) {
        	poll = pQueue.poll();
        	pNo = poll.no;
        	pWeight = poll.weight;
        	
        	if (visited[pNo]) continue;
        	
        	visited[pNo] = true;
        	result += Math.abs(pWeight);
        	
        	if (pWeight > 0) {
            	cnt++;
            	sb.append(idx[pNo] + 1).append(" ").append(pNo + 1).append("\n");
        	}
        	
        	for (int i = 0; i < N; i++) {
        		if (!visited[i] && map[pNo][i] < minEdge[i]) {
        			minEdge[i] = map[pNo][i];
                	pQueue.offer(new Vertex(i, map[pNo][i]));
                	idx[i] = pNo;
        		}
        	}
        }
        
        System.out.println(result + " " + cnt);
        if (sb.length() != 0) {
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb);
        }
    }
}

class Vertex {
	int no;
	int weight;
	public Vertex(int no, int weight) {
		super();
		this.no = no;
		this.weight = weight;
	}
}