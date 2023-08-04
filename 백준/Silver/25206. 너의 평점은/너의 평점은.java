
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	double sum = 0;
    	double score = 0;
    	for (int i = 0; i < 20; i++) {
    		String[] srr = br.readLine().split(" ");
    		if (srr[2].equals("A+")) {
    			score += Double.parseDouble(srr[1]);
    			sum += Double.parseDouble(srr[1]) * 4.5;
    		}
    		else if (srr[2].equals("A0")) {
    			score += Double.parseDouble(srr[1]);
    			sum += Double.parseDouble(srr[1]) * 4.0;
    		}
    		else if (srr[2].equals("B+")) {
    			score += Double.parseDouble(srr[1]);
    			sum += Double.parseDouble(srr[1]) * 3.5;
    		}
    		else if (srr[2].equals("B0")) {
    			score += Double.parseDouble(srr[1]);
    			sum += Double.parseDouble(srr[1]) * 3.0;
    		}
    		else if (srr[2].equals("C+")) {
    			score += Double.parseDouble(srr[1]);
    			sum += Double.parseDouble(srr[1]) * 2.5;
    		}
    		else if (srr[2].equals("C0")) {
    			score += Double.parseDouble(srr[1]);
    			sum += Double.parseDouble(srr[1]) * 2.0;
    		}
    		else if (srr[2].equals("D+")) {
    			score += Double.parseDouble(srr[1]);
    			sum += Double.parseDouble(srr[1]) * 1.5;
    		}
    		else if (srr[2].equals("D0")) {
    			score += Double.parseDouble(srr[1]);
    			sum += Double.parseDouble(srr[1]) * 1.0;
    		}
    		else if (srr[2].equals("F")) {
    			score += Double.parseDouble(srr[1]);
    			sum += 0;
    		}
    	}
    	System.out.println(sum / score);
    	
    }
}