import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int N;
//	이빨 찍힌 자국이 있는 위치를 저장하는 teeth List
	static int[] teeth;
//	이빨 찍힌 자국이 있는 위치를 저장하는 visited Set (vSet)
	static Set<Integer> vSet;
//	가능한 이빨 간격 후보를 저장하는 k Set (kSet)
	static Set<Integer> kSet;
//	모든 검사를 통과한 진짜 가능한 이빨 간격을 저장하는 list
	static List<Integer> list;
	static int cnt;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		teeth = new int[N];
		vSet = new HashSet<>();
		kSet = new HashSet<>();
		list = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		for (int n = 0; n < N; n++) {
			teeth[n] = Integer.parseInt(st.nextToken());
			vSet.add(teeth[n]);
		}
//		이 부분이 가장 중요한데 여기서 이중 루프를 돌 필요가 없다는 것임
//		언뜻 생각하면 0에서 1, 0에서 2, ... 0에서 N-1까지 고려한 뒤 1에서 2, 1에서 3, 이런 식으로 이중 루프를 돌아야 된다고 생각할 수 있지만 이러면 경우가 너무 많아짐 (이래도 통과하긴 함)
//		잘 생각해보면 어떤 이빨이 찍힌 기준점이 있을 때 반드시 그 기준점과 짝을 이루는 다른 이빨 찍힌 위치가 존재해야 함
//		결국 이빨 간격으로 가능한 후보는 이빨이 찍힌 어떤 위치 하나를 기준으로 잡고 다른 모든 이빨이 찍힌 위치와의 거리를 넣어주면 되는 것임
		for (int i = 1; i <= N / 2; i++) {
			kSet.add(teeth[i] - teeth[0]);
		}
//		가능한 이빨 간격을 하나 가져와서 기존 이빨이 찍혀 있는 모든 위치에서 +-k한 위치가 찍혀있는지 조사함
//		왜냐하면 이빨 자국은 하나만 찍히는 것이 아니라 반드시 짝이 있기 때문임
//		그렇기 때문에 그 어떤 위치를 기준으로 잡든 해당 위치에서 +k한 위치 혹은 -k한 위치에 이빨 자국이 찍혀있어야 함
		for (int k : kSet) {
			boolean flag = true;
			for (int n = 0; n < N; n++) {
				if (vSet.contains(teeth[n] - k)) continue;
				if (vSet.contains(teeth[n] + k)) continue;
				flag = false;
				break;
			}
//			도중에 어떤 기준점에서 +-k한 위치에 이빨 자국이 찍혀있지 않을 경우에는 flag가 false일 것이고 끝까지 루프를 돌았는데 모두 +-k한 위치에 이빨 자국이 남아있었다면 flag가 true임
//			만약 flag가 true로 남아있다면 이빨 자국 후보 k를 검사를 통과한 이빨 간격을 모아두는 list에 추가하고 cnt를 증가시킴 (cnt를 굳이 안 쓰고 나중에 list.size() 써도 되긴 함)
			if (flag) {
				list.add(k);
				cnt++;
			}
		}
		Collections.sort(list);
		sb.append(cnt).append("\n");
		for (int k : list) {
			sb.append(k).append(" ");
		}
		System.out.println(sb);
	}
}