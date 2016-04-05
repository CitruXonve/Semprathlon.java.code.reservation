
/**
 * Mar 28, 2016 8:25:14 PM
 * PrjName: fzu2155
 * @semprathlon
 */
import java.io.*;
import java.util.*;

public class Main {
	final static int maxn = 2000010;
	static int[] f = new int[maxn];
	static int[] g = new int[maxn];

	static int n, m, cnt;
	static Set<Integer> st = new HashSet<Integer>();

	static int getf(int x) {
		if (f[x] == x)
			return x;
		f[x] = getf(f[x]);
		return f[x];
	}

	static boolean unite(int x, int y) {
		int a = getf(g[x]);
		int b = getf(g[y]);
		if (a == b)
			return false;
		f[a] = b;
		return true;
	}

	static void init() {
		cnt = n;
		for (int i = 0; i < n + m; i++)
			f[i] = i;
		for (int i = 0; i < n; i++)
			g[i] = i;
	}

	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int cas = 0;
		while (in.nextLine() != null) {
			n = in.nextInt();
			m = in.nextInt();
			if (n == 0 && m == 0)
				break;
			init();
			for (int i = 1; i <= m; i++) {
				String s = in.next();
				if (s.charAt(0) == 'M') {
					int x = in.nextInt();
					int y = in.nextInt();
					unite(x, y);
				} else if (s.charAt(0) == 'S') {
					int x = in.nextInt();
					g[x] = cnt++;
				}
			}
			st.clear();
			for (int i = 0; i < n; i++)
				st.add(getf(g[i]));

			out.println("Case #" + (++cas) + ": " + st.size());
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
