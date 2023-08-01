import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] srr = br.readLine().split(" ");
        int[] irr = new int[n];
        for (int i = 0; i < srr.length; i++) {
            irr[i] = Integer.parseInt(srr[i]);
        }
        int k = Integer.parseInt(br.readLine());
        String[] srr2 = new String[2];
        int pos = 0;
        int cnt = 0;
        for (int i = 0; i < k; i++) {
        	cnt = 0;
            srr2 = br.readLine().split(" ");
            if (srr2[0].equals("1")) {
                for (int idx = Integer.parseInt(srr2[1]) - 1; idx < n; idx += Integer.parseInt(srr2[1])) {
                    if (irr[idx] == 1) {
                        irr[idx] = 0;
                    }
                    else {
                        irr[idx] = 1;
                    }
                }
            }
            else {
                pos = Integer.parseInt(srr2[1]) - 1;
                for (int j = 1; j < n; j++) {
                    if ((pos + j < n) && (pos - j) >= 0) {
                        if (irr[pos + j] == irr[pos - j]) {
                            cnt++;
                        }
                        else {
                            break;
                        }
                    }
                    else {
                    	break;
                    }
                }
                for (int l = cnt; l > 0; l--) {
                    if (irr[pos + l] == 0) {
                        irr[pos + l] = 1;
                    }
                    else {
                        irr[pos + l] = 0;
                    }
                    if (irr[pos - l] == 0) {
                    	irr[pos - l] = 1;
                    }
                    else {
                    	irr[pos - l] = 0;
                    }
                }
                if (irr[pos] == 0) {
                    irr[pos] = 1;
                }
                else {
                    irr[pos] = 0;
                }
            }
        }
        for (int e = 0; e < irr.length; e++) {
        	if ((e + 1) % 20 != 0 && e != irr.length - 1) {
            	System.out.print(irr[e] + " ");
        	}
        	else {
        		System.out.print(irr[e]);
        	}
        	if ((e + 1) % 20 == 0) {
        		System.out.println("");
        	}
        }
    }
}