import java.util.*;
import java.io.*;

public class dm2d {
	FastScanner in;
	PrintWriter out;

	class Node {
		Node next;
		int key;
		public Node(Node next, int key) {
			this.next = next;
			this.key = key;
		}
	}
	
	class Queue {
		Node first, last;		
		
		void enque(int x) {
			last.next = new Node(null, x);
			last = last.next;
		}
		
		int deque() {
			int x = first.next.key;
			first.next = first.next.next;
			if (first.next == null) {
				last = first;
			}
			return x;
		}
		
		public Queue() {
			first = new Node(null, 0);
			last = first;
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
			in = new FastScanner(new File("queue2.in"));
			out = new PrintWriter(new File("queue2.out"));

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
		new dm2d().run();
	}
}