import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] srr = br.readLine().split(" ");
        int s = Integer.parseInt(srr[0]);
        int p = Integer.parseInt(srr[1]);
        String dna = br.readLine();
        srr = br.readLine().split(" ");
        int numA = 0;
        int numC = 0;
        int numG = 0;
        int numT = 0;
        int boundA = Integer.parseInt(srr[0]);
        int boundC = Integer.parseInt(srr[1]);
        int boundG = Integer.parseInt(srr[2]);
        int boundT = Integer.parseInt(srr[3]);
        int cnt = 0;
        
        for (int i = 0; i < p; i++) {
        	if (dna.charAt(i) == 'A') {
        		numA++;
        	}
        	else if (dna.charAt(i) == 'C') {
        		numC++;
        	}
        	else if (dna.charAt(i) == 'G') {
        		numG++;
        	}
        	else if (dna.charAt(i) == 'T') {
        		numT++;
        	}
        }
    	if (numA >= boundA && numC >= boundC && numG >= boundG && numT >= boundT) {
    		cnt++;
    	}
    	
        for (int i = 0; i < (s - p); i++) {
        	if (dna.charAt(i) == 'A') {
        		numA--;
        	}
        	else if (dna.charAt(i) == 'C') {
        		numC--;
        	}
        	else if (dna.charAt(i) == 'G') {
        		numG--;
        	}
        	else if (dna.charAt(i) == 'T') {
        		numT--;
        	}
        	if (dna.charAt(p + i) == 'A') {
        		numA++;
        	}
        	else if (dna.charAt(p + i) == 'C') {
        		numC++;
        	}
        	else if (dna.charAt(p + i) == 'G') {
        		numG++;
        	}
        	else if (dna.charAt(p + i) == 'T') {
        		numT++;
        	}
        	if (numA >= boundA && numC >= boundC && numG >= boundG && numT >= boundT) {
        		cnt++;
        	}
        }

        System.out.println(cnt);

    }
}