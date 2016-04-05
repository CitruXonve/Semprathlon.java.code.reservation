
/**
 * Mar 5, 2016 11:17:28 AM
 * PrjName: hdu5620
 * @semprathlon
 */
import java.io.*;
import java.util.*;

public class Main {
	final static long maxs = 1000000000000000000L;
	static Vector<Long> v = new Vector<>();

	static void init() {
		v.add(1L);
		v.add(2L);
		long a = 1L, b = 2L, c = 3L, s = 6L;
		while (s <= maxs) {
			v.add(s);
			a = b;
			b = c;
			c = a + b;
			s += c;
		}
		;
	}

	static int bsearch(Vector<Long> v, long key) {
		int l = 0, r = v.size() - 1;
		while (l < r) {
			int m = (l + r + 1) >> 1;
			if (v.get(m) == key)
				return m + 1;
			else if (v.get(m) > key)
				r = m - 1;
			else
				l = m;
		}
		return l + 1;
	}

	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		init();
		int T = in.nextInt();
		while (T-- > 0) {
			long n = in.nextLong();
			int ans = (n > 2L) ? bsearch(v, n) : 1;
			out.println(ans);
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