import java.util.*;
import java.io.*;

public class dm3c {
	FastScanner in;
	PrintWriter out;
	
	final Node NIL;
	{
		NIL = new Node(Integer.MAX_VALUE, null);
		NIL.left = NIL.right = NIL.parrent = NIL;
	}
	
	public class Tree {
		Node top;
		public Tree(Node top) {
			this.top = top;
			top.parrent = NIL;
		}
		public Tree(int n){
			top = new Node(1, NIL);
			top.size++;
			for (int i = 2; i <= n; i++) {
				maximum();
				top.right = new Node(i, NIL);
				top.right.parrent = top;
				top.size++;
				top.right.size++;
			}
		}
		private boolean isLeft(Node x) {
			return x.parrent.left == x;
		}
		private void splay(Node x) {
			while (x != top && x.parrent != top) {
				if (isLeft(x) && isLeft(x.parrent)) {
					rightRotate(x.parrent.parrent);
					rightRotate(x.parrent);
					continue;
				}
				if (!isLeft(x) && !isLeft(x.parrent)) {
					leftRotate(x.parrent.parrent);
					leftRotate(x.parrent);
					continue;
				}
				if (isLeft(x) && !isLeft(x.parrent)) {
					rightRotate(x.parrent);
					leftRotate(x.parrent);
					continue;
				}
				if (!isLeft(x) && isLeft(x.parrent)) {
					leftRotate(x.parrent);
					rightRotate(x.parrent);
					continue;
				}
			}
			if (x != top) {
				if (isLeft(x)) {
					rightRotate(x.parrent);
				} else {
					leftRotate(x.parrent);
				}
			}
		} 
		protected void leftRotate(Node x) {
			Node t = x.right;
			int tsize = x.size;
			int xsize = x.left.size + t.left.size + 1;
			t.size = tsize;
			x.size = xsize;
			x.right = t.left;
			if (t.left != NIL) { t.left.parrent = x; }
			t.parrent = x.parrent;
			if (x.parrent == NIL) { top = t; }
			else {
				if (x == x.parrent.left) { x.parrent.left  = t; }
				else					 { x.parrent.right = t; }
			}
			t.left = x;
			x.parrent = t;
		}
		protected void rightRotate(Node x) {
			Node t = x.left;
			int tsize = x.size;
			int xsize = x.right.size + t.right.size + 1;
			t.size = tsize;
			x.size = xsize;
			x.left = t.right;
			if (t.right != NIL) { t.right.parrent = x; }
			t.parrent = x.parrent;
			if (x.parrent == NIL) { top = t; }
			else {
				if (x == x.parrent.left) { x.parrent.left  = t; }
				else					 { x.parrent.right = t; }
			}
			t.right = x;
			x.parrent = t;				
		}
		protected Node find(int key) {
			Node x = top;
			while (true) {
				if (key <= x.left.size) { 
					if (x.left == NIL) { break; } else {x = x.left;  continue;}
				}

				if (key > x.left.size + 1) { 
					if (x.right == NIL) { break; } else {key -= x.left.size + 1; x = x.right;  continue;}
				}
				break;
			}
			splay(x);
			return x;
		}
		protected Node maximum() {
			Node x = top;
			while (x.right != NIL) {
				x = x.right;
			}
			splay(x);
			return x;
		}
		public Tree mergeWith(Tree other) {
			maximum();
			top.right = other.top;
			other.top.parrent = top;
			top.size += top.right.size;
			return this;
		}
		public Tree split(int key) {
			Tree splittedTree;
			find(key);
			if (top.left == NIL) { return null; }
			splittedTree = new Tree(top.left);
			top.size -= top.left.size;
			top.left.parrent = NIL;
			top.left = NIL;
			return splittedTree;
		}
	}
	public  class Node {
		int key, size;
		Node left, right, parrent;
		public Node(int key, Node nil) {
			this.key = key;
			left = right = parrent = nil;
		}
		public String inOrder() {
			String leftPart  = left == NIL ? "" : left.inOrder() + " ";
			String rightPart = right == NIL ? "" : " " + right.inOrder();
			return leftPart + key + rightPart;
		}
	}
	
	public void solve() throws IOException {
		int n = in.nextInt(), k = in.nextInt(), l, r;
		Tree tree = new Tree(n);
		Tree tree1, tree2;
		for (int i = 0; i < k; i++) {
			l = in.nextInt();
			r = in.nextInt();
			if (l == 1) {
				continue;
			}
			if (r != n) {
				tree1 = tree.split(l);
				tree2 = tree.split(r - l + 2);
				tree2.mergeWith(tree1);
				tree2.mergeWith(tree);
				tree = tree2;
			} else {
				tree1 = tree.split(l);
				tree.mergeWith(tree1);
			}
		}
		out.println(tree.top.inOrder());
	}

	public void run() {
		try {
			in = new FastScanner(new File("movetofront.in"));
			out = new PrintWriter(new File("movetofront.out"));

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
		new dm3c().run();
	}
}