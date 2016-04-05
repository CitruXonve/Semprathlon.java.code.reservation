
/**
 * Mar 30, 2016 2:07:43 PM
 * PrjName: 0329-I-4
 * @semprathlon
 */
import java.io.*;
import java.util.*;

public class Main {
	static int[] a,f;

	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int T = in.nextInt();
		while (T-- > 0) {
			int n = in.nextInt();
			a = new int[n+2];
			f=new int[n+2];
			long ans = 0, cnt = 0;
			for (int i = 1; i <= n; i++) 
				a[i] = in.nextInt();
			for(int i=n;i>=1;i--)
				f[i]=Math.max(f[i+1], a[i]);
			for(int i=1;i<=n;i++)
				if (a[i]<f[i+1]) {
						ans -= a[i];
						cnt++;
					} else {
						ans += a[i] * cnt;
						cnt = 0;
					}
			if (cnt > 0) {
				ans += a[n] * cnt;
				cnt = 0;
			}
			if (ans < 0)	ans = 0;
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
