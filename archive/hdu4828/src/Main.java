
/**
 * Apr 3, 2016 9:54:01 PM
 * PrjName: hdu4828
 * @semprathlon
 */

import java.io.*;
import java.util.*;

public class Main {
	final static int maxn = 1000010, mod = 1000000007;
	static long inv[] = new long[maxn];
	static long a[] = new long[maxn];
	static long b[] = new long[maxn];
	static long C[] = new long[maxn];
	static long x, y;

	static void extgcd(long a, long b) {
		if (b == 0L) {
			x = 1L;
			y = 0L;
			return;
		}
		extgcd(b, a % b);
		long t = x;
		x = y;
		y = t - a / b * y;
	}

	static void get_inv(int maxn, long mod) {
		inv[1] = 1;
		for (int i = 2; i < maxn; i++) {
			// inv[i] = (mod - mod / i) * inv[(int) (mod % i)] % mod;

			inv[i] = (int) cal_inv(i, mod);
		}
	}

	static long cal_inv(long n, long mod) {
		extgcd(n, mod);
		return x < 0L ? (x + mod) % mod : x % mod;
	}

	static long pow_mod(long n, long m, long mod) {
		long res = 1L;
		n %= mod;
		while (m > 0L) {
			if ((m & 1L) > 0L)
				res = res * n % mod;
			n = n * n % mod;
			m >>= 1;
		}
		return res;
	}

	static long div_mod(long n, long m, long mod) {
		// return n * pow_mod(m, mod - 2, mod) % mod;
		// return n * pow_mod(m, phi(mod) - 1, mod) % mod;
		return n * inv[(int) m] % mod;
	}

	static void get_Catalan(int maxn) {
		a[1] = 2;
		b[1] = 1;
		C[1] = 1;
		for (int i = 2; i < maxn - 1; i++) {
			/*
			 * a[i] = a[i - 1] * ((i << 1) - 1) % mod * (i << 1) % mod; a[i] =
			 * div_mod(a[i], i, mod); a[i] = div_mod(a[i], i, mod); b[i] = b[i -
			 * 1] * ((i << 1) - 1) % mod * (i << 1) % mod; b[i] = div_mod(b[i],
			 * i + 1, mod); b[i] = div_mod(b[i], i - 1, mod); C[i] = a[i] -
			 * b[i]; if (C[i] < 0) C[i] += mod;
			 */
			C[i] = C[i - 1] * ((i << 2) - 2) % mod;
			C[i] = div_mod(C[i], i + 1, mod);
		}
	}

	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		get_inv(maxn, mod);
		get_Catalan(maxn);
		int T = in.nextInt(), cas = 0;
		while (T-- > 0) {
			int n = in.nextInt();
			out.println("Case #" + (++cas) + ":");
			out.println(C[n]);
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
