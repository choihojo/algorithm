import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int temp = 1;
		int i = 1;
//		부등호에서 <=가 아니라 <임에 주의할 것
//		육각형 껍데기가 하나 추가될 때마다 거리가 1 추가됨
//		육각형 껍데기는 6의 배수만큼 증가함
		while (temp < N) {
			temp += 6 * i;
			i++;
		}
		System.out.println(i);
		
	}	
}