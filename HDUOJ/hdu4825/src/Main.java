/**
 * 2015��7��15�� ����11:21:15
 * PrjName:hdu4825
 * @ Semprathlon
 */

import java.io.*;

class Trie {
	final int maxd = 33;
	long data;
	Trie ch0, ch1;

	void insert(long n) {
		Trie rt = this;
		for (int i = maxd - 1; i >= 0; i--) {
			if ((n & (1L << i)) == 0L) {// 0
				if (rt.ch0 == null)
					rt.ch0 = new Trie();
				rt = rt.ch0;
			} else {// 1
				if (rt.ch1 == null)
					rt.ch1 = new Trie();
				rt = rt.ch1;
			}
			if (i == 0)
				rt.data = n;
		}
	}

	long query(long n) {
		Trie rt = this;
		for (int i = maxd - 1; i >= 0; i--) {
			if ((n & (1L << i)) > 0L && rt.ch0 != null || rt.ch1 == null)// 0
				rt = rt.ch0;
			else if (rt.ch1 != null)// 1
				rt = rt.ch1;
		}
		return rt.data;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(
				new InputStreamReader(System.in)));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int cas = 0;
		in.nextToken();
		int T = (int) in.nval;
		while (T-- > 0) {
			Trie tr = new Trie();
			in.nextToken();
			int n = (int) in.nval;
			in.nextToken();
			int m = (int) in.nval;
			for (int i = 1; i <= n; i++) {
				in.nextToken();
				tr.insert((long) in.nval);
			}
			out.println("Case #" + (++cas) + ":");
			for (int i = 1; i <= m; i++) {
				in.nextToken();
				out.println(tr.query((long) in.nval));
			}
		}
		out.flush();
		out.close();
	}

}