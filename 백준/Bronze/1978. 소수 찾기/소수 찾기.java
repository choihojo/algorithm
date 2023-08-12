import java.io.*;

public class Main {
	
	public static boolean isPrime(int n) {
		if (n == 1) {
			return false;
		}
		
		boolean[] checked = new boolean[n];
		
//		1과 자기자신은 제외해야 하므로 미리 false로 설정
//		약수면 true로 체크할 예정
		checked[0] = false;
		checked[n - 1] = false;
		
//		false의 개수를 세는 cnt는 0으로 초기화;
//		false가 cnt개라면 소수임
//		만약 temp가 n의 약수가 아니라면 그대로 false로 남아있을 것임
//		그러므로 0번째부터 (n - 1)번째까지 모두 false면 소수라고 판단할 수 있음
		int cnt = 0;
		
//		에라토스테네스의 체 알고리즘에서 배수로 쓸 변수 선언
		int temp = 0;
		
//		i는 인덱스고 temp는 실제 원소의 값을 의미함
//		2에서 (n - 1)까지만 조사하면 됨
		for (int i = 1; i < n; i++) {
//			만약 checked[i]가 false라면 조건문에 진입
			if (!checked[i]) {
				temp = (i + 1);
//				만약 i번째 요소의 값인 temp가 n의 약수라면 조건문에 진입하고 체크
				if (n % temp == 0) {
//					temp의 배수의 인덱스들 모두 true로 체크
					while (temp < n) {
						checked[temp - 1] = true;
						temp += (i + 1);
					}
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			if (!checked[i]) {
				cnt++;
			}
		}
		
		return (cnt == n);
	}
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] srr = br.readLine().split(" ");
		int[] irr = new int[N];
		for (int i = 0; i < N; i++) {
			irr[i] = Integer.parseInt(srr[i]);
		}
		
		int prime = 0;
		for (int i = 0; i < N; i++) {
			if (isPrime(irr[i])) {
				prime++;
			}
		}
		
		System.out.println(prime);
	}
}