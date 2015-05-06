import java.io.*;

public class dm1a {
	
	public static void sort(int[] a, int l, int r) {
		if (r <= l) { return; }
		int x = a[(r - l) / 3  + l];
		int i = l;
		int j = r;
		while (true) {
			while (a[i] < x) {
				i++;
			}
			while (a[j] > x) {
				j--;
			}
			if (i < j) {
				int t = a[i]; a[i] = a[j]; a[j] = t;
				i++;
				j--;
			} else {
				break;
			}
		}
		sort(a, l, j);
		sort(a, j + 1, r);
	}

	public static void main(String[] args) 
			throws IOException, FileNotFoundException {
		BufferedReader in = new BufferedReader(new FileReader(new File("sort.in")));
		BufferedWriter out = new BufferedWriter(new FileWriter(new File("sort.out")));
		int n = Integer.parseInt(in.readLine());
		int[] a = new int[n];
		String[] s = in.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(s[i]);
		}
		sort(a, 0, n-1);
		for (int i = 0; i < n; i++) {
			out.write(a[i]+" ");
		}		
		in.close();
		out.close();
	}

}
