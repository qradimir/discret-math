import java.io.*;

public class dm1c {
	
	public static void swap(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	public static int partition(int[] a, int l, int r ) {
		if (l == r) { return l;}
        int i = l - 1;
        int j = r;
        while (true) {
            while (a[++i] < a[r]) {
            	;
            }                               
            while (a[r] < a[--j] && j > l) {
            	;
            }
            if (i >= j) { 
            	break;   
            }
            swap(a, i, j);                     
        }
        swap(a, i, r);                     
        return i;
	}
	
	public static int kth(int[] a, int k) {
		int l = 0;
		int r = a.length - 1;
		while (true) {
			int q = partition(a, l, r);
			if (k < q) {
				r = q - 1;
				continue;
			} 
			if (q < k) {
				l  = q + 1;
				continue;
			}  
			return a[k];
		}
	}

	public static void main(String[] args) 
			throws IOException, FileNotFoundException {
		BufferedReader in = new BufferedReader(new FileReader(new File("kth.in")));
		BufferedWriter out = new BufferedWriter(new FileWriter(new File("kth.out")));
		String s[] = in.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int k = Integer.parseInt(s[1]);
		int[] a = new int[n];
		s = in.readLine().split(" ");
		int qA = Integer.parseInt(s[0]);
		int qB = Integer.parseInt(s[1]);
		int qC = Integer.parseInt(s[2]);
		a[0] = Integer.parseInt(s[3]);
		if (n > 1) {a[1] = Integer.parseInt(s[4]);}
		for (int i = 2; i < n; i++) {
			a[i] = qA * a[i - 2] + qB * a[i - 1] + qC;
		}
		int ans = kth(a, k - 1);
		out.write(Integer.toString(ans));	
		in.close();
		out.close();
	}

}

