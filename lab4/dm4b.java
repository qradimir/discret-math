import java.util.*;
import java.io.*;

public class dm4b {
	FastScanner in;
	PrintWriter out;

	class Unions {
		int[] rate, base, l, r, c;
		public Unions(int n) {
			rate = new int[n];
			base = new int[n];
			l = new int[n];
			r = new int[n];
			c = new int[n];
			for (int i = 0; i < n; i++) {
				l[i] = r[i] = base[i] = i;
				c[i] = 1;
			}
		}
		protected int get(int x) {
			if (base[x] != x) { base[x] = get(base[x]); }
			return base[x];
		}
		private void set(int to, int from) {
			base[from] = to;
			l[to] = l[to] < l[from] ? l[to] : l[from];
			r[to] = r[to] > r[from] ? r[to] : r[from];
			c[to] += c[from];
		}
		
		public void union(int x, int y) {
			x = get(--x);
			y = get(--y);
			if (x == y) { return; }
			if (rate[x] == rate[y]) { rate[x]++; }
			if (rate[y] > rate[x]) { 
				set(y, x);
			} else {
				set(x, y);
			}
		}
		public String info(int x) {
			x = get(--x);
			return (l[x] + 1) + " " + (r[x] + 1) + " " + c[x];
		}
	}
	
	
	public void solve() throws IOException {
		int n = in.nextInt();
		Unions u = new Unions(n);
		String s;
		while(true) {
			s = in.next();
			if (in.eof) break;
			switch (s) {
				case "get"   : out.println(u.info(in.nextInt()));   break;
				case "union" : u.union(in.nextInt(), in.nextInt()); break;
			}
		}
	}
	
	public void run() {
		try {
			in = new FastScanner(new File("dsu.in"));
			out = new PrintWriter(new File("dsu.out"));

			solve();

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			out.close();
		} 
	}

	class FastScanner {
		BufferedReader br;
		StringTokenizer st;
		boolean eof;

		FastScanner(File f) {
			try {
				br = new BufferedReader(new FileReader(f));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			eof = false;
		}

		String next() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (Exception e) {
					eof = true;
					return "";
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			String s = next();

			return eof ? null : Integer.parseInt(s);
		}
	}

	public static void main(String[] arg) {
		new dm4b().run();
	}
}
