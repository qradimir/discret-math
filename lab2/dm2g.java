import java.util.*;
import java.io.*;

public class dm2g {
	FastScanner in;
	PrintWriter out;

	public void solve() throws IOException {
		int n = in.nextInt();
		int m = in.nextInt();
		int k = in.nextInt();
		int a = in.nextInt();
		int b = in.nextInt();
		int c = in.nextInt();
		long min = 0;
		int[] firsts = new int[k];
		for (int i = 0; i < k; i++) {
			firsts[i] = in.nextInt();
		}
		ArrayBuilder A = new ArrayBuilder(a, b, c, firsts);
		ArrayBuilder B = new ArrayBuilder(a, b, c, firsts); 
		QueueMin q = new QueueMin(m);
		for(int i = 0; i < m - 1; i++) {
			q.enqueue(A.next());
		}
		for (int i = m - 1; i < n; i++) {
			q.enqueue(A.next());
			min += q.min();
			q.dequeue(B.next());
		}
		out.write(Long.toString(min));
	}

	public void run() {
		try {
			in = new FastScanner(new File("queuemin2.in"));
			out = new PrintWriter(new File("queuemin2.out"));

			solve();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	
	class QueueMin {
		Deque  min;
		
		void enqueue(int t) {
			while (min.size > 0 && min.peekBack() > t) {
				min.popBack();
			}
			min.pushBack(t);
		}
		
		void dequeue(int t) {
			if (min.size > 0 && min.peekFront() == t) {
				min.popFront();
			}
		}
		
		int min() {
			return min.peekFront();
		}
		
		public QueueMin(int cap) {
			min = new Deque(cap);
		}
	}
	
	class Deque {
		int first, last, size;
		int[] e;
				
		void pushBack(int x) {
			first = first == e.length - 1 ? 0 : first + 1;
			e[first] = x;
			size++;
		}
		
		void pushFront(int x) {
			last = last == 0 ? e.length - 1: last - 1;
			e[last] = x;
			size++;
		}
		
		int popBack() {
			size--;
			int x = e[first];
			first = first == 0 ? e.length - 1 : first - 1;
			return x;
		}
		
		int popFront() {
			size--;
			int x = e[last]; 
			last = last == e.length - 1 ? 0: last + 1;
			return x;
		}
		
		int peekBack() {
			return e[first];
		}
		
		int peekFront() {
			return e[last];
		}
		
		public Deque(int cap) {
			e = new int[cap];
			first = e.length -1;
			last = size = 0;
		}
	}
	
	class ArrayBuilder {
		int a, b, c;
		int[] A;
		int first, second, iterator;
		
		public ArrayBuilder(int a, int b, int c, int[] A) {
			this.a = a;
			this.b = b;
			this.c = c;
			this.A = A;
			first = A[A.length - 2];
			second = A[A.length - 1];
			iterator = 0;
		}
		
		int next() {
			if (iterator < A.length) {
				return A[iterator++];
			}
			int x = a * first + b * second + c;
			first = second;
			second = x;
			return x;
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
		new dm2g().run();
	}
}
