import java.io.*;

public class dm2e {
	
	BufferedReader in;
	BufferedWriter out;
	
	public dm2e() throws IOException {
		in = new BufferedReader(new FileReader(new File("brackets.in")));
		out = new BufferedWriter(new FileWriter(new File("brackets.out")));
	}
	
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
	
	void run()  throws IOException{
		String brackets;
		boolean flag;
		while (true) {
			brackets = in.readLine();
			if (brackets == null) {
				break;
			}
			Stack s = new Stack();
			flag = false;
			for (int i = 0; i < brackets.length(); i++) {
				switch (brackets.charAt(i)) {
					case '(' : s.push(0); break;
					case '[' : s.push(1);break;
					case ')' : flag = s.pop() == 0 ? flag : true; break;
					case ']' : flag = s.pop() == 1 ? flag : true; break;
				}
				if (flag) {
					break;
				}
			}
			flag = flag ? true : (s.e.next != null);
			out.write(flag ? "NO\n" : "YES\n");
		}
		
		in.close();
		out.close();
	}
	
	public static void main(String[] args) throws IOException {
		new dm2e().run();
	}
}