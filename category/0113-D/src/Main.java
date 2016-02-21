
/**
 * Jan 13, 2016 6:26:17 PM
 * PrjName: 0113-D
 * @semprathlon
 */
import java.io.*;
import java.util.*;

public class Main {
	static int f[], a[];
	static boolean g[];

	static int binarySearch(int a[], int n, int key) {
		int l = 0, r = n - 1;
		int mid;
		while (l <= r) {
			mid = l + (r - l) / 2;
			if (a[mid] == key)
				return mid;
			else if (a[mid] < key)
				l = mid + 1;
			else
				r = mid - 1;
		}

		return l;
	}

	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while (in.nextLine() != null) {
			int n = in.nextInt();
			a = new int[n];
			for (int i = 0; i < n; i++)
				a[i] = in.nextInt();

			int len = 0, maxLen = 0, num = 0;
			f = new int[n];
			g = new boolean[n];
			while (true) {
				len = 0;
				for (int i = 0; i < n; ++i) {
					if (g[i])
						continue;
					if ((len < 1 ? 0 : f[len - 1]) < a[i]) {
						f[len++] = a[i];
						g[i] = true;
					} else {
						int j = binarySearch(f, len, a[i]);
						f[j] = a[i];

					}

				}
				if (maxLen < len) {
					maxLen = len;
					num = 1;
				} else if (maxLen == len)
					++num;
				else
					break;
			}
			out.print(maxLen + "\n" + num + "\n");
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
