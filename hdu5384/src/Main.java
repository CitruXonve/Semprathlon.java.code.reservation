
/** Sep 8, 2015 9:08:56 PM
 * PrjName:hdu5384
 * @author Semprathlon
 */

import java.io.*;
import java.util.*;

public class Main {

	/**
	 * @param args
	 */
	static AC ac = new AC();
	static ArrayList<String> strs = new ArrayList<String>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int T = in.nextInt();
		while (T-- > 0) {
			int n = in.nextInt();
			int m = in.nextInt();
			strs.clear();
			for (int i = 1; i <= n; i++)
				strs.add(in.next());
			ac.clear();
			for (int i = 1; i <= m; i++)
				ac.insert(in.next());
			ac.build();
			for (int i = 0; i < n; i++)
				out.println(ac.query(strs.get(i)));
			// out.println();
		}
		out.flush();
		out.close();
	}

}

class AC {
	final int maxl = 100010, maxc = 26;
	final char fstc = 'a';
	int root, L;
	int[][] next;
	int[] fail, end;

	AC() {
		next = new int[maxl][maxc];
		fail = new int[maxl];
		end = new int[maxl];
		L = 0;
		root = newnode();
	}

	void clear() {
		Arrays.fill(fail, 0);
		Arrays.fill(end, 0);
		L = 0;
		root = newnode();
	}

	int newnode() {
		Arrays.fill(next[L], -1);
		end[L++] = 0;
		return L - 1;
	}

	void insert(String str) {
		int now = root;
		for (char ch : str.toCharArray()) {
			if (next[now][ch - fstc] == -1)
				next[now][ch - fstc] = newnode();
			now = next[now][ch - fstc];
		}
		end[now]++;
	}

	void build() {
		Queue<Integer> que = new LinkedList<Integer>();
		fail[root] = root;
		for (int i = 0; i < maxc; i++)
			if (next[root][i] == -1)
				next[root][i] = root;
			else {
				fail[next[root][i]] = root;
				que.add(next[root][i]);
			}
		while (!que.isEmpty()) {
			int now = que.poll();
			for (int i = 0; i < maxc; i++)
				if (next[now][i] == -1)
					next[now][i] = next[fail[now]][i];
				else {
					fail[next[now][i]] = next[fail[now]][i];
					que.add(next[now][i]);
				}
		}
	}

	int query(String str) {
		int now = root, res = 0;
		for (char ch : str.toCharArray()) {
			now = next[now][ch - fstc];
			int tmp = now;
			while (tmp != root) {
				res += end[tmp];
				// end[tmp] = 0;
				tmp = fail[tmp];
			}
		}
		return res;
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