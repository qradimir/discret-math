import java.io.*;

public class dm1e {
	public static void main(String[] args) throws IOException, FileNotFoundException {
		BufferedReader in = new BufferedReader(new FileReader(new File("radixsort.in")));
		BufferedWriter out = new BufferedWriter(new FileWriter(new File("radixsort.out")));
		String s[] = in.readLine().split(" ");
		int n  = Integer.parseInt(s[0]);
		int m  = Integer.parseInt(s[1]);
		int k  = Integer.parseInt(s[2]);
		final int alpha = 26;
		String a[] = new String[n];
		String b[] = new String[n];
		int c[] = new int[alpha];
		for (int i = 0; i < n; i++) {
			a[i] = in.readLine();
		}
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < alpha; j++) {
				c[j] = 0;
			}
			for (int j = 0; j < n; j++) {
				int d = a[j].charAt(m - i - 1) - 'a';
				c[d]++;
			}
			int count = 0;
			for (int j = 0; j < alpha; j++) {
				int t = c[j];
				c[j] = count;
				count += t;
			}
			for (int j = 0; j < n; j++) {
				int d = a[j].charAt(m - i - 1) - 'a';
				b[c[d]] = a[j];
				c[d]++;
			}
			a = b.clone();
		}
		for (int i = 0; i < n; i++) {
			out.write(a[i]);
			out.newLine();
		}
		in.close();
		out.close();
	}
}
