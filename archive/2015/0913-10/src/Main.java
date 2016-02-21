
/** Sep 13, 2015 9:26:58 AM
 * PrjName:0913-10
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;

public class Main {

	/**
	 * @param args
	 */
	final static int maxn = 110000;
	static long[] fac = new long[maxn];
	static long[] p, a;
	static long x, y;

	static void Get_Fac(long n, long mod) {
		fac[0] = 1;
		for (int i = 1; i <= n; i++) {
			fac[i] = fac[i - 1] * i;
			fac[i] %= mod;
		}
	}

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

	static long mul_mod(long n, long m, long mod) {
		long ans = 0L;
		n %= mod;
		while (m > 0L) {
			if ((m & 1L) > 0L)
				ans = (ans + n) % mod;
			m >>= 1;
			n = (n + n) % mod;
		}
		return ans;
	}

	static long div_mod(long n, long m, long mod) {
		return n * pow_mod(m, mod - 2, mod) % mod;
	}

	static long C(long n, long m, long mod) {
		int a = (int) (n % mod), b = (int) (m % mod);
		return div_mod(fac[a], mul_mod(fac[a - b], fac[b], mod), mod);
	}

	static long Lucas(long n, long m, long mod) {
		long ret = 1L;
		while (n > 0L && m > 0L) {
			if (n % mod < m % mod)
				return 0L;
			ret = mul_mod(ret, C(n, m, mod), mod);
			ret %= mod;
			n /= mod;
			m /= mod;
		}
		return ret;
	}

	// 中国剩余定理 x == a[i] (mod m[i]) 共有n个方程。
	static long CRT(long n, long[] a, long[] m) {
		long pro = 1L, res = 0L;
		for (int i = 0; i < n; i++)
			pro *= m[i];
		for (int i = 0; i < n; i++) {
			long w = pro / m[i];
			extgcd(m[i], w);
			res = (res + mul_mod(y, mul_mod(w, a[i], pro), pro)) % pro;
		}
		return (res + pro) % pro;
	}

	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int T = in.nextInt();
		while (T-- > 0) {
			long n = in.nextLong();
			long m = in.nextLong();
			long num = in.nextLong();
			p = new long[15];
			a = new long[15];
			for (int i = 0; i < num; i++)
				p[i] = in.nextLong();
			for (int i = 0; i < num; i++) {
				Get_Fac(p[i], p[i]);
				a[i] = Lucas(n, m, p[i]);
			}
			out.println(CRT(num, a, p));
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