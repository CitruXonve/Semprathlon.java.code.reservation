
/**
 * Feb 13, 2016 5:03:08 PM
 * PrjName: hdu5616
 * @semprathlon
 */
import java.io.*;
import java.util.*;

public class Main {
	static int[] w;
	static Set st = new Set();
	static Set tmp;

	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int T = in.nextInt();
		while (T-- > 0) {
			int n = in.nextInt();
			int sw = 0;
			w = new int[n];
			for (int i = 0; i < n; i++)
				w[i] = in.nextInt();
			st.clear();
			st.add(0);

			for (int i = 0; i < n; i++)
				sw += w[i];
			for (int i = 0; i < (1 << (n - 1)); i++) {
				int v1 = 0;
				for (int k = 0; k < n; k++)
					if (((1 << k) & i) > 0)
						v1 += w[k];
				st.add(v1);
				st.add(sw - v1);
			}
			tmp = new Set();
			for (int i = 0; i < st.vec.size() - 1; i++, tmp.add(st.vec.get(i)))
				for (int j = i + 1; j < st.vec.size(); j++)
					tmp.add(Math.abs(st.vec.get(i) - st.vec.get(j)));
			st = tmp;
			int m = in.nextInt();
			while (m-- > 0) {
				int q = in.nextInt();
				out.println(st.contains(q) ? "YES" : "NO");
			}

		}
		out.flush();
		out.close();
	}

}

class Set {
	final int maxn = 2010;
	BitSet set;
	Vector<Integer> vec;

	Set() {
		set = new BitSet(maxn);
		vec = new Vector<Integer>();
	}

	void clear() {
		set.clear();
		vec.clear();
	}

	void add(int v) {
		if (v < 0 || v >= maxn)
			return;
		if (!this.contains(v)) {
			set.set(v);
			vec.add(v);
		}
	}

	boolean contains(int v) {
		if (v < 0 || v >= maxn)
			return false;
		return set.get(v);
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
