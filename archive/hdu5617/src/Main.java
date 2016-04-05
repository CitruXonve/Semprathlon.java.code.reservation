
/**
 * Mar 1, 2016 7:18:49 PM
 * PrjName: hdu5617
 * @semprathlon
 */
import java.io.*;
import java.util.*;

public class Main {
	final static int mod = 5201314;
	static char[][] g;
	static int[][][] f;
	static int n;

	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int T = in.nextInt();
		while (T-- > 0) {
			n = in.nextInt();
			g = new char[n + 2][n + 2];
			f = new int[2][n + 2][n + 2];
			for (int i = 1; i <= n; i++) {
				String str = in.next();
				for (int j = 0; j < n; j++)
					g[i][j + 1] = str.charAt(j);
			}
			int cur = 0;
			f[cur][1][n] = (g[1][1] == g[n][n] ? 1 : 0);

			for (int k = 1; k <= n - 1; k++) {
				cur ^= 1;
				for (int i = 1; i <= k + 1; i++)
					for (int j = 1; j <= k + 1; j++) {
						int x1 = i, x2 = n - j + 1;
						int y1 = k - x1 + 2, y2 = 2 * n - k - x2;

						if (g[x1][y1] == g[x2][y2]) {
							f[cur][x1][x2] = f[cur ^ 1][x1][x2];
							f[cur][x1][x2] += f[cur ^ 1][x1 - 1][x2];f[cur][x1][x2] %= mod;
							f[cur][x1][x2] += f[cur ^ 1][x1][x2 + 1];f[cur][x1][x2] %= mod;
							f[cur][x1][x2] += f[cur ^ 1][x1 - 1][x2 + 1];f[cur][x1][x2] %= mod;
						} else
							f[cur][x1][x2] = 0;
					}
			}
			int ans = 0;
			for (int i = 1; i <= n; i++) {
				ans += f[cur][i][i];
				ans %= mod;
			}
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