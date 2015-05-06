import java.io.*;

public class dm1h {
	
	public static int n = 0, ans  = 10, count = 80;
	
	public class Pare {
		public int left, right;
		public Pare(int left, int right) {
			this.left = left;
			this.right = right;
		}
	}
	
	public static int[][] Compare = new int[][]{
			new int[]{8, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16},
			new int[]{8, 1, 4, 2, 3, 5, 8, 6, 7, 9, 12, 10, 11, 13, 16, 14, 15},
			new int[]{8, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16},
			new int[]{8, 1, 8, 2, 7, 3, 6, 4, 5, 9, 16, 10, 15, 11, 14, 12, 13},
			new int[]{8, 1, 3, 2, 4, 5, 7, 6, 8, 9, 11, 10, 12, 13, 15, 14, 16},
			new int[]{8, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16},
			new int[]{8, 1, 16, 2, 15, 3, 14, 4, 13, 5, 12, 6, 11, 7, 10, 8, 9},
			new int[]{8, 1, 5, 2, 6, 3, 7, 4, 8, 9, 13, 10, 14, 11, 15, 12, 16},
			new int[]{8, 1, 3, 2, 4, 5, 7, 6, 8, 9, 11, 10, 12, 13, 15, 14, 16},
			new int[]{8, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16},
	};
	
	public static void deleteRow(int r){
		for (int[] Col : Compare) {
			for (int i = 2; i < 18; i +=2) {
				if (Col[i] == r) {
					 Col[i - 1] = Col[i] = 0;
					 Col[0]--;
					 count--;
					 if (Col[0] == 0) {
						 ans--;
					 }
				}
			}
		}
	}
	
	public static String returnCol(int c) {
		String ret = Compare[c][0]+" ";
		for(int i = 2; i < 18; i +=2){
			if (Compare[c][i] > 0 ) {
				ret += Compare[c][i - 1] + " " + Compare[c][i]+" ";
			}
		}
		return ret;
	}
	
	public static void main(String[] args) throws IOException, FileNotFoundException {
		BufferedReader in = new BufferedReader(new FileReader(new File("netbuild.in")));
		BufferedWriter out = new BufferedWriter(new FileWriter(new File("netbuild.out")));
		
		n = Integer.parseInt(in.readLine());
		
		for (int i = 16; i > n; i--) {
			deleteRow(i);
		}
		out.write(n+" "+count+" "+ans);
		out.newLine();
		for(int i = 0; i < 10; i++) {
			if (Compare[i][0] > 0) {
				out.write(returnCol(i));
				out.newLine();
			}
		}
		
		in.close();
		out.close();
	}
}
