import java.util.*;
import java.io.*;

public class dm4c {
	FastScanner in;
	PrintWriter out;

	class LinkedValue {
		public int link, value;
		public LinkedValue(int link, int value) {
			this.link = link;
			this.value = value;
		}
	}
	
	class PQueue {
		private List<LinkedValue> q = new ArrayList<LinkedValue>();
		private List<Integer> a = new ArrayList<Integer>(); 
		private void swap(int i, int j) {
			a.set(q.get(i).link, j);
			a.set(q.get(j).link, i);
			LinkedValue t = q.get(i);
			q.set(i, q.get(j));
			q.set(j, t);
		}
		
		private void shiftDown(int x) {
			while (2 * x + 1 < q.size()) {
				int left  = 2 * x + 1;
				int right = 2 * x + 2;
				int i = right < q.size() && q.get(right).value < q.get(left).value ? right : left;
				if (q.get(x).value <= q.get(i).value) { return; }
				swap(x, i);
				x = i;
			}
			
		}
		private void shiftUp(int x) {
			while (q.get(x).value < q.get((x - 1) / 2).value) {
				swap(x, (x - 1) / 2);
				x = (x - 1) / 2;
			}
		}
		public Object extractMin() {
			a.add(-1);
			if (q.size() == 0) { return "*"; }
			int min = q.get(0).value;
			swap(0, q.size() - 1);
			q.remove(q.size() - 1);
			shiftDown(0);
			return min;
		}
		public void push(int x) {
			a.add(q.size());
			q.add(new LinkedValue(a.size() - 1, x));
			shiftUp(q.size() - 1);
		}
		public void decreseKey(int i,int value) {
			a.add(-1);
			if (q.get(a.get(i)).value < value) { return; }
			q.get(a.get(i)).value = value;
			shiftUp(a.get(i));
		}
	}
	
	
	public void solve() throws IOException {
		PQueue u = new PQueue();
		String s = in.next();
		while(!in.eof) {
			switch (s) {
				case "push" :  u.push(in.nextInt()); break;
				case "extract-min" : out.println(u.extractMin()); break;
				case "decrease-key" : u.decreseKey(in.nextInt() - 1, in.nextInt()); break;
			}
			s = in.next();
		}
	}
	
	public void run() {
		try {
			in = new FastScanner(new File("priorityqueue.in"));
			out = new PrintWriter(new File("priorityqueue.out"));

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
		new dm4c().run();
	}
}
