import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int X = Integer.parseInt(br.readLine());
		int depth = 1;
		int cnt = 0;
		
		while (cnt < X) {
			cnt = depth * (depth + 1) / 2;
			depth++;
		}
//		X번째가 몇 번째 depth인지 구함
//		1/1을 기준으로 평면파가 퍼져나가는 형태
		depth--;
		
//		depth 바로 이전층까지 총 몇 개의 원소가 있는지 계산
		int temp = (depth - 1) * depth / 2;
		int diff = X - temp;
		
		int up = 0;
		int down = 0;
		
		if (depth % 2 == 0) {
			up = 1;
			down = depth;
			up += (diff - 1);
			down -= (diff - 1);
		}
		else {
			up = depth;
			down = 1;
			up -= (diff - 1);
			down += (diff - 1);
		}

		StringBuilder sb = new StringBuilder();
		sb.append(up).append("/").append(down);
		System.out.println(sb.toString());
	}	
}