import java.util.*;
import java.io.*;

public class dm3a {
	FastScanner in;
	PrintWriter out;

	public class Tree {
		Node top;
		public boolean exists(int key) {
			return top == null ? false : top.search(key) != null;
		}
		public void insert(int key) {
			if (top == null) {
				top = new Node(key);
				return;
			}
			if (top.key != key) {
				top.insert(new Node(key));
			}
		}
		public void delete(int key) {
			if (top == null) { return; }
			Node t = top.search(key);
			if (t == null) { return; }
			t.delete();
			if (top.invalide) {
				if (top.left == null) {
					top = top.right;
					if (top != null) { top.parrent = null; }
				} else {
					top = top.left;
					top.parrent = null;
				}
			}
		}
		public String next(int key) {
			if (top == null) { return "none"; }
			Node t = top.searchUpper(key);
			return t == null ? "none" : Integer.toString(t.key);
		}
		public String prev(int key) {
			if (top == null) { return "none"; }
			Node t = top.searchLower(key);
			return t == null ? "none" : Integer.toString(t.key);
		}
	}
	
	public class Node {
		int key;
		Node left, right, parrent;
		boolean invalide;
		public Node(int key) {
			this.key = key;
			invalide = false;
		}
		public Node searchLower(int k) {
			Node t = this, candidate = null;
			while (true) {
				if (t.key == k) {
					if (t.left == null) {
						return candidate;
					}
					return t.left.maximum();
				}
				if (t.key > k) {
					if (t.left == null) {
						return candidate;
					}
					t = t.left;
				} else {
					candidate = t;
					if (t.right == null) {
						return candidate;
					}
					t = t.right;
				}
			}
		}
		public Node searchUpper(int k) {
			Node t = this, candidate = null;
			while (true) {
				if (t.key == k) {
					if (t.right == null) {
						return candidate;
					}
					return t.right.minimum();
				}
				if (t.key > k) {
					candidate = t;
					if (t.left == null) {
						return candidate;
					}
					t = t.left;
				} else {
					if (t.right == null) {
						return candidate;
					}
					t = t.right;
				}
			}
		}
		public Node search(int k) {
			Node t = this;
			while (t != null) {
				if (t.key == k) {
					return t;
				}
				if (t.key > k) {
					t = t.left;
				} else {
					t = t.right;
				}
			}
			return t;
		}
		public void insert(Node t) {
			if (key < t.key) {
				if (right != null) {
					right.insert(t);
				} else {
					t.parrent = this;
					right = t;
				}
			} else if (key > t.key) {
				if (left != null) {
					left.insert(t);
				} else {
					t.parrent = this;
					left = t;
				}
			}
		}
		public Node minimum() {
			Node t = this;
			while (t.left != null) { 
				t = t.left; 
				}
			return t;
		}
		public Node maximum() {
			Node t = this;
			while (t.right != null) { 
				t = t.right; 
				}
			return t;
		}
		
		private void parrentLinkSet(Node t) {
			if (parrent.left == this) {
				parrent.left = t;
			} else {
				parrent.right = t; 
			}
			if (t != null) {
				t.parrent = parrent;
			}
		}
		
		private void  deleteNoParrent() {
			if (left == null || right == null) {
				invalide = true;
				return;
			}
			Node x = right.minimum();
			x.parrentLinkSet(x.right);
			key = x.key;
		}
		
		public void delete() {
			if (parrent == null) {
				deleteNoParrent();
				return;
			}
			if (left == null) {
				parrentLinkSet(right);
				return;
			}
			if (right == null) {
				parrentLinkSet(left);
				return;
			}
			Node x = right.minimum();
			x.parrentLinkSet(x.right);
			key = x.key;
		}
	}
	
	public void solve() throws IOException {
		Tree tree = new Tree();
		String input = in.next();
		int value;
		while(!in.eof) {
			value = in.nextInt();
			switch (input) {
				case "insert" : tree.insert(value); break;
				case "delete" : tree.delete(value); break;
				case "exists" : out.println(tree.exists(value)); break;
				case "prev"   : out.println(tree.prev(value)); break;
				case "next"   : out.println(tree.next(value)); break;
			}
			input = in.next();
		}
	}

	public void run() {
		try {
			in = new FastScanner(new File("bstsimple.in"));
			out = new PrintWriter(new File("bstsimple.out"));

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
		new dm3a().run();
	}
}
