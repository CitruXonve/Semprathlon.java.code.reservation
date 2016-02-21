
/**
 * Feb 13, 2016 4:54:02 PM
 * PrjName: hdu5615
 * @semprathlon
 */
import java.io.*;
import java.util.*;

public class Main {
	static boolean check(int a, int b, int c, int p, int k) {
		int q = a / p;
		int m = c / k;
		return q * k + m * p == b;
	}

	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int T = in.nextInt();
		while (T-- > 0) {
			int a = in.nextInt();
			int b = in.nextInt();
			int c = in.nextInt();
			int mp = (int) Math.sqrt(a);
			int mk = (int) Math.sqrt(c);
			boolean ans = false;
			for (int p = 1; p <= mp; p++) {
				if (a % p > 0)
					continue;
				if (ans)
					break;
				for (int k = 1; k <= mk; k++) {
					if (c % k > 0)
						continue;
					if (check(a, b, c, p, k) || check(a, b, c, a / p, k) || check(a, b, c, p, c / k)
							|| check(a, b, c, a / p, c / k)) {
						ans = true;
						break;
					}
				}
			}
			out.println(ans ? "YES" : "NO");

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
