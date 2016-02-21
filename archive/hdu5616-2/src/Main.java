
/**
 * Feb 16, 2016 1:24:36 PM
 * PrjName: hdu5616-2
 * @semprathlon
 */
import java.io.*;
import java.util.*;

public class Main {
	final static int maxn = 2010;
	static int[] w, f;

	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int T = in.nextInt();
		while (T-- > 0) {
			int n = in.nextInt();
			int sw = 0;
			w = new int[n];
			f = new int[maxn];
			f[0] = 1;
			for (int i = 0; i < n; i++)
				w[i] = in.nextInt();
			for (int i = 0; i < n; i++)
				for (int j = maxn - 1; j >= w[i]; j--)
					if (f[j - w[i]] > 0)
						f[j]++;
			for (int i = 0; i < n; i++)
				for (int j = 0; j < maxn - w[i]; j++)
					if (f[j + w[i]] > 0)
						f[j]++;
			int m = in.nextInt();
			while (m-- > 0) {
				int q = in.nextInt();
				out.println(f[q] > 0 ? "YES" : "NO");
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