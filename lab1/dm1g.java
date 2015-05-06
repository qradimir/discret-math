import java.io.*;
 
 
public class dm1g {
     
    public static int[] next(int[] x) {
        int i;
        for (i = 0; x[i] == 1; i++) {
            x[i] = 0;
        }
        x[i] = 1;
        return x;
    }
     
    public static boolean test(int[] a, int[] check) {
        boolean flag = true, t = true;
        for (int i = 0; i < a.length; i +=2) {
            if (check[a[i]] + check[a[i + 1]] == 1) {
                check[a[i]] = 0;
                check[a[i + 1]] = 1;
            }
        }
        for (int i = 1; i < check.length; i++) {
                if (Math.abs(check[i] - check[i - 1]) == 1) {
                    t = !t;
                    if (t) {
                        flag = false;
                        break;
                    }
                }
        } 
        return flag;
    }
     
    public static void main(String[] args) throws IOException, FileNotFoundException {
        BufferedReader in = new BufferedReader(new FileReader(new File("netcheck.in")));
        BufferedWriter out = new BufferedWriter(new FileWriter(new File("netcheck.out")));
         
        String[] s = in.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        int k = Integer.parseInt(s[2]);
        int[] a = new int[2*m];
        int p = 0;
        for (int i = 0; i < k; i++) {
            s = in.readLine().split(" ");
            int r = Integer.parseInt(s[0]);
            for (int j = 1; j < 2 * r + 1; j += 2) {
                a[p] = Integer.parseInt(s[j]) - 1;
                a[p + 1] = Integer.parseInt(s[j + 1]) - 1;
                p += 2;
            }
        }
        int[] b = new int[n];
        boolean check = true, cur = true;
        for(int i = 0; i < Math.pow(2, n) - 1; i++, b = next(b)) {
            cur = test(a, b.clone());
            if (!cur) {
            	for (int j : b) {
					out.write(j+" ");
				}
            	out.newLine();
            }
            check &= cur;
        }
        check &= test(a, b);
        out.write(check ? "Yes" : "No");
        in.close();
        out.close();
    }
}