import java.util.*;
import java.io.*;


public class dm2c {
	FastScanner in;
	PrintWriter out;
	
	class Queue {
		int[] e;
		int last, first, size;
		
		void ensureCapacity(int x) {
			if (x <= e.length) {
				return;
			}
			int[] t = new int[2 * x];
			for (int i = 0; i < 2*x; i++) {
				t[i] = e[(first + i) % e.length];
			}
			first = 0;
			last = size;
			e = t;
		}
		
		void enque(int x) {
			ensureCapacity(size + 1);
			e[last] = x;
			last = (last + 1) % e.length;
			size++;
		}
		
		int deque() {
			int x = e[first];
			first = (first + 1) % e.length;
			size--;
			return x;
		}
		
		public Queue() {
			e = new int[50];
			last = 0;
			first = 0;
			size = 0;
		}
	}

	public void solve() throws IOException {
		int n = in.nextInt();
		Queue queue = new Queue();
		for(int i = 0; i<n; i++)
			switch (in.next()) {
				case "+" : queue.enque(in.nextInt()); break;
				case "-" : out.println(queue.deque()); break;
			}
	}

	public void run() {
		try {
			in = new FastScanner(new File("queue1.in"));
			out = new PrintWriter(new File("queue1.out"));

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
		new dm2c().run();
	}
}