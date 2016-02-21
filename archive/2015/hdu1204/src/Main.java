
/** Sep 5, 2015 9:45:04 PM
 * PrjName:hdu1204
 * @author Semprathlon
 */
import java.awt.Toolkit;
import java.io.*;
import java.util.*;

public class Main {

	/**
	 * @param args
	 */
	static double pow(double n, int m) {
		double res = 1;
		while (m > 0) {
			if ((m & 1) > 0)
				res = res * n;
			n = n * n;
			m >>= 1;
		}
		return res;
	}

	static String str;
	static StringTokenizer tokenizer;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while (in.nextLine() != null) {
			// out.println(str);
			int n = in.nextInt();
			int m = in.nextInt();
			double p = in.nextDouble();
			double q = in.nextDouble();
			// if (br.readLine()==null) break
			// out.println(n+" "+m+" "+p+" "+q);
			if (n == 0)
				out.println("0.00");
			else if (m == 0)
				out.println("1.00");
			else if (p == 0 || q == 1)
				out.println("0.00");
			else if (p == 1 || q == 0)
				out.println("1.00");
			else {
				double k = q * (1.0 - p) / p / (1.0 - q);
				double ans = p == q ? n * 1.0 / (m + n) : (1.0 - pow(k, n)) / (1.0 - pow(k, n + m));
				out.println(String.format("%.2f", ans));
				// out.printf("%.2f\n", ans);
			}
		}
		out.flush();
		out.close();
	}

}

class InputReader {
	public BufferedReader reader;
	public StringTokenizer tokenizer;

	public InputReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream), 32768);
		tokenizer = null;
	}

	public String nextLine() {
		String tmp = null;
		try {
			tmp = reader.readLine();
			tokenizer = new StringTokenizer(tmp);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (NullPointerException e) {
			return null;
		}
		return tmp;
	}

	public String next() {
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new StringTokenizer(reader.readLine());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return tokenizer.nextToken();
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public long nextLong() {
		return Long.parseLong(next());
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}
}
