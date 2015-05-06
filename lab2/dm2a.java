import java.util.*;
import java.io.*;

public class dm2a {
	FastScanner in;
	PrintWriter out;
	
	class Stack {
		int[] e;
		int size;
		
		void ensureCapacity(int a) {
			if (a <= e.length) {
				return;
			}
			int[] t = new int[2*a];
			for (int i = 0; i < size; i++) {
				t[i] = e[i];
			}
			e = t;
		}
		
		void push(int x) {
			ensureCapacity(size + 1);
			e[size] = x;
			size++;
		}
		
		int pop() {
			return e[--size];
		}
		
		public Stack() {
			e = new int[50];
			size = 0;
		}
	}

	public void solve() throws IOException {
		int n = in.nextInt();
		Stack stack = new Stack();
		for(int i = 0; i<n; i++)
			switch (in.next()) {
				case "+" : stack.push(in.nextInt()); break;
				case "-" : out.println(stack.pop()); break;
			}
	}

	public void run() {
		try {
			in = new FastScanner(new File("stack1.in"));
			out = new PrintWriter(new File("stack1.out"));

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
		new dm2a().run();
	}
} 