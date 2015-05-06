import java.io.*;

public class dm1d {
	
	public static final double EPS = 0.000001;
	public static int n = 0;
	public static double res, a = 0;
	
	public static double f(double m) {
		double min = a;
		double[] x = new double[n];
		x[0] = a;
		x[1] = m;
		for (int i = 2; i < x.length; i++) {
			x[i] = 2 * x[i - 1] - x[i - 2] + 2;
			if (x[i] < min) {
				min = x[i];
			}
		}
		res = x[n - 1];
		return min;
	}
	
	public static void binSearch() {
		double l = 0;
		double r = a;
		while (r - l > EPS) {
			double m = (r - l) / 2 + l;
			if (f(m) >= 0) {
				r = m;
			} else {
				l = m;
			}
		}
	}
	
	public static void main(String[] args) throws IOException, FileNotFoundException {
		BufferedReader in = new BufferedReader(new FileReader(new File("garland.in")));
		BufferedWriter out = new BufferedWriter(new FileWriter(new File("garland.out")));
		
		String[] s = in.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		a = Double.parseDouble(s[1]);
		binSearch();
		out.write(Double.toString(res));
		
		in.close();
		out.close();
	}
}
