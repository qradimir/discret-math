import java.util.*;
import java.io.*;

public class dm3b {
	FastScanner in;
	PrintWriter out;

	public class Tree {
		Node top, nil;
		public Tree(){
			nil = new Node(Integer.MAX_VALUE, null);
			nil.parrent = nil.left = nil.right = nil;
			nil.isRed = false;
			top = nil;
		}
		protected void leftRotate(Node x) {
			Node t = x.right;
			x.right = t.left;
			if (t.left != nil) { t.left.parrent = x; }
			t.parrent = x.parrent;
			if (x.parrent == nil) { top = t; }
			else {
				if (x == x.parrent.left) { x.parrent.left  = t; }
				else					 { x.parrent.right = t; }
			}
			t.left = x;
			x.parrent = t;
		}
		protected void rightRotate(Node x) {
			Node t = x.left;
			x.left = t.right;
			if (t.right != nil) { t.right.parrent = x; }
			t.parrent = x.parrent;
			if (x.parrent == nil) { top = t; }
			else {
				if (x == x.parrent.left) { x.parrent.left  = t; }
				else					 { x.parrent.right = t; }
			}
			t.right = x;
			x.parrent = t;			
		}
	
		protected Node find(int key) {
			Node x = top;
			while (x != nil) {
				if (key < x.key) { x = x.left;  continue;}
				if (key > x.key) { x = x.right; continue;}
				break;
			}
			return x;
		}
		public boolean exists(int key) {
			return find(key) != nil;
		}
		
		public void insert(int key) {
			if (top == nil) {
				top = new Node(key, nil);
				top.isRed = false;
				return;
			}
			Node x = top;
			while (true) {
				if (key < x.key) {
					if (x.left == nil) { 
						x.left = new Node(key, nil);
						x.left.parrent = x;
						insertFix(x.left);
						return;
					}
					x = x.left; continue;
				} 
				if (key > x.key) {
					if (x.right == nil) { 
						x.right = new Node(key, nil);
						x.right.parrent = x;
						insertFix(x.right);
						return; 
					}
					x = x.right; continue;
				}
				return;
			}
		}
		private void insertFix(Node x) {
			while (x.parrent.isRed) {
				if (x.parrent == x.parrent.parrent.left) {
					Node t = x.parrent.parrent.right;
					if (t.isRed) {
						x.parrent.isRed = false;
						t.isRed = false;
						x.parrent.parrent.isRed = true;
						x = x.parrent.parrent;
					} else {
						if (x == x.parrent.right) {
							x = x.parrent;
							leftRotate(x);
						}
						x.parrent.isRed = false;
						x.parrent.parrent.isRed = true;
						rightRotate(x.parrent.parrent);
					}
				} else {
					Node t = x.parrent.parrent.left;
					if (t.isRed) {
						x.parrent.isRed = false;
						t.isRed = false;
						x.parrent.parrent.isRed = true;
						x = x.parrent.parrent;
					} else {
						if (x == x.parrent.left) {
							x = x.parrent;
							rightRotate(x);
						}
						x.parrent.isRed = false;
						x.parrent.parrent.isRed = true;
						leftRotate(x.parrent.parrent);
					}
				}
			}
			top.isRed = false;
		}
	
		private void parrentLinkSet(Node x, Node t) {
			t.parrent = x.parrent;
			if (x.parrent == nil) { top = t; }
			else {
				if (x.parrent.left == x) { x.parrent.left  = t; }
				else 					 { x.parrent.right = t; }
			}
		}
		
		public int prev(int key) {
			Node x = top, candidate = nil;
			while (x != nil) {
				if (x.key == key) {
					int k = maximum(x.left).key; 
					return k == Integer.MAX_VALUE ? candidate.key: k;
				} 
				if (x.key > key) {
					x = x.left;
				} else {
					candidate = x;
					x = x.right;
				}
			}
			return candidate.key;
		}
		public int next(int key) {
			Node x = top, candidate = nil;
			while (x != nil) {
				if (x.key == key) {
					int k = minimum(x.right).key; 
					return k == Integer.MAX_VALUE ? candidate.key: k;
				} 
				if (x.key > key) {
					candidate = x;
					x = x.left;
				} else {
					x = x.right;
				}
			}
			return candidate.key;
		}
		
		public Node minimum(Node x) {
			while(x.left != nil) {
				x = x.left; 
			}
			return x;
		}
		public Node maximum(Node x) { 
			while(x.right != nil) {
				x = x.right; 
			}
			return x;
		}
		
		public void delete(int key) {
			Node x = find(key);
			Node tmp;
			if (x == nil) { return; }
			boolean isDeletedRed = x.isRed;
			if 		(x.left  == nil) { parrentLinkSet(x, tmp = x.right); }
			else if (x.right == nil) { parrentLinkSet(x, tmp = x.left); }
			else {
				Node t = minimum(x.right);
				parrentLinkSet(t, tmp = t.right);
				x.key = t.key;
				isDeletedRed = t.isRed;
			}
			if (!isDeletedRed) { deleteFix(tmp); }
		}
		private void deleteFix(Node x) {
			while (x != top && !x.isRed) {
				if (x == x.parrent.left) {
					Node t = x.parrent.right;
					if (t.isRed) {
						t.isRed = false;
						x.parrent.isRed = true;
						leftRotate(x.parrent);
						t = x.parrent.right;
					}
					if (!t.left.isRed && !t.right.isRed) {
						t.isRed = true;
						x = x.parrent; 
					} else {
						if (!t.right.isRed) {
							t.left.isRed = false;
							t.isRed = true;
							rightRotate(t);
							t = x.parrent.right;
						}
						t.isRed = x.parrent.isRed;
						x.parrent.isRed = false;
						t.right.isRed = false;
						leftRotate(x.parrent);
						x = top;
					}
				} else {
					Node t = x.parrent.left;
					if (t.isRed) {
						t.isRed = false;
						x.parrent.isRed = true;
						rightRotate(x.parrent);
						t = x.parrent.left;
					}
					if (!t.left.isRed && !t.right.isRed) {
						t.isRed = true;
						x = x.parrent; 
					} else {
						if (!t.left.isRed) {
							t.right.isRed = false;
							t.isRed = true;
							leftRotate(t);
							t = x.parrent.left;
						}
						t.isRed = x.parrent.isRed;
						x.parrent.isRed = false;
						t.left.isRed = false;
						rightRotate(x.parrent);
						x = top;
					}
				}
			}
			x.isRed = false;
		}
		
	}
	
	public class Node {
		int key;
		Node left, right, parrent;
		boolean isRed;
		public Node(int key, Node nil) {
			this.key = key;
			isRed = true;
			parrent = left = right = nil;
		}
	}
	
	public void solve() throws IOException {
		Tree tree = new Tree();
		String input = in.next();
		int value, k;
		while(!in.eof) {
			value = in.nextInt();
			switch (input) {
				case "insert" : tree.insert(value); break;
				case "delete" : tree.delete(value); break;
				case "exists" : out.println(tree.exists(value)); break;
				case "prev"   : k = tree.prev(value); out.println(k == Integer.MAX_VALUE ? "none" : k); break;
				case "next"   : k = tree.next(value); out.println(k == Integer.MAX_VALUE ? "none" : k); break;
			}
			input = in.next();
		}
	}
	
	public void run() {
		try {
			in = new FastScanner(new File("bst.in"));
			out = new PrintWriter(new File("bst.out"));

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
		new dm3b().run();
	}
}
