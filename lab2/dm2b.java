import java.util.*;
import java.io.*;

public class dm2b {
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
	
	class Stack {
		Node e;
				
		void push(int x) {
			Node t = new Node(e, x);
			e = t;
		}
		
		int pop() {
			int x = e.key;
			e = e.next;
			return x;
		}
		
		public Stack() {
			e = new Node(null, -1); 
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
			in = new FastScanner(new File("stack2.in"));
			out = new PrintWriter(new File("stack2.out"));

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
		new dm2b().run();
	}
}