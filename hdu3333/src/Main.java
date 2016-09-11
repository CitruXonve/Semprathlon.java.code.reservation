
/**
 * May 6, 2016 8:15:30 PM
 * PrjName: hdu3333
 * @semprathlon
 */
import java.io.*;
import java.util.*;

public class Main {
	// static SegTree st = new SegTree();
	final static int maxn=30010;
	static BIT st = new BIT(maxn);
	static int[] a=new int[maxn];
	static long[] ans=new long[maxn];
	static Vector<Pair<Pair<Integer, Integer>, Integer>> vec = new Vector<Pair<Pair<Integer, Integer>, Integer>>();
	static Map<Integer, Integer> mp = new HashMap<Integer, Integer>();

	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int T = in.nextInt();
		while (T-- > 0) {
			int n = in.nextInt();
			a = new int[n + 1];
			// st.build(1, n);
			st.init();
			for (int i = 1; i <= n; i++)
				a[i] = in.nextInt();
			int q = in.nextInt();
			vec.clear();
			for (int i = 0; i < q; i++) {
				int x = in.nextInt();
				int y = in.nextInt();
				vec.add(Pair.make_pair(Pair.make_pair(y, x), i));
			}
			ans = new long[q];
			Collections.sort(vec);
			mp.clear();
			int j = 0;
			for (int i = 1; i <= n; i++) {
				if (mp.containsKey(a[i])) {
					st.add(mp.get(a[i]), -a[i]);
				}
				mp.put(a[i], i);
				st.add(i, a[i]);
				for (; j < vec.size() && vec.get(j).left.left == i; j++) {
					ans[vec.get(j).right] = st.sum(vec.get(j).left.left) - st.sum(vec.get(j).left.right - 1);// st.query(vec.get(j).left.right,
																												// vec.get(j).left.left);
				}
			}
			for (int i = 0; i < q; i++)
				out.println(ans[i]);
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

	void init() {
		Arrays.fill(data, 0);
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
/*
 * class SegTree { int l, r, m; long val; SegTree L, R;
 * 
 * SegTree() { }
 * 
 * SegTree(int x, int y) { build(x, y); }
 * 
 * void build(int x, int y) { int mi = (x + y) >> 1; l = x; r = y; m = mi; val =
 * 0; if (x < y) { if (L == null) L = new SegTree(x, m); else L.build(x, m);
 * 
 * if (R == null) R = new SegTree(m + 1, y); else R.build(m + 1, y); } }
 * 
 * void up() { val = L.val + R.val; }
 * 
 * void add(int x, int v) { if (l == r) { val += v; return; } if (x <= m)
 * L.add(x, v); else if (x > m) R.add(x, v); up(); }
 * 
 * long query(int x, int y) { if (x <= l && r <= y) { return val; } long res =
 * 0; if (x <= m) res += L.query(x, y); if (y > m) res += R.query(x, y); return
 * res; } }
 */

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