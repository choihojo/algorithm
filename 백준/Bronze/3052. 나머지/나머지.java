import java.util.*;

public class Main {
	public static void main(String[] args) {
		HashSet<Integer> set = new HashSet<>();
		Scanner sc = new Scanner(System.in);
		int num = 0;
		for (int i = 0; i < 10; i++) {
			num = sc.nextInt();
			set.add(num % 42);
		}
		System.out.println(set.size());
	}
}
