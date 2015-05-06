import java.util.*;
import java.io.*;

public class dm4d {
	FastScanner in;
	PrintWriter out;
	
	class Unions {
		private int[] rate, base, r;
		public Unions(int n) {
			rate = new int[n];
			base = new int[n];
			r = new int[n];
			for (int i = 0; i < n; i++) {
				r[i] = base[i] = i;
			}
		}
		private int get(int x) {
			if (base[x] != x) { base[x] = get(base[x]); }
			return base[x];
		}
		public int right(int x) {
			return r[get(x)];
		}
		
		public void union(int x, int y) {
			x = get(x);
			y = get(y);
			if (x == y) { return; }
			if (rate[x] == rate[y]) { rate[x]++; }
			if (rate[y] > rate[x]) { 
				base[x] = y;
			} else {
				base[y] = x;
				r[x] = r[y];
			}
			
		}
	}
	
	public void solve() throws IOException {
		int n = in.nextInt();
		Unions u = new Unions(n);
		for (int i = 0; i < n; i++) {
			int k = in.nextInt() - 1;
			k = u.right(k);
			u.union(k, k == n - 1 ? 0 : k + 1);
			out.print(k + 1 + " ");
		}
	}

	public void run() {
		try {
			in = new FastScanner(new File("parking.in"));
			out = new PrintWriter(new File("parking.out"));

			solve();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class FastScanner {
		BufferedReader br;
		StringTokenizer st;

		FastScanner(File f) {
			try {
				br = new BufferedReader(new FileReader(f));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		String next() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}
	}

	public static void main(String[] arg) {
		new dm4d().run();
	}
}