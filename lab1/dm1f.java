import java.io.*;


public class dm1f {

	public static void main(String[] args) 
			throws IOException, FileNotFoundException {
		BufferedReader in = new BufferedReader(new FileReader(new File("antiqs.in")));
		BufferedWriter out = new BufferedWriter(new FileWriter(new File("antiqs.out")));
		int n = Integer.parseInt(in.readLine());
		int[] a = new int[n];
		for (int i = 0; i < a.length; i++) {
			a[i] = i + 1;
			int t = a[i]; a[i] = a[i / 2]; a[i / 2] = t;
		}
		for (int i = 0; i < a.length; i++) {
			out.write(a[i]+" ");
		}
		in.close();
		out.close();
	}
}
