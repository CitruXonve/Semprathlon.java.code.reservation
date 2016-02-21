
/**
 * Dec 5, 2015 8:39:53 PM
 * PrjName: 1205-03-2
 * @semprathlon
 */
import java.io.*;
import java.util.*;

public class Main {
	static int[] a, f;
	static BIT tr;
	static HashSet<Integer> st = new HashSet<Integer>();

	static void print(int[] a, PrintWriter out) {
		int n = a.length - 1;
		for (int i = 1; i <= n; i++)
			out.print(a[i] + (i < n ? " " : ""));
		out.println();
	}

	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int T = in.nextInt();
		// tr=new BIT(5);
		// out.print(tr.lowbit(2));
		while (T-- > 0) {
			int n = in.nextInt();
			a = new int[n + 1];
			f = new int[n + 1];
			tr = new BIT(n);
			for (int i = 1; i <= n; i++) {
				f[i] = in.nextInt();
				tr.add(i, 1);
			}

			for (int i = n; i >= 1; i--) {
				f[i] -= f[i - 1];
				int tmp = tr.find(i - f[i] - 1);
				// out.println(i+","+f[i]+","+tmp);
				a[i] = tmp;
				tr.add(tmp, -1);
			}
			print(a, out);
		}
		out.flush();
		out.close();
	}
}

class BIT {
	int[] data;
	int sz;

	BIT() {
	}

	BIT(int _sz) {
		sz = _sz;
		data = new int[sz + 1];
	}

	int lowbit(int x) {
		return x & (-x);
	}

	void add(int p, int v) {
		while (p <= sz) {
			data[p] += v;
			p += lowbit(p);
		}
	}

	int sum(int p) {
		int res = 0;
		while (p > 0) {
			res += data[p];
			p -= lowbit(p);
		}
		return res;
	}

	int find(int p) {
		int l = 1, r = sz;
		while (l < r) {
			int mid = (l + r) >> 1;
			if (sum(mid) <= p)
				l = mid + 1;
			else
				r = mid;
		}

		return l;
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
