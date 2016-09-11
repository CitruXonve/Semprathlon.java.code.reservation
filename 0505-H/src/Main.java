
/**
 * May 5, 2016 9:06:21 PM
 * PrjName: 0505-H
 * @semprathlon
 */

import java.io.*;
import java.util.*;

public class Main {
	static Vector<Pair<Integer, Integer>> vec = new Vector<Pair<Integer, Integer>>();
	final static int maxn = 100010;
	static int[] f1 = new int[maxn];
	static int[] f2 = new int[maxn];
	static int[] t1 = new int[maxn];
	static int[] t2 = new int[maxn];

	static int bisearch1(int l, int r, int v) {
		Pair<Integer, Integer> p = Pair.make_pair(v, 0);
		while (l < r) {
			int m = (l + r) >> 1;
			if (vec.get(m).compareTo(p) <= 0)
				l = m + 1;
			else
				r = m;
		}
		return l;
	}

	static int cal(int a, int b, int c, int d) {
		if (a > 0 && b == 0)
			return 1;
		if (a > b)
			return c > d ? b + 1 : b;
		return Math.min(a, b);
	}

	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int cas = 0;
		while (in.hasNext()) {
			int n = in.nextInt();
			int m = in.nextInt();
			int t = in.nextInt();
			vec.clear();
			for (int i = 1; i <= n; i++) {
				vec.add(Pair.make_pair(in.nextInt(), i));
			}
			Collections.sort(vec);
			Arrays.fill(f1, 0);
			Arrays.fill(f2, 0);
			Arrays.fill(t1, 0);
			Arrays.fill(t2, 0);
			for (int i = 1; i <= m; i++) {
				int a = in.nextInt();
				int b = in.nextInt();
				int tmp = bisearch1(0, n - 1, a + 1) + 1;

				if (tmp == n) {
					if (vec.get(n - 1).left <= a) {
						f1[n]++;
						t1[n] = i;
					} else {
						f1[n - 1]++;
						t1[n - 1] = i;
					}
				} else if (tmp == 1) {
					if (vec.get(0).left <= a) {
						f1[1]++;
						t1[1] = i;
					}
				} else {
					f1[tmp - 1]++;
					t1[tmp - 1] = i;
				}

				// out.print(tmp+"_");
				tmp = bisearch1(0, n - 1, b + 1) + 1;
				// out.print(tmp+"_");

				if (tmp == n) {
					if (vec.get(n - 1).left > b) {
						f2[n]++;
						t2[n] = i;
					}
				} else if (tmp == 1) {
					if (vec.get(0).left <= b) {
						f2[2]++;
						t2[2] = i;
					} else {
						f2[1]++;
						t2[1] = i;
					}
				} else {
					f2[tmp]++;
					t2[tmp] = i;
				}
			}
			int delta = f2[1], tm = t2[1];
			for (int i = 2; i <= n; i++) {
				delta += f2[i];
				f2[i] = delta;
				tm = Math.max(tm, t2[i]);
				t2[i] = tm;
			}

			delta = f1[n];
			tm = t1[n];
			for (int i = n - 1; i >= 1; i--) {
				delta += f1[i];
				f1[i] = delta;
				tm = Math.max(tm, t1[i]);
				t1[i] = tm;
			}

			int ans = 0;
			for (int i = 1; i <= n; i++) {
				// out.println(f1[i]+","+f2[i]);
				// out.println(i+","+cal(f1[i], f2[i],t1[i],t2[i]));
				if (cal(f1[i], f2[i], t1[i], t2[i]) >= t)
					ans++;
			}

			out.println("Case " + (++cas) + ": " + ans);
		}
		out.flush();
		out.close();
	}

}

class Pair<TypeA extends Comparable<TypeA>, TypeB extends Comparable<TypeB>> implements Comparable<Pair<TypeA, TypeB>> {
	TypeA left;
	TypeB right;

	public Pair() {
	}

	public Pair(TypeA first, TypeB second) {
		left = first;
		right = second;
	}

	public static <TypeA extends Comparable<TypeA>, TypeB extends Comparable<TypeB>> Pair<TypeA, TypeB> make_pair(
			TypeA first, TypeB second) {
		return new Pair<TypeA, TypeB>(first, second);
	}

	public String toString() {
		return "[" + left.toString() + "," + right.toString() + "]";
	}

	boolean equals(Pair<TypeA, TypeB> p) {
		return this.left.equals(p.left) && this.right.equals(p.right);
	}

	public int compareTo(Pair<TypeA, TypeB> p) {
		if (this.left.equals(p.left))
			return this.right.compareTo(p.right);
		return this.left.compareTo(p.left);
	}
}

class Pair_Comp<TypeA extends Comparable<TypeA>, TypeB extends Comparable<TypeB>>
		implements Comparator<Pair<TypeA, TypeB>> {
	public int compare(Pair<TypeA, TypeB> p1, Pair<TypeA, TypeB> p2) {
		return p1.compareTo(p2);
	}
}

class InputReader {
	public BufferedReader reader;
	public StringTokenizer tokenizer;

	public InputReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream), 32768);
		tokenizer = null;
	}

	public boolean hasNext() {
		return tokenizer != null && tokenizer.hasMoreTokens() || nextLine() != null;
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