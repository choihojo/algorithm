import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine());
    	boolean[][] map = new boolean[100][100];
    	int size = 0;
    	for (int i = 0; i < n; i++) {
    		String[] srr = br.readLine().split(" ");
    		int a = Integer.parseInt(srr[0]);
    		int b = Integer.parseInt(srr[1]);
    		for (int j = b; j < b + 10; j++) {
    			for (int k = a; k < a + 10; k++) {
    				map[k][j] = true;
    			}
    		}
    	}
    	for (boolean[] brr : map) {
    		for (boolean b : brr) {
    			if (b) {
    				size++;
    			}
    		}
    	}
    	System.out.println(size);
    }
}