
/** Sep 1, 2015 5:16:47 PM
 * PrjName:loj1205
 * @author Semprathlon
 */

import java.io.*;
import java.util.*;

public class Main {
	final static int maxl = 18;
	static long[][] f;
	static int[] digit;

	static void init() {
		f = new long[maxl + 1][maxl + 1];
		f[0][0] = 1L;
	}

	static void getd(long n) {
		digit = new int[maxl + 1];
		while (n > 0L) {
			digit[++digit[0]] = (int) (n % 10L);
			n /= 10L;
		}
	}

	static long dfs(int d, int l, int[] num, boolean fst, boolean c) {
		if (d == 0)
			return 1L;
		if (c && !fst && f[d][l] > 0)
			return f[d][l];
		long res = 0L;

		int up = c ? 9 : digit[d];

		for (int i = 0; i <= up; i++)
			if (fst) {
				num[l] = i;
				if (i == 0)
					res += dfs(d - 1, l - 1, num, true, c || i != up);
				else
					res += dfs(d - 1, l, num, false, c || i != up);
			} else {
				int mid = (l + 1) >> 1;
				if (l % 2 > 0) {
					if (d >= mid) {
						num[d] = i;
						res += dfs(d - 1, l, num, fst && i == 0, c || i != up);
					} else if (num[2 * mid - d] == i)
						res += dfs(d - 1, l, num, fst && i == 0, c || i != up);
				} else if (d >= (mid + 1)) {
					num[d] = i;
					res += dfs(d - 1, l, num, fst && i == 0, c || i != up);
				} else if (num[l + 1 - d] == i)
					res += dfs(d - 1, l, num, fst && i == 0, c || i != up);
			}

		if (c && !fst)
			f[d][l] = res;

		return res;
	}

	static long solve(long n) {
		if (n < 0)
			return 0L;
		getd(n);
		return dfs(digit[0], digit[0], new int[maxl + 1], true, false);
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		init();

		int T = in.nextInt(), cas = 0;
		while (T-- > 0) {
			long n = in.nextLong();
			long m = in.nextLong();
			if (n > m) {
				long t = n;
				n = m;
				m = t;
			}
			// out.println(solve(m)+" "+solve(n-1));
			out.println("Case " + (++cas) + ": " + (solve(m) - solve(n - 1)));
		}

		// for(int i=1;i<=maxl;i++){ for(int j=0;j<10;j++) out.print(f[i][j]+"
		// "); out.println(); }

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