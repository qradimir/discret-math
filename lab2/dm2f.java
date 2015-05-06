import java.io.*;

public class dm2f {

	BufferedReader in;
	BufferedWriter out;

	public dm2f() throws IOException {
		in = new BufferedReader(new FileReader(new File("postfix.in")));
		out = new BufferedWriter(new FileWriter(new File("postfix.out")));
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
	
	void run() throws IOException {
		String[] tokens = in.readLine().split(" ");
		Stack s = new Stack();
		for (String token : tokens) {
			switch (token) {
				case "+" : s.push(s.pop() + s.pop()); break;
				case "-" : s.push(-s.pop() + s.pop()); break;
				case "*" : s.push(s.pop() * s.pop()); break;
				default : s.push(Integer.parseInt(token));
			}
		}
		out.write(Integer.toString(s.pop()));
		
		in.close();
		out.close();
	}

	public static void main(String[] args) throws IOException {
		new dm2f().run();
	}
}
