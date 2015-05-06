import java.io.*;

public class dm4a {
	public static void main(String[] args) throws IOException, FileNotFoundException {
		BufferedReader in = new BufferedReader(new FileReader(new File("isheap.in")));
		BufferedWriter out = new BufferedWriter(new FileWriter(new File("isheap.out")));
		
		int n = Integer.parseInt(in.readLine());
		int k = n + (n + 1) % 2;
		int[] a = new int[k];
		a[k - 1] = Integer.MAX_VALUE;
		String[] s = in.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(s[i]);
		}
		boolean check = true;
		for(int i = 0; check && i < k / 2 ; i++) {
			check &= a[2 * i + 1] > a[i] & a[2 * i + 2] > a[i];
		}
		out.write(check ? "YES" : "NO");
		in.close();
		out.close();
	}
}
