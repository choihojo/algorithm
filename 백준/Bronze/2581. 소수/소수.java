import java.io.*;

public class Main {
	
	public static boolean isPrime(int n) {
		if (n == 1) {
			return false;
		}
		
//		소수 판별에 쓸 배열 선언
//		2의 배수는 모두 판별했다고 체크, 3의 배수는 모두 판별했다고 체크, ... 이런 식
		boolean[] checked = new boolean[n];
		
//		에라토스테네스의 체 알고리즘에서 배수로 쓸 변수 선언
		int temp = 0;
		
//		범위가 중요한데 1과 자기자신을 의미하는 0번째 인덱스와 (n - 1)번째 인덱스는 순회하면 안 됨
		for (int i = 1; i < (n - 1); i++) {
//			만약 체크하지 않은 인덱스라면 조건문 진입
			if (!checked[i]) {
//				인덱스보다 1 큰 값이 거기에 저장된 값이므로 temp 값 설정
				temp = (i + 1);
				
//				만약 도중에 약수가 한번이라도 발견되면 바로 false 반환
				if (n % temp == 0) {
					return false;
				}
				else {
//					temp가 n보다 작은 동안 계속 루프 돌면서 temp의 배수는 약수 여부를 체크한 것이라 간주
					while (temp < n) {
						checked[temp - 1] = true;
						temp += (i + 1);
					}
				}
			}
		}
		
//		만약 (n - 1)번째까지 순회를 돌았음에도 불구하고 위에서 false가 반환되지 않았다면 약수인 셈이므로 true 반환
		return true;
	}
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		
		int min = 0;
		int sum = 0;
		int cnt = 0;
		for (int i = M; i <= N; i++) {
			if (isPrime(i)) {
//				만약 소수가 카운팅된 적 없으면 처음 발견된 소수를 min에 저장함
				if (cnt == 0) {
					min = i;
//					min 값이 이미 생긴 것이므로 cnt에다 1을 더해줘서 다음부터는 이 조건문을 통과하지 않도록 함
					cnt++;
				}
//				소수 합 갱신
				sum += i;
			}
		}
		if (sum == 0) {
			System.out.println(-1);
		}
		else {
			System.out.println(sum);
			System.out.println(min);
		}
	}
}