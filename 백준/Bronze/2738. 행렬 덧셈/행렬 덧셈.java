

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String[] srr = br.readLine().split(" ");
    	int N = Integer.parseInt(srr[0]);
    	int M = Integer.parseInt(srr[1]);
    	int[][] arr = new int[N][M];
    	int[][] brr = new int[N][M];
    	int[][] crr = new int[N][M];
    	
    	for (int i = 0; i < N; i++) {
    		srr = br.readLine().split(" ");
    		for (int j = 0; j < M; j++) {
    			arr[i][j] = Integer.parseInt(srr[j]);
    		}
    	}
    	
    	for (int i = 0; i < N; i++) {
    		srr = br.readLine().split(" ");
    		for (int j = 0; j < M; j++) {
    			brr[i][j] = Integer.parseInt(srr[j]);
    		}
    	}
    	
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < M; j++) {
    			crr[i][j] = (arr[i][j] + brr[i][j]);
    		}
    	}
    	
    	for (int[] i : crr) {
    		for (int ii : i) {
    			System.out.print(ii + " ");
    		}
    		System.out.println("");
    	}
    	
    }
}