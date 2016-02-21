
/**
 * Feb 13, 2016 7:25:26 PM
 * PrjName: Bc72-02
 * @semprathlon
 */

import java.io.*;
import java.util.*;

public class Main {
	static long sd, seed;
	final static long inf = 0x7ffffffffffL;

	static long solve(int n) {
		long ans = 0, mi, mx, t;
		for (int s = 0; s < 4; s++) {
			mi = inf;
			mx = -inf;
			sd = seed;
			for (int i = 0; i < n; i++) {
				t = 0;
				long t0 = rand(-1000000000, 1000000000);
				long t1 = rand(-1000000000, 1000000000);
				if ((1 & s) > 0)
					t += t0;
				else
					t -= t0;
				if ((2 & s) > 0)
					t += t1;
				else
					t -= t1;
				mi = Math.min(mi, t);
				mx = Math.max(mx, t);
			}
			ans = Math.max(ans, mx - mi);
		}
		return ans;
	}

	static long rand(long l, long r) {
		long mo = 1000000000L + 7, g = 78125L;
		sd *= g;
		return l + (sd %= mo) % (r - l + 1);
	}

	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int T = in.nextInt();
		while (T-- > 0) {
			int n = in.nextInt();
			sd = in.nextLong();
			seed = sd;
			out.println(solve(n));
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
